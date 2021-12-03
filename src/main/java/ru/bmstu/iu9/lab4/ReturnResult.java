package ru.bmstu.iu9.lab4;

import java.util.List;

public class ReturnResult {
    private final String id;
    private final List<Test> tests;

    public ReturnResult(String id, List<Test> tests) {
        this.id = id;
        this.tests = tests;
    }

    static class Test {
        private final String testName;
        private final String result;

        public Test(String testName, String result) {
            this.testName = testName;
            this.result = result;
        }
    }
}
