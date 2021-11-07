package com.george.json.placeholder.API.todos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("completed")
    @Expose
    private boolean completed;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
