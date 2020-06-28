package com.seithigal.newsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.seithigal.newsapp.Fragments.HeadlinesFragment;
import com.seithigal.newsapp.Fragments.NewsCoverageFragment;
import com.seithigal.newsapp.Fragments.NewsstandFragment;
import com.seithigal.newsapp.R;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HeadlinesFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_headlines:
                    getSupportActionBar().setTitle("Headlines");
                    selectFragment = new HeadlinesFragment();
                    break;
                case R.id.nav_news:
                    getSupportActionBar().setTitle("Category");
                    selectFragment = new NewsCoverageFragment();
                    break;
                case R.id.nav_newstand:
                    getSupportActionBar().setTitle("Channels");
                    selectFragment = new NewsstandFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment).commit();
            return true;
        }
    };
}