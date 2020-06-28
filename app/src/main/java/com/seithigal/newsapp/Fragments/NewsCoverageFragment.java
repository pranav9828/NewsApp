package com.seithigal.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.seithigal.newsapp.R;
import com.seithigal.newsapp.activities.BusinessNews;
import com.seithigal.newsapp.activities.EntertainmentNews;
import com.seithigal.newsapp.activities.GeneralNews;
import com.seithigal.newsapp.activities.HealthNews;
import com.seithigal.newsapp.activities.ScienceNews;
import com.seithigal.newsapp.activities.SportsNews;
import com.seithigal.newsapp.activities.TechNews;

public class NewsCoverageFragment extends Fragment {

    CardView business_card, entertainment_card, general_card, health_card, science_card, sports_card, tech_card;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_coverage, container, false);
        business_card = view.findViewById(R.id.business_card);
        entertainment_card = view.findViewById(R.id.entertainment_card);
        general_card = view.findViewById(R.id.general_card);
        health_card = view.findViewById(R.id.health_card);
        science_card = view.findViewById(R.id.science_card);
        sports_card = view.findViewById(R.id.sports_card);
        tech_card = view.findViewById(R.id.tech_card);

        business_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BusinessNews.class);
                startActivity(intent);
            }
        });

        entertainment_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EntertainmentNews.class);
                startActivity(intent);
            }
        });

        general_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GeneralNews.class);
                startActivity(intent);
            }
        });

        health_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HealthNews.class);
                startActivity(intent);
            }
        });

        science_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScienceNews.class);
                startActivity(intent);
            }
        });

        sports_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SportsNews.class);
                startActivity(intent);
            }
        });

        tech_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TechNews.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
