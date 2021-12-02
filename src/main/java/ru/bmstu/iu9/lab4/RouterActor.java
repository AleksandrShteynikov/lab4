package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class RouterActor extends AbstractActor {
    final static int POOL_SIZE = 5;

    private ActorRef router;
    private ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StrorageActor.class));
        router = getContext().actorOf(new RoundRobinPool(5), );
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
