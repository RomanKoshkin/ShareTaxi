package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Activity_3 extends BaseActivity {
    static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    static String Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // СОЗДАЁМ ЭЛЕМЕНТЫ ТОЛЬКО ПОСЛЕ СОЗДАНИЯ САМОЙ АКТИВНОСТИ!
        final TextView tv = findViewById(R.id.textView2);
        tv.setText("Awesome!");

        postRequest("Раз", "Два"); // при нажатии кнопки вызывается postRequest из этого же класса
        tv.setText("Готово! Скоро мы найдём вам попутчика!");
    }

    private void postRequest(String reqtxt1, String reqtxt2){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://nightdude.pythonanywhere.com/order"; //"https://hookb.in/Mq1YPKXGMqfmJlBlOq8w";  //
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("city", City); //City
            jsonBody.put("country", Country);//Country
            jsonBody.put("Destination", DestinationPoint);//DestinationPoint
            jsonBody.put("desiredGMT", DesiredDate);//DesiredDate
            jsonBody.put("time_sent", TimeSubmitted);//TimeSubmitted
            jsonBody.put("AppID", AppID); //AppID


            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                // mRequestBody contains data to be sent to the server
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                String decodeUTF8(byte[] bytes) {
                    return new String(bytes, UTF8_CHARSET);
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Count = decodeUTF8(response.data);

                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
