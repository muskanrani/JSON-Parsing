package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  // TextView textview;
    RequestQueue requestQueue;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> post_title = new ArrayList<String>();
    ArrayList<String> post_date = new ArrayList<String>();
    ArrayList<String> post_comment = new ArrayList<String>();
    ArrayList<String> post_author = new ArrayList<String>();
    ArrayList<String> post_description = new ArrayList<String>();
    ArrayList<String> post_image = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   //     textview=findViewById(R.id.textview);
        requestQueue = Volley.newRequestQueue(this);
        fetchdata();
    }

    private void fetchdata() {
        String url="http://smartres.in/api/get_recent_posts/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    for (int i = 0; i < 2; i++) {

                        post_title.add(response.getJSONArray("posts").getJSONObject(i).getString("title"));

                    }

                    for (int i = 0; i < 2; i++) {

                        post_date.add(response.getJSONArray("posts").getJSONObject(i).getString("date"));

                    }

                    for (int i = 0; i < 2; i++) {

                        post_author.add(response.getJSONArray("posts").getJSONObject(i).getJSONObject("author").getString("name"));

                    }


                    for (int i = 0; i < 2; i++) {

                        Document document= Jsoup.parse(response.getJSONArray("posts").getJSONObject(i).getString("content"));
                        post_description.add(document.text());

                    }

                    for (int i = 0; i < 2; i++) {

                        post_image.add(response.getJSONArray("posts").getJSONObject(i).getJSONArray("attachments").getJSONObject(0).getString("url"));

                    }

                    initRecyclerView();
             //     textview.setText(post_image.toString());
                }
                catch (JSONException e) {
                        e.printStackTrace();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new DataDisplayAdapter(this,post_title,post_date,post_image,post_description,post_author);
        recyclerView.setAdapter(adapter);
    }
}