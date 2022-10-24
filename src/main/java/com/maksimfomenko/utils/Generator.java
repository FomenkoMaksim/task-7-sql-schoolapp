package com.maksimfomenko.utils;

import com.github.javafaker.Faker;
import com.maksimfomenko.model.Course;
import com.maksimfomenko.model.Group;
import com.maksimfomenko.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final String[] courseName = new String[]{"Algebra", "Biology", "Drawing",
            "Chemistry", "Geography", "Geometry", "History",
            "Literature", "Mathematics", "Music"};

    Random random = new Random();

    private List<Student> students = generateStudents();
    private List<Course> courseList;
    private List<Group> groupList = generateGroups();

    private String randomLetter() {
        int index = random.nextInt(Generator.alphabet.length);
        return String.valueOf(Generator.alphabet[index]);
    }

    private String generateGroupName() {
        int num = random.nextInt(89) + 10;
        return randomLetter() + randomLetter() + "-" + num;
    }

    private List<Group> generateGroups() {
        groupList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String groupName = generateGroupName();
            groupList.add(new Group(groupName));
        }
        return groupList;
    }

    private List<Course> generateCourses() {
        courseList = new ArrayList<>();

        for (String str : courseName) {
            courseList.add(new Course(str));
        }
        return courseList;
    }

    private List<Student> generateStudents() {
        students = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 1; i <= 200; i++) {
            students.add(new Student(faker.name().firstName(),
                    faker.name().lastName()));
        }
        return students;
    }

//    public void addStudentsToGroups() {
//        List<Group> groups = getGroups();
//        List<Student> allStudents = getStudents();
//
//        int index = 0;
//
//        for (Group group : groups) {
//            final int group_id = group.getGroupID();
//            int count = ThreadLocalRandom.current().nextInt(9, 31);
//
//            if (count != 9 && index + count < 200) {
//                for (int i = index; i < index + count; i++) {
//                    allStudents.get(i).setGroup_id(group_id);
//                }
//                index += count;
//            }
//        }
//    }

    private void courseToStudent() {
        int randNum;
        int course_index;

        for (Student student : students) {
            randNum = random.nextInt(3) + 1;

            for (int i = 0; i < randNum; i++) {
                course_index = random.nextInt(courseList.size()) + 1;

            }
        }
    }

    public void insertData(Connection connection) throws SQLException {
        for (Group group : groupList) {
            String sqlQuery = "INSERT INTO school.groups VALUES (" + group.getGroupName() + ");";
            try (Statement st = connection.createStatement()) {
                st.executeQuery(sqlQuery);
                connection.close();
            }
        }
    }

    public String getGroupName() {
        return generateGroupName();
    }

    public List<Group> getGroups() {
        return generateGroups();
    }

    public List<Course> getCourses() {
        return generateCourses();
    }

    public List<Student> getStudents() {
        return students;
    }
}
