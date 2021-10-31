package com.george.json.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.posts.Post;
import com.george.json.placeholder.API.posts.PostsAdapter;
import com.george.json.placeholder.databinding.ActivityPostsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {

    ActivityPostsBinding postsBinding;
    private static final String TAG = "PostActivity";

    ArrayList<Post> postList;
    PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsBinding = ActivityPostsBinding.inflate(getLayoutInflater());
        setContentView(postsBinding.getRoot());

        postsBinding.postToolbar.setNavigationOnClickListener(v -> onBackPressed());

        postsBinding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsBinding.postRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllPosts();

    }

    private void getAllPosts() {

        NetworkService
                .getInstance()
                .getApi()
                .getAllPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                        assert response.body() != null;
                        postList = new ArrayList<>(response.body());
                        adapter = new PostsAdapter(PostsActivity.this, postList, (post, position) -> {
                            Toast.makeText(PostsActivity.this, "Position: " + post.getId(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Position: " + post.getId());
                        });
                        postsBinding.postRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! " + t.getMessage());
                    }
                });

    }
}