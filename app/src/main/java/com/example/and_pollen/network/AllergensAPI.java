package com.example.and_pollen.network;

import com.example.and_pollen.responses.AllergensResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AllergensAPI {

    @GET("/api/opendata/allergens")
    Call<List<AllergensResponse>> getAllergen(
            @Query("id") int id
    );
}
