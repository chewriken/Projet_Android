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
    private static final String BASE_URL = "https://pokeapi.co/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Pokemon> pokemonList = getDataFromCache();
        if(pokemonList != null){
            showList(pokemonList);
        }else{
            makeApiCall();
        }
    }

    private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST,null);

        if(jsonPokemon == null){
           return null;
        }else{
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);
        }
    }

    private void showList(List<Pokemon> pokemonList) {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final PokeApi pokeAPI = retrofit.create(PokeApi.class);
        Call<RestPokemonResponse> call = pokeAPI.getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {

            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Pokemon> pokemonList = response.body().getResults();
                    savedList(pokemonList);
                    showList(pokemonList);
                } else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                showError();
            }

        });

    }

    private void savedList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
        Toast.makeText(this,Constants.LIST_SAVED, Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(this,Constants.API_ERROR, Toast.LENGTH_SHORT).show();
    }
}
