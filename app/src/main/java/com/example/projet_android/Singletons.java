package com.example.projet_android;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projet_android.model.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static LeagueOfLegendsApi leagueOfLegendsApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }

        return sharedPreferencesInstance;
    }

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }

        return gsonInstance;
    }

    public static LeagueOfLegendsApi getLeagueOfLegendsApi() {
        if(leagueOfLegendsApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            leagueOfLegendsApiInstance = retrofit.create(LeagueOfLegendsApi.class);
        }
        return leagueOfLegendsApiInstance;
    }
}
