package ru.bmstu.iu9.lab4;

public class TestResult {
    private final String id;
    private final String testName;
    private final String result;

    public TestResult(String id, String testName, String result) {
        this.id = id;
        this.testName = testName;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public String getTestName() {
        return testName;
    }
}
