package ru.bmstu.iu9.lab4;

public class TestResult {
    private final String id;
    private final Test test;

    public TestResult(String id, String testName, String result) {
        this.id = id;
        this.test = new Test(testName, result);
    }

    public String getId() {
        return id;
    }

    public Test getTest() {
        return test;
    }

    static class Test {
        private final String testName;
        private final String result;

        public Test(String testName, String result) {
            this.testName = testName;
            this.result = result;
        }

        public String getTestName() {
            return testName;
        }

        public String getResult() {
            return result;
        }
    }
}
