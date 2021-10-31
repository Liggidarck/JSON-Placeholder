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

    }
}