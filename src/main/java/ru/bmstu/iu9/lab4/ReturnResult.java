package ru.bmstu.iu9.lab4;

import java.util.List;

public class ReturnResult {
    private final String id;
    private final List<TestResult.Test> tests;

    public ReturnResult(String id, List<TestResult.Test> tests) {
        this.id = id;
        this.tests = tests;
    }

    public String getId() {
        return id;
    }

    public List<TestResult.Test> getTests() {
        return tests;
    }
}
