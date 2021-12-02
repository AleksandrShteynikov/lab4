package ru.bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class JSTester {
    static final String AKKA_SYSTEM_NAME = "Akka JS Tester";
    static final String ROUTER_NAME = "router";
    static final int POOL_SIZE = 5;
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create(AKKA_SYSTEM_NAME);
        ActorRef router = system.actorOf(Props.create(RouterActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        JSTester instance = new JSTester();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute(system).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                                                                          ConnectHttp.toHost("localhost", 8080),
                                                                          materializer);
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound->system.terminate());
    }
    private Route createRoute(ActorRef router) {
        return route(
                path("api", () -> route(get(() -> {
                    Future<Object> result = Patterns.ask(router, idrequest(), 5000);
                    return completeOKWithFuture(result, Jackson.marshaller());
                }))),
                path("api", () -> route(post(() -> entity(Jackson.unmarshaller(TestPackage.class), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete("Test started");
                }))))
        );
    }
}