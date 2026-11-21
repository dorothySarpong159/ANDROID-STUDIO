package com.example.assignment1_dorothyowususarpong.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment1_dorothyowususarpong.model.CityLocation;
import com.example.assignment1_dorothyowususarpong.model.WeatherData;
import com.example.assignment1_dorothyowususarpong.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class WeatherViewModel extends ViewModel {

    private static final String TAG = "WeatherViewModel";

    private final WeatherRepository repository = new WeatherRepository();

    private final MutableLiveData<List<CityLocation>> locations = new MutableLiveData<>();
    private final MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    private Call weatherCall;

    public WeatherViewModel() {
        initSearchDiagnostics();
        locations.setValue(repository.getDefaultLocations());
    }

    private void initSearchDiagnostics() {
        Log.i(TAG, "search diagnostics enabled");
    }

    public LiveData<List<CityLocation>> getLocations() {
        return locations;
    }

    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void filterLocations(String query) {
        List<CityLocation> all = repository.getDefaultLocations();
        if (query == null || query.trim().isEmpty()) {
            locations.setValue(all);
            return;
        }
        String lower = query.trim().toLowerCase();
        List<CityLocation> filtered = new ArrayList<>();
        for (CityLocation city : all) {
            if (city.getName().toLowerCase().contains(lower)
                    || city.getRegion().toLowerCase().contains(lower)) {
                filtered.add(city);
            }
        }
        locations.setValue(filtered);
    }

    public void fetchWeather(String cityName) {
        Log.i(TAG, "method is reachable");

        if (weatherCall != null && !weatherCall.isCanceled()) {
            weatherCall.cancel();
        }

        isLoading.postValue(true);
        errorMessage.postValue(null);

        weatherCall = repository.fetchCurrentWeather(cityName, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(WeatherData data) {
                isLoading.postValue(false);
                weatherData.postValue(data);
            }

            @Override
            public void onError(String message) {
                isLoading.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }

    public void clearWeatherData() {
        weatherData.setValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (weatherCall != null && !weatherCall.isCanceled()) {
            weatherCall.cancel();
        }
    }
}