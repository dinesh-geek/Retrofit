package com.example.retrofit;

import java.util.HashMap;

import model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {



    /**
     * 1. Get
     * 2. Post
     * 3. Put
     * 4. Delete
     */

//    @GET("everything/")
//    Call<String> getAllNews(@QueryMap HashMap<String, Object> queries);
//
//    @GET("top-headlines/")
//    Call<String> getAllSources(@QueryMap HashMap<String, Object> queries);

    @GET("top-headlines/")
    Call<Result> getAllSource(@QueryMap HashMap<String, Object> queries);

    @GET("everything/")
    Call<Result> getAllNews(@QueryMap HashMap<String, Object> queries);

}
