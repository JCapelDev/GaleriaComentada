package com.capel.galery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private String[] items;
    private int[] programImages = {
            R.drawable.h6jJMYjltWk,
            R.drawable.jadMxMbKbiQ,
            R.drawable.Jt4ABNbETW4,
            R.drawable.oGTvZCs3GhI,
            R.drawable.u1Re1UAer7g,
            R.drawable.u7XAOL8N_3U,
            R.drawable.wrr6bqkJiD0,
            R.drawable.XXt6wwDWNEM
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImageView().setImageResource(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
