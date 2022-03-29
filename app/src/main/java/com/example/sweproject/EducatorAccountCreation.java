package com.example.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class EducatorAccountCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educator_account_creation);

        //Drop Down Menu Functionality for Educator to Set Grade Level for Students
        Spinner gradeLevelMenu = findViewById(R.id.gradeLevelDropDown);
        ArrayAdapter<CharSequence> gradeLevelAdapter = ArrayAdapter.createFromResource(this, R.array.gradeLevels, android.R.layout.simple_spinner_item);
        gradeLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeLevelMenu.setAdapter(gradeLevelAdapter);
        gradeLevelMenu.setOnItemSelectedListener(this);
    }

    //What Happens When Drop Down Menu (Grade Level Selection for Educators) Is Selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}