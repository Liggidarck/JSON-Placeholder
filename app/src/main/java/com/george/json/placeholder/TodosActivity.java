package com.george.json.placeholder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.todos.Todo;
import com.george.json.placeholder.API.todos.TodosAdapter;
import com.george.json.placeholder.databinding.ActivityTodosBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodosActivity extends AppCompatActivity {

    private static final String TAG = "TodosActivity";
    ActivityTodosBinding todosBinding;

    ArrayList<Todo> todoArrayList;
    TodosAdapter todosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todosBinding = ActivityTodosBinding.inflate(getLayoutInflater());
        setContentView(todosBinding.getRoot());

        todosBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        todosBinding.todosRecycler.setLayoutManager(new LinearLayoutManager(this));
        todosBinding.todosRecycler.setItemAnimator(new DefaultItemAnimator());
        getAllTodos();
    }

    private void getAllTodos() {

        NetworkService
                .getInstance()
                .getApi()
                .getAllTodos()
                .enqueue(new Callback<List<Todo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                        assert response.body() != null;
                        todoArrayList = new ArrayList<>(response.body());
                        todosAdapter = new TodosAdapter(TodosActivity.this, todoArrayList, (todo, position) -> {
                            Toast.makeText(TodosActivity.this, "Position:" + todo.getId(), Toast.LENGTH_SHORT).show();
                        });
                        todosBinding.todosRecycler.setAdapter(todosAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! "+ t.getMessage());
                    }
                });

    }


}