package com.example.demoapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    private TextView doubt,or;
    private ImageView cameraImage;
    private Button btn,btnCourse,btn_upload,btnMyCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();   // Initialize all the views of the Activity

        // Code for notification starts here
        String notificationText = "Hey there! I hope you enjoying the App";  // text for notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this) // Initializing notification
                .setSmallIcon(R.drawable.ic_camera).setContentTitle("DemoApp").setContentText(notificationText).setAutoCancel(true);

        Intent i = new Intent(MainActivity.this, Notification.class);
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("notificationText",notificationText);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
        // code for notification ends here

        final ArrayList<String> subjectList = new ArrayList<>(); // ArrayList of subjects for search view which is initially set
        subjectList.add("Math Class");                                                                  // to invisible
        subjectList.add("Physics Class");
        subjectList.add("Chemistry Class");
        subjectList.add("Biology Class");

        // setting adapter for arrayList using inbuilt adapters in android layout
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,subjectList);
        listView.setAdapter(adapter);

        // below code is to handle the click on the items that appeared while searching
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                Intent i = new Intent(MainActivity.this, LiveClass.class);
                if(position == 0){
                    message = "Math";
                    i.putExtra("LiveClass",message); // creating text to receive from other activity
                    startActivity(i);
                }
                if(position == 1){
                    message = "Physics";
                    i.putExtra("LiveClass",message);
                    startActivity(i);
                }
                if(position == 2){
                    message = "Chemistry";
                    i.putExtra("LiveClass",message);
                    startActivity(i);
                }
                if(position == 3){
                    message = "Biology";
                    i.putExtra("LiveClass",message);
                    startActivity(i);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery(query,true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);   // Only filter that item that matches to the string 's'
                listView.setVisibility(View.VISIBLE); // set visibility to visible
                doubt.setVisibility(View.GONE);        // for improving the user experience
                cameraImage.setVisibility(View.GONE);  // same as above
                or.setVisibility(View.GONE);           // same as above
                btn_upload.setVisibility(View.GONE);   // same as above

                if(s.length() == 0){           // handles the situation when search view is empty
                    listView.setVisibility(View.GONE);
                    doubt.setVisibility(View.VISIBLE);
                    cameraImage.setVisibility(View.VISIBLE);
                    or.setVisibility(View.VISIBLE);
                    btn_upload.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        // Opens the camera of the phone
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent();
                    i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(i);
                }catch (Exception e){   // Check for the unexpected results
                    e.printStackTrace();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {          // open the gallery the upload images
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 1000);
            }
        });

        btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     // Explore the available courses
                Intent i = new Intent(MainActivity.this,CoursesActivity.class);
                startActivity(i);
            }
        });
        btnMyCourse.setOnClickListener(new View.OnClickListener() {   // Open the list of the course you enrolled
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MyCourses.class);
                startActivity(i);            }
        });

    }

    // initialize all the views
    public void initViews(){
        searchView = findViewById(R.id.searchview);
        listView = findViewById(R.id.listview);
        doubt = findViewById(R.id.doubt);
        or = findViewById(R.id.or);
        cameraImage = findViewById(R.id.cameraImg);
        btn = findViewById(R.id.btn_upload);
        btnCourse = findViewById(R.id.btnCourse);
        btnMyCourse = findViewById(R.id.btn_my_course);
        btn_upload = findViewById(R.id.btn_upload);
    }
}