package com.george.json.placeholder.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if(mInstance == null)
            mInstance = new NetworkService();
        return mInstance;
    }

    public JsonPlaceholderApi getApi() {
        return retrofit.create(JsonPlaceholderApi.class);
    }

}
