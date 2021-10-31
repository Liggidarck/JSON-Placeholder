package com.george.json.placeholder.API.comments;

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

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    Context context;
    ArrayList<Comment> commentArrayList;
    onItemClickListener clickListener;

    public CommentsAdapter(Context context, ArrayList<Comment> commentArrayList, onItemClickListener clickListener) {
        this.context = context;
        this.commentArrayList = commentArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> {
            clickListener.onCommentClick(commentArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
        });

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        holder.commentNameView.setText(commentArrayList.get(position).getName());
        holder.commentBodyView.setText(commentArrayList.get(position).getBody());
        holder.commentEmailView.setText(commentArrayList.get(position).getEmail());
        holder.commentPostIdView.setText("Post id: " + commentArrayList.get(position).getPostId());
        holder.commentEmailView.setText("Id: " + commentArrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView commentNameView;
        public TextView commentBodyView;
        public TextView commentEmailView;
        public TextView commentPostIdView;
        public TextView commentIdView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentNameView = itemView.findViewById(R.id.commentNameView);
            commentBodyView = itemView.findViewById(R.id.commentBodyView);
            commentEmailView = itemView.findViewById(R.id.commentEmailView);
            commentPostIdView = itemView.findViewById(R.id.commentPostIdView);
            commentIdView = itemView.findViewById(R.id.commentIdView);
        }
    }

    public interface onItemClickListener {
        void onCommentClick(Comment comment, int position);
    }

}
