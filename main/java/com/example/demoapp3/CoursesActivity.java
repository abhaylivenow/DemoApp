package com.example.demoapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    private ListView list_view;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        initViews();   // initialize all views

        ArrayList<String> course_list = new ArrayList<>();  // arrayList fro the available courses
        course_list.add("1. Math");
        course_list.add("2. Physics");
        course_list.add("3. Chemistry");
        course_list.add("4. Biology");

        // set adapter for the arrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,android.R.layout.simple_list_item_1,course_list);
        list_view.setAdapter(adapter);

        // click listener for individual items in the arrayList to show detail about the subject
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                Intent i = new Intent(CoursesActivity.this,CourseDetail.class);
                if(position == 0){
                    message = "Math";
                    i.putExtra("key",message);
                    startActivity(i);
                }
                if(position == 1){
                    message = "Physics";
                    i.putExtra("key",message);
                    startActivity(i);
                }
                if(position == 2){
                    message = "Chemistry";
                    i.putExtra("key",message);
                    startActivity(i);
                }
                if(position == 3){
                    message = "Biology";
                    i.putExtra("key",message);
                    startActivity(i);
                }
            }
        });

        // travel back to the previous activity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CoursesActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    public void initViews(){
        list_view = findViewById(R.id.course_list);
        btnBack = findViewById(R.id.btnback);
    }
}