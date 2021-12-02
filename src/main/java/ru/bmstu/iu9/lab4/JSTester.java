package ru.bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class JSTester {
    static final String AKKA_SYSTEM_NAME = "Akka JS Tester";
    static final String ROUTER_NAME = "router";
    static final int POOL_SIZE = 5;
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(AKKA_SYSTEM_NAME);
        ActorRef router = getContext().actorOf(Props.create(RouterActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        JSTester instance = new JSTester();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute(system).flow(system, materializer);

    }
}
