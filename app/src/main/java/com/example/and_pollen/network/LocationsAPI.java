package com.example.and_pollen.network;



import com.example.and_pollen.model.Locations;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface LocationsAPI {
    @GET("/api/opendata/locations/")

    Call<List<Locations>> getLocations();



}
