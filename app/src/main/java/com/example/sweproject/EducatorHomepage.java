package com.example.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EducatorHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educator_homepage);



        Button manageStudents = (Button)findViewById(R.id.manageStudents);
        manageStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducatorHomepage.this, ManageStudentsPage.class));
            }
        });

        Button seeReports = (Button)findViewById(R.id.viewScoreReports);
        seeReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducatorHomepage.this, ViewScoreReportsPage.class));
            }
        });




    }
}