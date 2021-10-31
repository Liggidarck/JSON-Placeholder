package com.george.json.placeholder.API.photos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.george.json.placeholder.R;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    Context context;
    ArrayList<Photo> photoArrayList;
    onItemClickListener itemClickListener;

    public PhotoAdapter(Context context, ArrayList<Photo> photoArrayList, onItemClickListener itemClickListener) {
        this.context = context;
        this.photoArrayList = photoArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> itemClickListener.photoClickListener(photoArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition()));

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        holder.photoTitleView.setText(photoArrayList.get(position).getTitle());
        holder.photoUrlView.setText(photoArrayList.get(position).getUrl());
        holder.photoThumbnailUrlView.setText(photoArrayList.get(position).getThumbnailUrl());
        holder.photoIdView.setText("Id: " + photoArrayList.get(position).getId());
        holder.photoAlbumIdView.setText("AlbumId: " + photoArrayList.get(position).getAlbumId());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView photoTitleView;
        public TextView photoUrlView;
        public TextView photoThumbnailUrlView;
        public TextView photoIdView;
        public TextView photoAlbumIdView;
        public ImageView photoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoTitleView = itemView.findViewById(R.id.photoTitleView);
            photoUrlView = itemView.findViewById(R.id.photoUrlView);
            photoThumbnailUrlView = itemView.findViewById(R.id.photoThumbnailUrlView);
            photoIdView = itemView.findViewById(R.id.photoIdView);
            photoAlbumIdView = itemView.findViewById(R.id.photoAlbumIdView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }
    }

    public interface onItemClickListener {
        void photoClickListener(Photo photo, int position);
    }

}
