package com.example.groupworksheffxplore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class WestonParkPage extends AppCompatActivity {

    //initialized vairables
    private RatingBar ratingBarYours;
    private RatingBar ratingBarAll;

    private Button buttonSubmit;
    private TextView textViewYourCurrentRating;
    private TextView textViewRatingCount;
    private TextView textViewSumAllRating;
    private TextView textViewAverageAllRating;

    private List<Float> allRatings = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weston_park_info);

    }


}

