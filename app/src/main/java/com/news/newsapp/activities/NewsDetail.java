package com.news.newsapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    String TITLE, SOURCE,DATE,DESCRIPTION, AUTHOR, url, imageUrl, publishedAt;
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
        TITLE = intent.getStringExtra("title");
        SOURCE = intent.getStringExtra("source");
        DATE = intent.getStringExtra("time");
        DESCRIPTION = intent.getStringExtra("description");
        AUTHOR = intent.getStringExtra("author");
        url = intent.getStringExtra("url");
        imageUrl = intent.getStringExtra("imageUrl");
        publishedAt = intent.getStringExtra("publishedAd");

        title.setText(TITLE);
        source.setText(publishedAt);
        date.setText(DATE);
        description.setText(DESCRIPTION);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        Picasso.get().load(imageUrl).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share){
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, TITLE);
                String body = TITLE + " \n " + url + " \n ";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "Share via"));
            }
            catch (Exception e){
                Toast.makeText(NewsDetail.this, "Can't share at the moment. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}