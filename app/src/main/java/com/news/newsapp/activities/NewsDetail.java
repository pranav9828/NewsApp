package com.news.newsapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.news.newsapp.R;
import com.news.newsapp.model.News;
import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {

    TextView title, source, date, description;
    WebView webView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = (TextView) findViewById(R.id.title);
        source = (TextView) findViewById(R.id.source);
        date = (TextView) findViewById(R.id.date);
        description = (TextView) findViewById(R.id.description);
        webView = (WebView) findViewById(R.id.webView);
        imageView = (ImageView) findViewById(R.id.image);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String TITLE = intent.getStringExtra("title");
        String SOURCE = intent.getStringExtra("source");
        String DATE = intent.getStringExtra("time");
        String DESCRIPTION = intent.getStringExtra("description");
        String AUTHOR = intent.getStringExtra("author");
        String url = intent.getStringExtra("url");
        String imageUrl = intent.getStringExtra("imageUrl");
        String publishedAt = intent.getStringExtra("publishedAd");

        title.setText(TITLE);
        source.setText(publishedAt);
        date.setText(DATE);
        description.setText(DESCRIPTION);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}