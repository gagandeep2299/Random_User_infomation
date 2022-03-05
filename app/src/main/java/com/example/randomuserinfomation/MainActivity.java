package com.example.randomuserinfomation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue rq = Volley.newRequestQueue(this);
        TextView t1 = findViewById(R.id.textbox);
        TextView mail = findViewById(R.id.textView);
        TextView gender = findViewById(R.id.textView2);
        TextView phone = findViewById(R.id.textView4);
        TextView location = findViewById(R.id.textView3);
        ImageView i = findViewById(R.id.imageView);
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.GET, "https://randomuser.me/api/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsa = response.getJSONArray("results");
                    t1.setText(jsa.getJSONObject(0).getJSONObject("name").getString("title") + " " +
                            jsa.getJSONObject(0).getJSONObject("name").getString("first") + " " +
                            jsa.getJSONObject(0).getJSONObject("name").getString("last"));
                    mail.setText(jsa.getJSONObject(0).getString("email"));
                    gender.setText(jsa.getJSONObject(0).getString("gender"));
                    phone.setText(jsa.getJSONObject(0).getString("phone"));
                    location.setText(jsa.getJSONObject(0).getJSONObject("location").getString("city") + " " +
                            jsa.getJSONObject(0).getJSONObject("location").getString("state") + " " +
                            jsa.getJSONObject(0).getJSONObject("location").getString("country") + " " +
                            jsa.getJSONObject(0).getJSONObject("location").getString("postcode"));
                    Picasso.with(MainActivity.this).load(jsa.getJSONObject(0).getJSONObject("picture").getString("large")).
                            placeholder(R.drawable.grid).into(i);
                } catch (JSONException e) {
                    Log.d("mydata", e.toString());

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("mydata", "sorry :(");

                    }
                });
        rq.add(js);
    }




}