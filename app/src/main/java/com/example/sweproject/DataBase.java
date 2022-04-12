package com.example.sweproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String STUDENT_COLUMN_STUDENT_USERNAME = "STUDENT_USERNAME";
    public static final String TEACHER_COLUMN_PASSWORD = "PASSWORD";
    public static final String STUDENT_COLUMN_PASSWORD = "PASSWORD";
    public static final String STUDENT_COLUMN_TEACHER = "TEACHER";
    public static final String QUESTION_COLUMN_GRADE = "GRADE";
    public static final String TEACHER_COLUMN_GRADE = QUESTION_COLUMN_GRADE;
    public static final String STUDENT_COLUMN_GRADE = QUESTION_COLUMN_GRADE;
    public static final String STUDENT_COLUMN_ACTUAL_NAME = "ACTUAL_STUDENT_NAME";
    public static final String TEACHER_TABLE = "TEACHER_TABLE";
    public static final String TEACHER_COLUMN_TEACHER_USERNAME = "TEACHER_USERNAME";
    public static final String TEACHER_COLUMN_ACTUAL_NAME = "ACTUAL_TEACHER_NAME";
    public static final String QUESTION_COLUMN_QUESTION = "QUESTION";
    public static final String QUESTION_TABLE = QUESTION_COLUMN_QUESTION + "_TABLE";
    public static final String QUESTION_COLUMN_QUESTION_ID = QUESTION_COLUMN_QUESTION + "_ID";
    public static final String QUESTION_COLUMN_SUBJECT = "SUBJECT";
    public static final String QUESTION_COLUMN_CORRECT_ANSWER = "CORRECT_ANSWER";
    public static final String QUESTION_COLUMN_WRONG_ANSWER_1 = "WRONG_ANSWER1";
    public static final String QUESTION_COLUMN_WRONG_ANSWER_2 = "WRONG_ANSWER2";
    public static final String QUESTION_COLUMN_WRONG_ANSWER_3 = "WRONG_ANSWER3";
    public static final String QUESTION_COLUMN_STANDARD = "STANDARD";

    public DataBase(@Nullable Context context) {
        super(context, "studentAssess.db", null, 1);
    }


    //call to create database and populate question table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuestionTableString = "CREATE TABLE " + QUESTION_TABLE + " (" + QUESTION_COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION_COLUMN_SUBJECT + " TEXT, " + QUESTION_COLUMN_GRADE + " INTEGER, " + QUESTION_COLUMN_QUESTION + " TEXT, " + QUESTION_COLUMN_CORRECT_ANSWER + " TEXT, " + QUESTION_COLUMN_WRONG_ANSWER_1 + " TEXT, " + QUESTION_COLUMN_WRONG_ANSWER_2 + " TEXT, " + QUESTION_COLUMN_WRONG_ANSWER_3 + " TEXT, " + QUESTION_COLUMN_STANDARD + " TEXT)";
        String createTeacherTableString = "CREATE TABLE " + TEACHER_TABLE + " (" + TEACHER_COLUMN_TEACHER_USERNAME + " TEXT PRIMARY KEY, " + TEACHER_COLUMN_PASSWORD + " TEXT, " + TEACHER_COLUMN_GRADE + " INTEGER, " + TEACHER_COLUMN_ACTUAL_NAME + " TEXT)";
        String createStudentTableString = "CREATE TABLE " + STUDENT_TABLE + " (" + STUDENT_COLUMN_STUDENT_USERNAME + " TEXT PRIMARY KEY, " + STUDENT_COLUMN_PASSWORD + " TEXT, " + STUDENT_COLUMN_TEACHER + " TEXT, " + STUDENT_COLUMN_GRADE + " INTEGER, " + STUDENT_COLUMN_ACTUAL_NAME + " TEXT, " + "FOREIGN KEY(" + STUDENT_COLUMN_TEACHER + ") REFERENCES " + TEACHER_TABLE + "(" + TEACHER_COLUMN_TEACHER_USERNAME + ") )";
        String createPerformanceTableString = "CREATE TABLE PERFORMANCE_TABLE (TEACHER STRING, STUDENT STRING, PERFORMANCE REAL, FOREIGN KEY(TEACHER) REFERENCES TEACHER_TABLE(TEACHER_USERNAME), FOREIGN KEY(STUDENT) REFERENCES STUDENT_TABLE(STUDENT_USERNAME))";
        db.execSQL(createQuestionTableString);
        db.execSQL(createTeacherTableString);
        db.execSQL(createStudentTableString);
        db.execSQL(createPerformanceTableString);


        String File_path = ""; //CHANGE ME!

        String line = "";
        String splitBy = ",";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(File_path));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] question = line.split(splitBy);
                question[0] = question[0].replace('&', ',');// use comma as separator
                //System.out.println("[Subject" + question[0] + ", Grade= " + question[1] + ", Question=" + question[2] + ", Correct=" + question[3] + ", Incorrect1= "
                //       + question[4] + ",Incorrect 2= " + question[5] + ",Incorrect 3= " + question[6] + ",Standard= " + question[7]+"]");

                ContentValues cv = new ContentValues();
                cv.put(QUESTION_COLUMN_QUESTION, question[2]);
                cv.put(QUESTION_COLUMN_SUBJECT, question[0]);
                cv.put(QUESTION_COLUMN_CORRECT_ANSWER, question[3]);
                cv.put(QUESTION_COLUMN_WRONG_ANSWER_1, question[4]);
                cv.put(QUESTION_COLUMN_WRONG_ANSWER_2, question[5]);
                cv.put(QUESTION_COLUMN_WRONG_ANSWER_3, question[6]);
                cv.put(QUESTION_COLUMN_STANDARD, question[7]);
                cv.put(QUESTION_COLUMN_GRADE, question[1]);
                long insert = db.insert(QUESTION_TABLE, null, cv);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //adds user (student/educator) to database
    public boolean addUser(boolean student, User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long insert;

        if (student)
        {
            cv.put(STUDENT_COLUMN_STUDENT_USERNAME, user.getUsername());
            cv.put(STUDENT_COLUMN_PASSWORD, user.getPassword());
            cv.put(STUDENT_COLUMN_TEACHER, user.getTeacher_user());
            cv.put(STUDENT_COLUMN_GRADE, user.getGrade());
            cv.put(STUDENT_COLUMN_ACTUAL_NAME, user.getActual_name());
            insert = db.insert(STUDENT_TABLE, null, cv);
        }
        else
        {
            cv.put(TEACHER_COLUMN_TEACHER_USERNAME, user.getUsername());
            cv.put(TEACHER_COLUMN_PASSWORD, user.getPassword());
            cv.put(TEACHER_COLUMN_GRADE, user.getGrade());
            cv.put(TEACHER_COLUMN_ACTUAL_NAME, user.getActual_name());
            insert = db.insert(TEACHER_TABLE, null, cv);
        }
        db.close();
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    //add performance score if no score previously saved
    public boolean addPerformance(String student_username, float score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String teacher_user = "";
        String query = "SELECT TEACHER FROM STUDENT_TABLE WHERE STUDENT_TABLE.STUDENT_USERNAME = '" + student_username + "'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 1)
        {
            teacher_user = cursor.getString(0);
        }
        else
        {
            return false;
            //this means that there is somehow more than one student with the same username (BAD)
        }

        cursor.close();

        cv.put("TEACHER", teacher_user);
        cv.put("STUDENT", student_username);
        cv.put("PERFORMANCE", score);

        long insert1 = db.insert("PERFORMANCE_TABLE", null, cv);

        db.close();
        if (insert1 == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    //changes performance score if student is retaking assessment
    public boolean changePerformance(String student_username, float score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("PERFORMANCE", score);

        long insert1 = db.update("PERFORMANCE_TABLE", cv, "PERFORMANCE_TABLE.STUDENT=?", new String[]{student_username});

        db.close();
        if (insert1 == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean checkAssessmentTaken(String student_username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String teacher_user = "";
        String query = "SELECT * FROM PERFORMANCE_TABLE WHERE STUDENT_TABLE.STUDENT_USERNAME = '" + student_username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 1)
        {
            db.close();
            cursor.close();
            return true;
        }
        else
        {
            db.close();
            cursor.close();
            return false;
        }

    }


    //Now returns 1 random question per standard
    public List<question> getrandQuestions(String grade, String subject)
    {
        List<question> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //look for all standards in the subject/grade
        String query = "SELECT DISTINCT QUESTION_COLUMN_STANDARD FROM QUESTION_TABLE WHERE " +
                "QUESTION_COLUMN_GRADE == " + grade+ " AND QUESTION_COLUMN_SUBJECT == " + subject;
        Cursor cursor = db.rawQuery(query,null);

        //populate a list with all the standards in the subject/grade
        List<String> applicable_standards = new ArrayList<String>();
        while(cursor.moveToNext()){
            String std = cursor.getString(0);
            applicable_standards.add(std);
        }

        //iterates over every desired standard, randomly selects 1 question for it. Adds it to return list
        for(String std_toget : applicable_standards){
            String std_query = "SELECT * from QUESTION_TABLE where QUESTION_COLUMN_STANDARD == " + std_toget
                    + "ORDER BY random() LIMIT 1";
            Cursor std_cursor = db.rawQuery(std_query, null);

            int questionID = std_cursor.getInt(0);
            String subject_toadd = std_cursor.getString(1);
            int grade_toadd = std_cursor.getInt(2);
            String question = std_cursor.getString(3);
            String correct_answer = std_cursor.getString(4);
            String wrong_answer1 = std_cursor.getString(5);
            String wrong_answer2 = std_cursor.getString(6);
            String wrong_answer3 = std_cursor.getString(7);
            String standard = std_cursor.getString(8);

            question _question = new question(questionID, subject_toadd, grade_toadd, question, correct_answer, wrong_answer1, wrong_answer2, wrong_answer3, standard);
            returnList.add(_question);
            std_cursor.close();
        }


        cursor.close();
        db.close();
        return returnList;
    }

    public List<scoreReport> getReportsForEachTeacher(String teacher_username)
    {
        List<scoreReport> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT PERFORMANCE_TABLE.STUDENT, STUDENT_TABLE.ACTUAL_STUDENT_NAME , PERFORMANCE_TABLE.PERFORMANCE FROM PERFORMANCE_TABLE, STUDENT_TABLE WHERE PERFORMANCE_TABLE.STUDENT = STUDENT_TABLE.STUDENT_USERNAME AND PERFORMANCE_TABLE.TEACHER = '" + teacher_username + "' ORDER BY STUDENT_TABLE.ACTUAL_STUDENT_NAME";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do
            {
                String student_user = cursor.getString(0);
                String student_name = cursor.getString(1);
                float score = cursor.getFloat(2);

                scoreReport report = new scoreReport(student_user, student_name, score);
                returnList.add(report);
            }
            while(cursor.moveToNext());
        }
        else
        {
            //nothing happened (this is BAD)
        }

        cursor.close();
        db.close();
        return returnList;
    }

}
