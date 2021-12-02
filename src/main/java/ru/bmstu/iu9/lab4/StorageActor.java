package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {

    private Map<String, Object> results = new HashMap<>();

    public StorageActor() {}

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SingleTest.class, test -> {
                    putTestResult(test);
                })
                .match(String.class, id -> {
                    getSender().tell(getResults(id), self());
                }).build();
    }

    private void putTestResult(SingleTest test) {

    }

    private TestPackage getResults(String id) {
        return null;
    }
}
