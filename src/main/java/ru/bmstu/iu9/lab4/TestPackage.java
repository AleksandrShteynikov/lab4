package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPackage {

    private final String id;
    private final String script;
    private final String funcName;
    private final List<Test> tests;

    public TestPackage(@JsonProperty()) {

    }

    public String getID() {
        return id;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getScript() {
        return script;
    }

    public List<Test> getTests() {
        return tests;
    }

    static class Test {

    }
}
