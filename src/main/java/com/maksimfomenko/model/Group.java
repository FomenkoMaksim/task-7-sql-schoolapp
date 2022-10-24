package com.maksimfomenko.model;

public class Group {
    private int id;
    private final String name;

    public Group(String name) {
        this.name = name;
    }

    public int getGroupID() {
        return id;
    }

    public String getGroupName() {
        return name;
    }
}
