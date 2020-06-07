package com.news.newsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.news.newsapp.R;
import com.news.newsapp.adapters.Adapter;
import com.news.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://earnezy.in/android_shop/newsapi2.php";
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private List<News> newsList;
    private RecyclerView recyclerView;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news);
        newsList = new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();
        jsonRequest();
    }

    private void jsonRequest() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL,null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray articles = response.optJSONArray("articles");
                JSONObject jsonObject = null;

                for (int i=0;i<articles.length();i++){
                    try {
                        jsonObject = articles.getJSONObject(i);
                        News news = new News();
                        news.setAuthor(jsonObject.getString("author"));
                        news.setDescription(jsonObject.getString("description"));
                        news.setTitle(jsonObject.getString("title"));
                        news.setPublishedAt(jsonObject.getString("publishedAt"));
                        news.setUrlToImage(jsonObject.getString("urlToImage"));
                        news.setUrl(jsonObject.getString("url"));
//                        news.setSource(jsonObject.getString("name"));
                        newsList.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                pd.dismiss();
                setUpRecyclerView(newsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    private void setUpRecyclerView(List<News> newsList) {
        Adapter adapter = new Adapter(MainActivity.this, newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}