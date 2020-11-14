package com.example.demoapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyCourses extends AppCompatActivity {

    private ListView myCourses_list;
    DataBaseHelper myDb;
//    private Button btn_remove;
    private TextView txt;
    ArrayList<String> course_list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        initViews();  // initialize all the views in the activity

        course_list = new ArrayList<>();   // initialize arrayList
        Cursor data = myDb.getDataFromTable();   // get the reference of the cursor

        // add the incoming data from the database to the list view
        while (data.moveToNext()){  // while there is data perform the following below codes
            course_list.add(data.getString(1));   // get the data from 2nd (1 is the index) column from the table
            adapter = new ArrayAdapter<>(MyCourses.this,android.R.layout.simple_list_item_1,course_list); // set the adapter
            myCourses_list.setAdapter(adapter);
        }

        // removing courses from my courses
        myCourses_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    adapter.remove(course_list.get(0));
                    Toast.makeText(MyCourses.this,  "Math is removed", Toast.LENGTH_SHORT).show();
                }
                if (position == 1){
                    adapter.remove(course_list.get(1));
                    Toast.makeText(MyCourses.this,  "Physics is removed", Toast.LENGTH_SHORT).show();
                }
                if (position == 2){
                    adapter.remove(course_list.get(2));
                    Toast.makeText(MyCourses.this,  "Chemistry is removed", Toast.LENGTH_SHORT).show();
                }
                if (position == 4){
                    adapter.remove(course_list.get(3));
                    Toast.makeText(MyCourses.this,  "Biology is removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(course_list.size() == 0) txt.setText("Empty list, add some courses");
        if(course_list.size() > 0) txt.setText("Tap on the course to remove");

    }
    // initialize views
    public void initViews(){
        myCourses_list = findViewById(R.id.my_courses);
        myDb = new DataBaseHelper(MyCourses.this);
//      btn_remove = findViewById(R.id.btn_remove);
        txt = findViewById(R.id.txt);
    }
}