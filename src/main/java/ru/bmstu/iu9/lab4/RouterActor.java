package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

import java.util.List;

public class RouterActor extends AbstractActor {
    private final static int POOL_SIZE = 5;
    private final static String ROUTER_NAME = "router";

    private final ActorRef router;
    private final ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StrorageActor.class));
        router = getContext().actorOf(new RoundRobinPool(POOL_SIZE).props(Props.create(TesterActor.class)), ROUTER_NAME);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestPackage.class, testPackage -> {
                    String id = testPackage.getID();
                    String JSScript = testPackage.getScript();
                    String funcName = testPackage.getFuncName();
                    List<TestPackage.Test> tests = testPackage.getTests();
                    for (test : )
                })
                .match(String.class, id -> {
                    storage.tell(id, getSender());
                }).build();
    }
}
