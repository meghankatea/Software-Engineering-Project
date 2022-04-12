package com.example.sweproject;

public class User {
    private String username;
    private String password;
    private int grade;
    private String teacher_user = "";
    private String actual_name;

    //for inserting students
    public User(String username, String password, int grade, String teacher_user, String actual_name)
    {
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.teacher_user = teacher_user;
        this.actual_name = actual_name;
    }

    //for inserting teachers
    public User(String username, String password, int grade, String actual_name)
    {
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.actual_name = actual_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getGrade() {
        return grade;
    }

    public String getTeacher_user() {
        return teacher_user;
    }

    public String getActual_name() {
        return actual_name;
    }
}
