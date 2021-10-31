package com.george.json.placeholder.API.posts;

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

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Post> postArrayList;
    private final itemClickListener itemClickListener;

    public PostsAdapter(Context context, ArrayList<Post> postArrayList, itemClickListener itemClickListener) {
        this.context = context;
        this.postArrayList = postArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> {
            itemClickListener.onPostItemClick(postArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
        });

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.postTitleView.setText(postArrayList.get(position).getTitle());
        holder.postBodyView.setText(postArrayList.get(position).getBody());
        holder.postUserIdView.setText("UserId: " + postArrayList.get(position).getUserId());
        holder.postIdView.setText("Id: " + postArrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView postTitleView;
        public TextView postBodyView;
        public TextView postUserIdView;
        public TextView postIdView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitleView = itemView.findViewById(R.id.postTitleView);
            postBodyView = itemView.findViewById(R.id.postBodyView);
            postUserIdView = itemView.findViewById(R.id.postUserIdView);
            postIdView = itemView.findViewById(R.id.postIdView);
        }
    }

    public interface itemClickListener {

        void onPostItemClick(Post post, int position);

    }

}
