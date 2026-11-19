package com.example.assignment1_dorothyowususarpong;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment1_dorothyowususarpong.databinding.ActivityWeatherDetailBinding;

public class WeatherDetailActivity extends AppCompatActivity {

    private ActivityWeatherDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve passed city data
        String cityName = getIntent().getStringExtra("CITY_NAME");
        if (cityName == null) {
            cityName = "London";
        }

        binding.tvDetailCity.setText(cityName);

        // Display hardcoded values based on selected city (will be replaced by live API data in Assignment 2)
        if (cityName.contains("Toronto")) {
            binding.tvDetailTemp.setText("22°C (71.6°F)");
            binding.tvDetailCondition.setText("Condition: Sunny | Wind: 15 kph | Humidity: 50%");
        } else if (cityName.contains("Tokyo")) {
            binding.tvDetailTemp.setText("28°C (82.4°F)");
            binding.tvDetailCondition.setText("Condition: Clear | Wind: 10 kph | Humidity: 45%");
        } else if (cityName.contains("Sydney")) {
            binding.tvDetailTemp.setText("20°C (68.0°F)");
            binding.tvDetailCondition.setText("Condition: Sunny | Wind: 18 kph | Humidity: 55%");
        } else if (cityName.contains("New York")) {
            binding.tvDetailTemp.setText("11°C (51.8°F)");
            binding.tvDetailCondition.setText("Condition: Rainy | Wind: 22 kph | Humidity: 80%");
        } else {
            binding.tvDetailTemp.setText("14°C (57.2°F)");
            binding.tvDetailCondition.setText("Condition: Cloudy | Wind: 12 kph | Humidity: 78%");
        }

        if (binding.btnBack != null) {
            binding.btnBack.setOnClickListener(v -> finish());
        }
    }
}