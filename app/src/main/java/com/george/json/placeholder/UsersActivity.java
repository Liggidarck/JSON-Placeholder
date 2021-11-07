package com.george.json.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.users.User;
import com.george.json.placeholder.API.users.UserAdapter;
import com.george.json.placeholder.databinding.ActivityUsersBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private static final String TAG = "UsersActivity";
    ActivityUsersBinding usersBinding;

    ArrayList<User> userArrayList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersBinding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(usersBinding.getRoot());

        usersBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        usersBinding.UsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersBinding.UsersRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllUsers();

    }

    private void getAllUsers() {
        NetworkService
                .getInstance()
                .getApi()
                .getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        assert response.body() != null;
                        userArrayList = new ArrayList<>(response.body());
                        adapter = new UserAdapter(UsersActivity.this, userArrayList, (user, position) -> {
                            Toast.makeText(UsersActivity.this, "Position: " + user.getId(), Toast.LENGTH_SHORT).show();
                        });
                        usersBinding.UsersRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! " + t.getMessage());
                    }
                });
    }
}