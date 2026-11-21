package com.example.assignment1_dorothyowususarpong.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1_dorothyowususarpong.databinding.ItemLocationRowBinding;
import com.example.assignment1_dorothyowususarpong.model.CityLocation;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private final List<CityLocation> locationList;
    private LocationClickListener clickListener;

    public LocationAdapter(List<CityLocation> locationList) {
        this.locationList = locationList;
    }

    public void setClickListener(LocationClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLocationRowBinding rowBinding = ItemLocationRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new LocationViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        CityLocation location = locationList.get(position);
        holder.binding.tvCityName.setText(location.getName());
        holder.binding.tvRegionCoordinates.setText(location.getRegion());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        private final ItemLocationRowBinding binding;

        public LocationViewHolder(@NonNull ItemLocationRowBinding rowBinding) {
            super(rowBinding.getRoot());
            this.binding = rowBinding;

            itemView.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onLocationClick(v, pos, locationList.get(pos));
                }
            });
        }
    }
}