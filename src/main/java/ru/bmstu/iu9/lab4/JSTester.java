package ru.bmstu.iu9.lab4;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;

public class JSTester {
    static final String AKKA_SYSTEM_NAME = "Akka JS Tester";
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(AKKA_SYSTEM_NAME);
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        JSTester instance = new JSTester();
        final Flow<HttpRequest, HttpResponse>
    }
}
