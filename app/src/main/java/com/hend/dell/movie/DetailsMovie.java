package com.hend.dell.movie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

;

public class DetailsMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ImageView posterImageIv = findViewById(R.id.poster_movie_iv);
        TextView titleTv = findViewById(R.id.originial_title);
        TextView overviewTv = findViewById(R.id.overview);
        TextView releaseDateTv = findViewById(R.id.releaseDate);
        TextView userRatingTv = findViewById(R.id.user_rating);
        Intent intent = getIntent();
        String TITLE = "title";
        titleTv.setText(intent.getStringExtra(TITLE));
        Picasso.get().load(intent.getData()).placeholder(R.drawable.error).into(posterImageIv);
        String OVERVIEW = "overview";
        overviewTv.setText(intent.getStringExtra(OVERVIEW));
        String RELEASE_DATE = "releaseDate";
        releaseDateTv.setText(intent.getStringExtra(RELEASE_DATE));
        String USER_RATING = "userRating";
        userRatingTv.setText(String.valueOf(intent.getDoubleExtra(USER_RATING, 0.0)));
    }
}
