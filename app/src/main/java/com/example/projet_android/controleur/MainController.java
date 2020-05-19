package com.example.projet_android.controleur;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet_android.R;
import com.example.projet_android.Singletons;
import com.example.projet_android.model.Champion;
import com.example.projet_android.model.Constants;
import com.example.projet_android.model.RestLeagueOfLegendsResponse;
import com.example.projet_android.model.Stats;
import com.example.projet_android.vue.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;
    private Dialog myDialog;

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
            public void onResponse(Call<RestLeagueOfLegendsResponse> call, Response<RestLeagueOfLegendsResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Champion> championList = response.body().getResults();
                    savedList(championList);
                    view.showList(championList);
                    //Toast.makeText(getApplicationContext()," API Success", Toast.LENGTH_SHORT).show();
                } else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestLeagueOfLegendsResponse> call, Throwable t) {
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
        //Toast.makeText(this,Constants.LIST_SAVED, Toast.LENGTH_SHORT).show();
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
