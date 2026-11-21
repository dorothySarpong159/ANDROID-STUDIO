package com.example.assignment1_dorothyowususarpong.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment1_dorothyowususarpong.WeatherDetailActivity;
import com.example.assignment1_dorothyowususarpong.databinding.FragmentSearchBinding;
import com.example.assignment1_dorothyowususarpong.model.CityLocation;
import com.example.assignment1_dorothyowususarpong.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements LocationClickListener {

    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding binding;
    private WeatherViewModel weatherViewModel;
    private LocationAdapter locationAdapter;
    private final List<CityLocation> locationList = new ArrayList<>();
    private String selectedCityName = "";

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "method is reachable");

        setupRecyclerView();
        setupViewModel();
        setupSearchFilter();
    }

    private void setupRecyclerView() {
        locationAdapter = new LocationAdapter(locationList);
        locationAdapter.setClickListener(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(locationAdapter);
    }

    private void setupViewModel() {
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        weatherViewModel.getLocations().observe(getViewLifecycleOwner(), updatedLocations -> {
            locationList.clear();
            if (updatedLocations != null) {
                locationList.addAll(updatedLocations);
            }
            locationAdapter.notifyDataSetChanged();
        });

        weatherViewModel.getWeatherData().observe(getViewLifecycleOwner(), data -> {
            if (data != null && getContext() != null) {
                Intent intent = new Intent(requireContext(), WeatherDetailActivity.class);
                intent.putExtra("CITY_NAME", selectedCityName);
                intent.putExtra("weatherData", data);
                startActivity(intent);
                weatherViewModel.clearWeatherData();
            }
        });

        weatherViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty() && getContext() != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSearchFilter() {
        binding.searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weatherViewModel.filterLocations(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onLocationClick(View view, int position, CityLocation location) {
        Log.i(TAG, "method is reachable");
        selectedCityName = location.getName();
        weatherViewModel.fetchWeather(location.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}