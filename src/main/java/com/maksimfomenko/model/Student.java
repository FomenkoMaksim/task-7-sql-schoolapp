package com.maksimfomenko.model;


public class Student {
    public static final String STUDENT_ID = "student_id";
    public static final String GROUP_ID = "group_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    int id;
    int group_id;
    String first_name;
    String last_name;

    public Student(String firstName, String lastName) {
        this.first_name = firstName;
        this.last_name = lastName;
    }
}
