package com.seithigal.newsapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.seithigal.newsapp.R;
import com.seithigal.newsapp.activities.MainActivity;
import com.seithigal.newsapp.adapters.Adapter;
import com.seithigal.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeadlinesFragment extends Fragment {

    private final String JSON_URL = "https://earnezy.in/android_shop/newsapi2.php";
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private List<News> newsList;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView greeting;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        recyclerView = view.findViewById(R.id.news);
        progressBar = view.findViewById(R.id.progress_bar);
        newsList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        greeting = view.findViewById(R.id.greeting);

        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (hours >= 12 && hours < 16){
            greeting.setText("Good Afternoon");
        }
        else if(hours >= 16){
            greeting.setText("Good Evening");
        }
        else if (hours >= 5 && hours < 12){
            greeting.setText("Good Morning");
        }

        jsonRequest();
        return view;
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
                        news.setContent(jsonObject.getString("content"));
                        newsList.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);
                setUpRecyclerView(newsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void setUpRecyclerView(List<News> newsList) {
        Adapter adapter = new Adapter(getContext(), newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
}
