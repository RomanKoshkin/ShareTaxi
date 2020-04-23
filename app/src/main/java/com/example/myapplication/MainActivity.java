package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // DECLARATIONS

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView post_response_text1;
    private TextView post_response_text2;
    private HashMap<String, String> params = new HashMap<String, String>();
    static String Count;
    protected static String DesiredTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
            setTheme(R.style.AppTheme);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtview0;
        txtview0 = findViewById(R.id.textView0);
        MoveOn(txtview0);
    }

    private void MoveOn(View view) {
        Intent intent = new Intent(this, Activity_2.class);
        String message = "Congratulations!";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}



