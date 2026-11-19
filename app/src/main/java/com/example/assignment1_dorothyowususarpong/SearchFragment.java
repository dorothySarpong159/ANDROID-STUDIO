package com.example.assignment1_dorothyowususarpong;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find all 5 city views by finding their parent or layout IDs
        int[] cityViewIds = new int[]{
                R.id.cityLondon, R.id.cityToronto, R.id.cityTokyo, R.id.citySydney, R.id.cityNewYork
        };

        String[] cityNames = new String[]{
                "London", "Toronto", "Tokyo", "Sydney", "New York"
        };

        for (int i = 0; i < cityViewIds.length; i++) {
            View cityView = view.findViewById(cityViewIds[i]);
            if (cityView != null) {
                final String name = cityNames[i];
                cityView.setOnClickListener(v -> openDetailScreen(name));
            }
        }
    }

    private void openDetailScreen(String cityName) {
        Intent intent = new Intent(requireContext(), WeatherDetailActivity.class);
        intent.putExtra("CITY_NAME", cityName);
        startActivity(intent);
    }
}