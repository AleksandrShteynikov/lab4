package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class TesterActor extends AbstractActor {

    public TesterActor() {}

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SingleTest.class, test -> {
                    String result = perform(test);
                    getSender().tell(new TestResult(test.getId(), test.getFuncName(), result), self());
                }).build();
    }

    private String perform(SingleTest test) {
        
    }
}
