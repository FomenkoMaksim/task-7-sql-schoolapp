package com.maksimfomenko.model;

public class Course {
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";
    public static final String COURSE_DESCRIPTION = "course_description";

    private int id;
    private String name;
    private String description;

    public Course(String name) {
        this.id = id;
        this.name = name;
        this.description = "Here we study - " + name;
    }

}
