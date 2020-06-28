package com.seithigal.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.seithigal.newsapp.R;
import com.seithigal.newsapp.activities.bbcNews;
import com.seithigal.newsapp.activities.cnnNews;
import com.seithigal.newsapp.activities.foxNews;

public class NewsstandFragment extends Fragment {

    ImageView bbc, cnn, fox;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsstand, container, false);
        bbc = view.findViewById(R.id.bbc);
        cnn = view.findViewById(R.id.cnn);
        fox = view.findViewById(R.id.fox);

        bbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), bbcNews.class);
                startActivity(intent);
            }
        });

        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cnnNews.class);
                startActivity(intent);
            }
        });

        fox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), foxNews.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
