package javaexercise.interview.generics;

/**
 * Created by rxh on 2018/4/15.
 */

public class AssignValueNew {
    private String test;
    private String name;

    /*public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */

    @Override
    public String toString() {
        return "AssignValueNew{" +
                "test='" + (test == null ? "null" : test) + '\'' +
                ", name='" + (name == null ? "null" : name) + '\'' +
                '}';
    }
}