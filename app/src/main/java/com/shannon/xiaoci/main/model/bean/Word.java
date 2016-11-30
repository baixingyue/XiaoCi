package com.shannon.xiaoci.main.model.bean;


public class Word {

    String name;
    String mean;
    String type;

    public Word(String name, String type, String mean) {
        this.name = name;
        this.mean = mean;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", mean='" + mean + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
