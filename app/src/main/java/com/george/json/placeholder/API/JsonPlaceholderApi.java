package com.george.json.placeholder.API;

import com.george.json.placeholder.API.comments.Comment;
import com.george.json.placeholder.API.posts.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("comments")
    Call<List<Comment>> getAllComments();

}
