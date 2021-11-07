package com.george.json.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.photos.Photo;
import com.george.json.placeholder.API.photos.PhotosAdapter;
import com.george.json.placeholder.databinding.ActivityPhotoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    private static final String TAG = "PhotoActivity";
    ActivityPhotoBinding photoBinding;

    ArrayList<Photo> photoArrayList;
    PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoBinding =  ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(photoBinding.getRoot());

        photoBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        photoBinding.photoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        photoBinding.photoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllPhotos();
    }

    private void getAllPhotos() {
        NetworkService
                .getInstance()
                .getApi()
                .getAllPhotos()
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                        assert response.body() != null;
                        photoArrayList = new ArrayList<>(response.body());
                        photosAdapter = new PhotosAdapter(PhotoActivity.this, photoArrayList, (photo, position) -> {
                            Toast.makeText(PhotoActivity.this, "Position: "+ photo.getId(), Toast.LENGTH_SHORT).show();
                        });
                        photoBinding.photoRecyclerView.setAdapter(photosAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! " + t.getMessage());
                    }
                });
    }


}