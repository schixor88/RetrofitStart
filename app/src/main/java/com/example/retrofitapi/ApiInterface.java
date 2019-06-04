package com.example.retrofitapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("posts/{id}")
    Call<List<Post>> getPosts(@Path("id") int postId);
}
