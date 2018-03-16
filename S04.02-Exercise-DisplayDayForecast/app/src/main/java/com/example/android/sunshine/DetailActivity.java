package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent i = getIntent();
        TextView weather = (TextView) findViewById(R.id.detail_weather_data);

        if (i.hasExtra(Intent.EXTRA_TEXT)) {

            weather.setText(i.getStringExtra(Intent.EXTRA_TEXT));
        } else {
            weather.setText("It's gonna be great");
        }
    }
}