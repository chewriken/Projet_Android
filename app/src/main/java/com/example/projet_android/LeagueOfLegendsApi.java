package com.example.projet_android;

import com.example.projet_android.model.RestLeagueOfLegendsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeagueOfLegendsApi {

    @GET("champion.json")
    Call<RestLeagueOfLegendsResponse> getLeagueOfLegendsResponse();

}

