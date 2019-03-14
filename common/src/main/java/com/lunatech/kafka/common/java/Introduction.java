package com.lunatech.kafka.common.java;

public class Introduction {
    private String name;
    private String greeting;

    public String getName() {
        return name;
    }

    public String getGreeting() {
        return greeting;
    }

    public Introduction(String name, String greeting) {
        this.name = name;
        this.greeting = greeting;
    }

    public Introduction() { /*Default Constructor for deserializer */ }

    @Override
    public String toString() {
        return "Introduction{" +
                "name='" + name + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
