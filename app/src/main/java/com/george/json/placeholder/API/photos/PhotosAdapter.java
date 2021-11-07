package com.george.json.placeholder.API.photos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.george.json.placeholder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    Context context;
    ArrayList<Photo> photoArrayList;
    customClickItem customClickItem;

    public PhotosAdapter(Context context, ArrayList<Photo> photoArrayList, customClickItem customClickItem){
        this.context = context;
        this.photoArrayList = photoArrayList;
        this.customClickItem = customClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> customClickItem.itemClick(photoArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition()));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TitleItemPhoto.setText(photoArrayList.get(position).getTitle());
        holder.UrlItemPhoto.setText(photoArrayList.get(position).getUrl());

        Picasso
                .get()
                .load(photoArrayList.get(position).getUrl())
                .into(holder.PhotoItemImageView);

    }

    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView TitleItemPhoto;
        public TextView UrlItemPhoto;
        public ImageView PhotoItemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleItemPhoto = itemView.findViewById(R.id.TitleItemPhoto);
            UrlItemPhoto = itemView.findViewById(R.id.UrlItemPhoto);
            PhotoItemImageView = itemView.findViewById(R.id.PhotoItemImageView);
        }
    }

    public interface customClickItem{
        void itemClick(Photo photo, int position);
    }

}
