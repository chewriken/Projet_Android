package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://raw.githubusercontent.com/chewriken/Projet_Android/master/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Champion> championList = getDataFromCache();

        if(championList != null){
            showList(championList);
        }else{
            makeApiCall();
        }
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

    private void showList(List<Champion> championList) {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(championList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final LeagueOfLegendsApi leagueOfLegendsApi = retrofit.create(LeagueOfLegendsApi.class);
        Call<RestLeagueOfLegendsResponse> call = leagueOfLegendsApi.getLeagueOfLegendsResponse();
        call.enqueue(new Callback<RestLeagueOfLegendsResponse>() {

            @Override
            public void onResponse(Call<RestLeagueOfLegendsResponse> call, Response<RestLeagueOfLegendsResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Champion> championList = response.body().getResults();
                    savedList(championList);
                    showList(championList);
                    Toast.makeText(getApplicationContext()," API Success", Toast.LENGTH_SHORT).show();
                } else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestLeagueOfLegendsResponse> call, Throwable t) {
                showError();
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
        Toast.makeText(this,Constants.LIST_SAVED, Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(this,Constants.API_ERROR, Toast.LENGTH_SHORT).show();
    }
}
