package com.example.projet_android.controleur;

import android.content.SharedPreferences;

import com.example.projet_android.Singletons;
import com.example.projet_android.model.Champion;
import com.example.projet_android.model.Constants;
import com.example.projet_android.model.RestLeagueOfLegendsResponse;
import com.example.projet_android.vue.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart(){
        List<Champion> championList = getDataFromCache();

        if(championList != null){
            view.showList(championList);
        }else{
            makeApiCall();
        }
    }

    private void makeApiCall(){
        Call<RestLeagueOfLegendsResponse> call = Singletons.getLeagueOfLegendsApi().getLeagueOfLegendsResponse();
        call.enqueue(new Callback<RestLeagueOfLegendsResponse>() {

            @Override
            public void onResponse(@NonNull Call<RestLeagueOfLegendsResponse> call, @NonNull Response<RestLeagueOfLegendsResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Champion> championList = response.body().getResults();
                    savedList(championList);
                    view.showList(championList);
                    view.showSuccess();
                } else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestLeagueOfLegendsResponse> call, @NonNull Throwable t) {
                view.showError();
            }

        });
    }

    private void savedList(List<Champion> championList) {
        String jsonString = gson.toJson(championList);
        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString(Constants.KEY_CHAMPION_LIST, jsonString)
                .apply();

        view.showSavedList();
    }

    private List<Champion> getDataFromCache() {
        String jsonChampion = sharedPreferences.getString(Constants.KEY_CHAMPION_LIST,null);

        if(jsonChampion == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Champion>>(){}.getType();
            return gson.fromJson(jsonChampion,listType);
        }
    }

    public void onItemClick(Champion champion){
        view.showPopUp(champion);

    }
}
