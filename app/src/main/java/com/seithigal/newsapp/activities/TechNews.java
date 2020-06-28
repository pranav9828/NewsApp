package com.seithigal.newsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.seithigal.newsapp.R;
import com.seithigal.newsapp.adapters.Adapter;
import com.seithigal.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TechNews extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    private String url = "https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json";
    private JsonObjectRequest jsonObjectRequest;
    private List<News> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_news);

        getSupportActionBar().setTitle("Technology News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.news);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        newsList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);

        fetchData();
    }

    private void fetchData() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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
                        newsList.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);
                Adapter adapter = new Adapter(TechNews.this, newsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(TechNews.this));
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}