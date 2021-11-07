package com.george.json.placeholder.API.users;

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
import java.util.Arrays;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<User> userArrayList;
    customClickItem customClickItem;

    public UserAdapter(Context context, ArrayList<User> users, customClickItem customClickItem) {
        this.context = context;
        this.userArrayList = users;
        this.customClickItem = customClickItem;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> customClickItem.itemUserClick(userArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition()));

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.UserTextViewId.setText("ID: " + userArrayList.get(position).getId());
        holder.UserTextViewName.setText(userArrayList.get(position).getName());
        holder.UserTextViewUsername.setText(userArrayList.get(position).getUsername());
        holder.UserTextViewEmail.setText(userArrayList.get(position).getEmail());
        holder.UserTextViewPhone.setText(userArrayList.get(position).getPhone());
        holder.UserTextViewWebsite.setText(userArrayList.get(position).getWebsite());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView UserTextViewId;
        public TextView UserTextViewName;
        public TextView UserTextViewUsername;
        public TextView UserTextViewEmail;
        public TextView UserTextViewAddress;
        public TextView UserTextViewPhone;
        public TextView UserTextViewWebsite;
        public TextView UserTextViewCompany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            UserTextViewId = itemView.findViewById(R.id.UserTextViewId);
            UserTextViewName = itemView.findViewById(R.id.UserTextViewName);
            UserTextViewUsername = itemView.findViewById(R.id.UserTextViewUsername);
            UserTextViewEmail = itemView.findViewById(R.id.UserTextViewEmail);
            UserTextViewAddress = itemView.findViewById(R.id.UserTextViewAddress);
            UserTextViewPhone = itemView.findViewById(R.id.UserTextViewPhone);
            UserTextViewWebsite = itemView.findViewById(R.id.UserTextViewWebsite);
            UserTextViewCompany = itemView.findViewById(R.id.UserTextViewCompany);
        }

    }

    public interface customClickItem {
        void itemUserClick(User user, int position);
    }


}
