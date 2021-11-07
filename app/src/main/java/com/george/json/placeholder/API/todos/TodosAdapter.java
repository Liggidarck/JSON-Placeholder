package com.george.json.placeholder.API.todos;

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

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Todo> todoArrayList;
    private final customClickItem customClickItem;

    public TodosAdapter(Context context, ArrayList<Todo> todoArrayList, customClickItem customClickItem){
        this.context = context;
        this.todoArrayList = todoArrayList;
        this.customClickItem = customClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> {
            customClickItem.onItemClick(todoArrayList.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
        });

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TitleTextViewTodoItem.setText(todoArrayList.get(position).getTitle());
        holder.CompletedTextViewTodoItem.setText("Comp: " + todoArrayList.get(position).isCompleted());
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TitleTextViewTodoItem;
        public TextView CompletedTextViewTodoItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleTextViewTodoItem = itemView.findViewById(R.id.TitleTextViewTodoItem);
            CompletedTextViewTodoItem = itemView.findViewById(R.id.CompletedTextViewTodoItem);
        }
    }

    public interface customClickItem {

        void onItemClick(Todo todo, int position);

    }

}
