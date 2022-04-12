package com.example.sweproject;

public class scoreReport {
    private String student_user;
    private String student_name;
    private float score;

    public scoreReport(String student_user, String student_name, float score) {
        this.student_user = student_user;
        this.student_name = student_name;
        this.score = score;
    }

    public String getStudent_user() {
        return student_user;
    }

    public String getStudent_name() {
        return student_name;
    }

    public float getScore() {
        return score;
    }
}

