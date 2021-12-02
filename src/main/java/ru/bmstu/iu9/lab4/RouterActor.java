package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class RouterActor extends AbstractActor {
    private final static int POOL_SIZE = 5;
    private final static String ROUTER_NAME = "router";

    private ActorRef router;
    private ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StrorageActor.class));
        router = getContext().actorOf(new RoundRobinPool(5).props(Props.create(TesterActor.class)), ROUTER_NAME);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match()
    }
}
