package com.example.assignment1_dorothyowususarpong;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_dorothyowususarpong.databinding.ActivityWeatherDetailBinding;
import com.example.assignment1_dorothyowususarpong.model.WeatherData;

import java.util.Locale;

public class WeatherDetailActivity extends AppCompatActivity {

    private static final String TAG = "WeatherDetailActivity";
    private ActivityWeatherDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(TAG, "method is reachable");

        String cityName = getIntent().getStringExtra("CITY_NAME");
        WeatherData weatherData = getIntent().getSerializableExtra("weatherData", WeatherData.class);

        if (weatherData != null) {
            String displayCity = (cityName != null && !cityName.isEmpty())
                    ? cityName
                    : weatherData.getLocationName();

            if (weatherData.getRegion() != null && !weatherData.getRegion().isEmpty()) {
                displayCity += ", " + weatherData.getRegion();
            }

            binding.tvDetailCity.setText(displayCity);

            String tempText = String.format(
                    Locale.getDefault(),
                    "%.1f°C (%.1f°F)",
                    weatherData.getTempC(),
                    weatherData.getTempF()
            );
            binding.tvDetailTemp.setText(tempText);

            String detailsText = String.format(
                    Locale.getDefault(),
                    "Condition: %s | Wind: %.1f kph | Feels Like: %.1f°C (%.1f°F)",
                    weatherData.getConditionText(),
                    weatherData.getWindKph(),
                    weatherData.getFeelsLikeC(),
                    weatherData.getFeelsLikeF()
            );
            binding.tvDetailCondition.setText(detailsText);
        } else {
            binding.tvDetailCity.setText(cityName != null ? cityName : "Unknown Location");
            binding.tvDetailTemp.setText("--°C (--°F)");
            binding.tvDetailCondition.setText("Weather data unavailable");
        }

        if (binding.btnBack != null) {
            binding.btnBack.setOnClickListener(v -> finish());
        }
    }
}