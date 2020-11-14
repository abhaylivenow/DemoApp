package com.example.demoapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class CourseDetail extends AppCompatActivity {

    private TextView course_detail;
    private TextView course_head;
    private Button btn_back,btn_enroll;
    private EditText invisible_edit_text;
    DataBaseHelper DB;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initViews();  // initialize all the views

        Bundle extra = getIntent().getExtras();   // get the incoming msg from the course activity
        final String text = extra.getString("key");
        final String msgForToast = "You are enrolled to " +text;  // set the toast msg to confirm that subject is enrolled

        course_head.setText(text);  // set the text of the heading according to the msg coming from course list activity

        // demo text for explaining details of the subject
        course_detail.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an" +
                " unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic typesetting," +
                " remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum.");

        // this click listener will add the current course to the database or to the my course activity
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseDetail.this, msgForToast+", added to My Course", Toast.LENGTH_SHORT).show();
                String newEntry = course_head.getText().toString();
                addData(newEntry);
            }
        });
        // travel back to the previous activity
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseDetail.this,CoursesActivity.class);
                startActivity(i);
            }
        });
    }
    // this method takes data from current activity and add it to the database
    public void addData(String newEntry){
        boolean insertData = DB.add(newEntry);
//        if(insertData == true) Toast.makeText(this, "Added to My Course", Toast.LENGTH_SHORT).show();
    }

    // initialize all the views of activity
    public void initViews(){
        course_detail = findViewById(R.id.course_detail_text);
        course_head = findViewById(R.id.CourseHead);
        btn_enroll = findViewById(R.id.btn_enroll);
        btn_back = findViewById(R.id.btn_back);
        invisible_edit_text = findViewById(R.id.edit_text);
        DB = new DataBaseHelper(CourseDetail.this);
    }
}