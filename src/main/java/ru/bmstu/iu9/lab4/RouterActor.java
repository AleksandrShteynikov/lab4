package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class RouterActor extends AbstractActor {
    private ActorRef router;
    private ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf();
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
