package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPackage {

    private final String id;
    private final String script;
    private final String funcName;
    private final List<Test> tests;

    @JsonCreator
    public TestPackage(@JsonProperty("packageId") String id,
                       @JsonProperty("jsScript") String script,
                       @JsonProperty("functionName") String funcName,
                       @JsonProperty("tests") List<Test> tests) {
        this.id = id;
        this.script = script;
        this.funcName = funcName;
        this.tests = tests;
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
        private final String testName;
        private final Object[] params;
        private final String expectedRes;
        private String result;

        @JsonCreator
        public Test(@JsonProperty("testName") String testName,
                    @JsonProperty("params") Object[] params,
                    @JsonProperty("expectedResult") String expectedRes) {
            this.testName = testName;
            this.params = params;
            this.expectedRes = expectedRes;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getTestName() {
            return testName;
        }

        public String getExpectedRes() {
            return expectedRes;
        }

        public String getResult() {
            return result;
        }

        
    }
}
