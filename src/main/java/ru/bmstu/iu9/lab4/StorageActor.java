package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private static final String RESULT_ABSENCE = "tests for package with provided id aren't present";

    private final Map<String, List<TestResult.Test>> results = new HashMap<>();

    public StorageActor() {}

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, this::putTestResult)
                .match(String.class, id -> {
                    getSender().tell(getResults(id), self());
                }).build();
    }

    private void putTestResult(TestResult test) {
        String id = test.getId();
        if (results.containsKey(id)) {
            results.get(id).add(test.getTest());
        } else {
            results.put(id, new ArrayList<>());
            results.get(id).add(test.getTest());
        }
    }

    private ReturnResult getResults(String id) {
        //if (results.containsKey(id)) {
            return new ReturnResult(id, results.get(id));
        //}
        //return new ReturnResult(RESULT_ABSENCE, null);
    }
}
