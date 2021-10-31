package com.george.json.placeholder.API.albums;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.george.json.placeholder.R;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;
    onItemClickListener clickListener;

    public AlbumsAdapter(Context context, ArrayList<Album> albumArrayList, onItemClickListener clickListener) {
        this.context = context;
        this.albumArrayList = albumArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> clickListener.itemClick(albumArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition()));

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.ViewHolder holder, int position) {
        holder.albumTitleView.setText(albumArrayList.get(position).getTitle());
        holder.albumIdView.setText("ID: " + albumArrayList.get(position).getId());
        holder.albumUserId.setText("UserId: " + albumArrayList.get(position).getUserId());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView albumTitleView;
        public TextView albumIdView;
        public TextView albumUserId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            albumTitleView = itemView.findViewById(R.id.albumTitleView);
            albumIdView = itemView.findViewById(R.id.albumIdView);
            albumUserId = itemView.findViewById(R.id.albumUserId);
        }
    }

    public interface onItemClickListener{
        void itemClick(Album album, int position);
    }

}
