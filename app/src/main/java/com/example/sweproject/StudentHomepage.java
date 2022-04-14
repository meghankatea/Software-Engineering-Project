package com.example.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Button takeMath = (Button)findViewById(R.id.takeMathematicsQuiz);
        takeMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomepage.this, MathQuiz.class));
            }
        });


        Button takeScience = (Button)findViewById(R.id.takeScienceQuiz);

        takeScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomepage.this, ScienceQuiz.class));
            }
        });
    }
}