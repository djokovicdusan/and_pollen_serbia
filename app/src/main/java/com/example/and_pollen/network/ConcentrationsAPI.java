package com.example.and_pollen.network;

import com.example.and_pollen.ConcentrationsSuperclass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConcentrationsAPI {

    @GET("/api/opendata/concentrations")
    Call<ConcentrationsSuperclass> getConcentration(
            @Query("id") int id
    );
}
