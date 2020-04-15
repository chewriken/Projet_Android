package com.example.projet_android;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeagueOfLegendsApi {

    @GET("champion.json")
    Call<RestLeagueOfLegendsResponse> getLeagueOfLegendsResponse();

}

