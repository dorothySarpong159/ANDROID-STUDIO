package com.example.assignment1_dorothyowususarpong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Display App Version
        TextView versionText = view.findViewById(R.id.versionText);
        if (versionText != null) {
            versionText.setText("Version 1.0");
        }

        // 2. Send Feedback Intent
        LinearLayout feedbackOption = view.findViewById(R.id.feedbackOption);
        if (feedbackOption != null) {
            feedbackOption.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:support@weathernow.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "WeatherNow App Feedback");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "No email app installed", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 3. View Website / GitHub Intent
        LinearLayout websiteOption = view.findViewById(R.id.websiteOption);
        if (websiteOption != null) {
            websiteOption.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "No web browser available", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 4. Share App Intent
        LinearLayout shareOption = view.findViewById(R.id.shareOption);
        if (shareOption != null) {
            shareOption.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Check out WeatherNow, my personal weather app!");
                try {
                    startActivity(Intent.createChooser(intent, "Share App via"));
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "Sharing not available", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}