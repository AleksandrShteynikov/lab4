package ru.bmstu.iu9.lab4;

import java.util.List;

public class ReturnResult {
    private final String id;
    private final List<Test> tests;

    public ReturnResult(String id, List<Test> tests) {
        this.tests = tests;
        this.id = id;
    }

    static class Test {

    }
}
