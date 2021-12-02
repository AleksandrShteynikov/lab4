package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class RouterActor extends AbstractActor {
    private ActorRef router;
    private ActorRef storage;

    @Override
    public Receive createReceive() {
        return null;
    }

    public RouterActor() {

    }
}
