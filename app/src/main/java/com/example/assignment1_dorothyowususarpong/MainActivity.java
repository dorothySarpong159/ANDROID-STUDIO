package com.example.assignment1_dorothyowususarpong;

import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.assignment1_dorothyowususarpong.databinding.ActivityMainBinding;
import com.example.assignment1_dorothyowususarpong.view.SavedFragment;
import com.example.assignment1_dorothyowususarpong.view.SearchFragment;
import com.example.assignment1_dorothyowususarpong.view.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Dark/Light Mode Switch Listener
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        // Load SearchFragment on startup
        if (savedInstanceState == null) {
            loadFragment(new SearchFragment());
        }

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_search) {
                selected = new SearchFragment();
            } else if (itemId == R.id.nav_saved) {
                selected = new SavedFragment();
            } else if (itemId == R.id.nav_settings) {
                selected = new SettingsFragment();
            }

            if (selected != null) {
                loadFragment(selected);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}