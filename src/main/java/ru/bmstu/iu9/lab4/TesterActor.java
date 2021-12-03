package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

    private String execute(String jscript, String functionName, Object[] params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();
    }
}
