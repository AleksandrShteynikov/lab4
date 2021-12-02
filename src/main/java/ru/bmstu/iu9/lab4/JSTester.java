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
    private static final int PORT = 8080;
    private static final int TIMEOUT = 5000;
    private static final String ID_PARAM = "packageId";
    private static final String AKKA_SYSTEM_NAME = "Akka JS Tester";
    private static final String HOST_NAME = "localhost";
    private static final String SERVER_MSG = "Server online at http://" + HOST_NAME + ":" + PORT +"/\nPress RETURN to stop...";
    private static final String URL_EXT = "api";
    private static final String RET_MSG = "Test started";

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create(AKKA_SYSTEM_NAME);
        ActorRef router = system.actorOf(Props.create(RouterActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        JSTester instance = new JSTester();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute(router).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                                                                          ConnectHttp.toHost(HOST_NAME, PORT),
                                                                          materializer);
        System.out.println(SERVER_MSG);
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound->system.terminate());
    }

    private Route createRoute(ActorRef router) {
        return route(
                path(URL_EXT, () -> route(get(() -> parameter(ID_PARAM, id -> {
                    Future<Object> result = Patterns.ask(router, id, TIMEOUT);
                    return completeOKWithFuture(result, Jackson.marshaller());
                })))),
                path(URL_EXT, () -> route(post(() -> entity(Jackson.unmarshaller(TestPackage.class), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete(RET_MSG);
                }))))
        );
    }
}