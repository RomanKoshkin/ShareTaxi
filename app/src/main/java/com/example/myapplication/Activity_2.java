package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Activity_2 extends BaseActivity {
    private static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
//    private static String DesiredTime;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        TimeSubmitted=java.util.Calendar.getInstance().getTime().toString();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.textView);
        textView.setText("Когда Вам нужно такси?");

        final CalendarView cv = findViewById(R.id.calendarView);
        Button button = findViewById(R.id.button);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                textView.setText("Super!");
                String Year = String.valueOf(year);
                String Month = String.valueOf(month+1);
                String Day = String.valueOf(dayOfMonth);
                DesiredDate = Day + "." + Month + "." + Year; // DesiredTime is a protected static String from the BaseClass, which in turn extends AppCompatActivity
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveOn(textView);
            }
        });

    }

    private void MoveOn(View view) {
        Intent intent = new Intent(this, Activity_3.class);
        String message = "Congratulations!";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
