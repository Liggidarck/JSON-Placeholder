package com.george.json.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.george.json.placeholder.API.NetworkService;
import com.george.json.placeholder.API.albums.Album;
import com.george.json.placeholder.API.albums.AlbumsAdapter;
import com.george.json.placeholder.databinding.ActivityAlbumsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsActivity extends AppCompatActivity {

    private static final String TAG = "AlbumsActivity";
    ActivityAlbumsBinding albumsBinding;

    ArrayList<Album> albumArrayList;
    AlbumsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumsBinding = ActivityAlbumsBinding.inflate(getLayoutInflater());
        setContentView(albumsBinding.getRoot());

        albumsBinding.albumsToolbar.setNavigationOnClickListener(v -> onBackPressed());

        albumsBinding.albumsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumsBinding.albumsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllAlbums();

    }

    private void getAllAlbums() {

        NetworkService
                .getInstance()
                .getApi()
                .getAllAlbums()
                .enqueue(new Callback<List<Album>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                        assert response.body() != null;
                        albumArrayList = new ArrayList<>(response.body());
                        adapter = new AlbumsAdapter(AlbumsActivity.this, albumArrayList, (album, position) -> {
                            Log.d(TAG, "Position: " + album.getId());
                            Toast.makeText(AlbumsActivity.this, "Position: " + album.getId(), Toast.LENGTH_SHORT).show();
                        });
                        albumsBinding.albumsRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
                        Log.e(TAG, "Error! " + t.getMessage());
                    }
                });

    }
}