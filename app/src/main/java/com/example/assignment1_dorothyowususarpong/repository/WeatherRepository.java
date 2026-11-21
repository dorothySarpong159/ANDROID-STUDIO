package com.example.assignment1_dorothyowususarpong.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.assignment1_dorothyowususarpong.model.CityLocation;
import com.example.assignment1_dorothyowususarpong.model.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WeatherRepository {

    private static final String TAG = "WeatherRepository";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = "58f5c59c680a4a09ac3161800262011";

    private final OkHttpClient client = new OkHttpClient();

    public interface WeatherCallback {
        void onSuccess(WeatherData data);
        void onError(String message);
    }

    public List<CityLocation> getDefaultLocations() {
        Log.i(TAG, "method is reachable");
        List<CityLocation> defaults = new ArrayList<>();
        defaults.add(new CityLocation("Toronto", "Ontario, Canada", 43.6532, -79.3832));
        defaults.add(new CityLocation("Montreal", "Quebec, Canada", 45.5017, -73.5673));
        defaults.add(new CityLocation("Barrie", "Ontario, Canada", 44.3894, -79.6903));
        defaults.add(new CityLocation("Accra", "Greater Accra, Ghana", 5.6037, -0.1870));
        defaults.add(new CityLocation("London", "Greater London, UK", 51.5072, -0.1276));
        return defaults;
    }

    public Call fetchCurrentWeather(String cityName, WeatherCallback callback) {
        Log.i(TAG, "method is reachable");

        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("key", API_KEY)
                .addQueryParameter("q", cityName)
                .addQueryParameter("aqi", "no")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (!call.isCanceled() && callback != null) {
                    callback.onError("Network error: " + e.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody body = response.body()) {
                    if (!response.isSuccessful() || body == null) {
                        if (callback != null) {
                            callback.onError("API error (HTTP " + response.code() + ")");
                        }
                        return;
                    }

                    WeatherData weatherData = parseWeatherJson(body.string());
                    if (callback != null) {
                        callback.onSuccess(weatherData);
                    }
                } catch (JSONException e) {
                    if (callback != null) {
                        callback.onError("Failed to parse weather data");
                    }
                }
            }
        });

        return call;
    }

    private WeatherData parseWeatherJson(String jsonString) throws JSONException {
        JSONObject root = new JSONObject(jsonString);
        JSONObject locationObj = root.getJSONObject("location");
        JSONObject currentObj = root.getJSONObject("current");
        JSONObject conditionObj = currentObj.getJSONObject("condition");

        String locationName = locationObj.optString("name", "Unknown");
        String region = locationObj.optString("region", "");
        double tempC = currentObj.optDouble("temp_c", 0.0);
        double tempF = currentObj.optDouble("temp_f", 0.0);
        String conditionText = conditionObj.optString("text", "");
        double windKph = currentObj.optDouble("wind_kph", 0.0);
        double feelsLikeC = currentObj.optDouble("feels like_c", 0.0);
        double feelsLikeF = currentObj.optDouble("feels like_f", 0.0);

        return new WeatherData(
                locationName,
                region,
                tempC,
                tempF,
                conditionText,
                windKph,
                feelsLikeC,
                feelsLikeF
        );
    }
}