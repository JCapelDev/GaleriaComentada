package com.capel.galery;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    private ArrayList<String> programCommentList;
    private ArrayList<String> images;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            commentView = (TextView) view.findViewById(R.id.textView1);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    public CustomAdapter(Context context, ArrayList<String> programCommentList, ArrayList<String> images){
        this.context = context;
        this.programCommentList = programCommentList;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.commentView.setText(programCommentList.get(position).replaceAll("\\s+"," "));
        holder.imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(context.getResources(),context.getResources().getIdentifier(images.get(position).replaceAll("\\s+",""),"drawable",context.getPackageName())),200,200));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
