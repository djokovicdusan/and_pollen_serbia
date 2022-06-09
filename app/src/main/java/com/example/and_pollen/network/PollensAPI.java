package com.example.and_pollen.network;

import com.example.and_pollen.PollenSuperclass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PollensAPI {
    //?date_after={date_after}&date_before={date_before}&location_ids={location_ids}

    @GET("/api/opendata/pollens")
    Call<PollenSuperclass> getPollen(
                @Query("date_after") String date_after,
                @Query("date_before") String date_before,
                @Query("location_ids") int location_ids
    );
}
