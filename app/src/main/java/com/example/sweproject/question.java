package com.example.sweproject;

public class question {
    private int question_id;
    private String subject;
    private int grade;
    private String question;
    private String right_answer;
    private String wrong_answer1;
    private String wrong_answer2;
    private String wrong_answer3;
    private String standard;

    public question(int question_id, String subject, int grade, String question, String right_answer, String wrong_answer1, String wrong_answer2, String wrong_answer3, String standard) {
        this.question_id = question_id;
        this.subject = subject;
        this.grade = grade;
        this.question = question;
        this.right_answer = right_answer;
        this.wrong_answer1 = wrong_answer1;
        this.wrong_answer2 = wrong_answer2;
        this.wrong_answer3 = wrong_answer3;
        this.standard = standard;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public String getQuestion() {
        return question;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public String getWrong_answer1() {
        return wrong_answer1;
    }

    public String getWrong_answer2() {
        return wrong_answer2;
    }

    public String getWrong_answer3() {
        return wrong_answer3;
    }

    public String getStandard() {
        return standard;
    }
}
