package com.hsbc.models;

public class Test {

    private int tid;
    private int appId;
    private String name;

    public Test(int tid, int appId, String name) {
        this.tid = tid;
        this.appId = appId;
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "tid=" + tid +
                ", appId=" + appId +
                ", name='" + name + '\'' +
                '}';
    }
}

