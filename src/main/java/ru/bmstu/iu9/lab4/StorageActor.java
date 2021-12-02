package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.List;

public class StorageActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestPackage.Test.class, test -> {

                })
                .match(String.class, id -> {
                    getSender().tell(TestPackage, self);
                }).build();
    }
}
