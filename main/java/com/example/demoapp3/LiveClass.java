package com.example.demoapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class LiveClass extends AppCompatActivity {

    private TextView live_class;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_class);
        live_class = findViewById(R.id.live_class);

        Bundle extra = getIntent().getExtras(); // receive data coming from intent
        String text = extra.getString("LiveClass");

        live_class.setText(text + " Live Class");   // set the text according to the incoming data
    }
}