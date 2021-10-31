package com.george.json.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.comments.Comment;
import com.george.json.placeholder.API.comments.CommentsAdapter;
import com.george.json.placeholder.databinding.ActivityCommentsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";

    ActivityCommentsBinding commentsBinding;

    CommentsAdapter adapter;
    ArrayList<Comment> commentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentsBinding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(commentsBinding.getRoot());

        commentsBinding.commentsToolbar.setNavigationOnClickListener(v -> onBackPressed());

        commentsBinding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentsBinding.commentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllComments();
    }

    private void getAllComments() {

        NetworkService
                .getInstance()
                .getApi()
                .getAllComments()
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
                        assert response.body() != null;
                        commentArrayList = new ArrayList<>(response.body());
                        adapter = new CommentsAdapter(CommentsActivity.this, commentArrayList, (comment, position) -> {
                            Log.d(TAG, "Position: " + comment.getId());
                            Toast.makeText(CommentsActivity.this, "Position: " + comment.getId(), Toast.LENGTH_SHORT).show();
                        });
                        commentsBinding.commentsRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! " + t.getMessage());
                    }
                });

    }
}