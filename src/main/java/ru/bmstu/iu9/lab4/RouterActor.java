package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class RouterActor extends AbstractActor {
    private ActorRef router;
    private ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StrorageActor.class));
        
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
