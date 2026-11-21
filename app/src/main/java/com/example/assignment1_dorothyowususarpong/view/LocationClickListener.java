package com.example.assignment1_dorothyowususarpong.view;

import android.view.View;
import com.example.assignment1_dorothyowususarpong.model.CityLocation;

public interface LocationClickListener {
    void onLocationClick(View view, int position, CityLocation location);
}