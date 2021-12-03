package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TesterActor extends AbstractActor {
    private final static String CRASH_MSG = "test crashed";
    private final static String SUCCESS_MSG = "OK";
    private final static String FAILURE_MSG = "FAILED";

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
        String output;
        String result;
        String expectedRes = test.getTest().getExpectedRes();
        try {
            output = execute(test.getScript(), test.getFuncName(), test.getTest().getParams());
            if (output.equals(expectedRes)) {
                result = SUCCESS_MSG;
            } else {
                result = FAILURE_MSG;
            }
        } catch (ScriptException | NoSuchMethodException e) {
            result = CRASH_MSG;
        }
        return result;
    }

    private String execute(String jscript, String functionName, Object[] params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();
    }
}
