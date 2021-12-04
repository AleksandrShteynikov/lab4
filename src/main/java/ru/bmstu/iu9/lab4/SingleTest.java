package ru.bmstu.iu9.lab4;

public class SingleTest {

    private final String id;
    private final String script;
    private final String funcName;
    private final TestPackage.Test test;

    public SingleTest(String id, String script, String funcName, TestPackage.Test test) {
        this.id = id;
        this.script = script;
        this.funcName = funcName;
        this.test = test;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getId() {
        return id;
    }

    public TestPackage.Test getTest() {
        return test;
    }

    public String getScript() {
        return script;
    }
}
