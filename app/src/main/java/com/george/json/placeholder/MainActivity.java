package com.george.json.placeholder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.george.json.placeholder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.cardPosts.setOnClickListener(v -> startActivity(new Intent(this, PostsActivity.class)));
        mainBinding.cardComments.setOnClickListener(v -> startActivity(new Intent(this, CommentsActivity.class)));
        mainBinding.cardAlbums.setOnClickListener(v -> startActivity(new Intent(this, AlbumsActivity.class)));
        mainBinding.cardPhotos.setOnClickListener(v -> startActivity(new Intent(this, PhotoActivity.class)));
        mainBinding.cardTodos.setOnClickListener(v -> startActivity(new Intent(this, TodosActivity.class)));
        mainBinding.cardUsers.setOnClickListener(v -> startActivity(new Intent(this, UsersActivity.class)));
    }
}