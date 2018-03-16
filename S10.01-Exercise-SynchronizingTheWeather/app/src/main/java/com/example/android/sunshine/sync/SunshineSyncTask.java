package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

//  TODO (1) Create a class called SunshineSyncTask
//  TODO (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      TODO (3) Within syncWeather, fetch new weather data
//      TODO (4) If we have valid results, delete the old data and insert the new
public class SunshineSyncTask {

    synchronized public static void syncWeather(Context context) {

        URL weatherRequestUrl = NetworkUtils.getUrl(context);

        try {
            String jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl);

            ContentValues[] simpleJsonWeatherData = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, jsonWeatherResponse);


            if (simpleJsonWeatherData != null && simpleJsonWeatherData.length > 0) {
                ContentResolver weatherDataResolver = context.getContentResolver();

                weatherDataResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI, null, null);

                weatherDataResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, simpleJsonWeatherData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}