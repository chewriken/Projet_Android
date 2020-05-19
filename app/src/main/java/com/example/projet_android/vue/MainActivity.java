package com.example.projet_android.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projet_android.Singletons;
import com.example.projet_android.R;
import com.example.projet_android.controleur.MainController;
import com.example.projet_android.model.Champion;
import com.example.projet_android.model.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));

        controller.onStart();
    }

    public void showList(List<Champion> championList) {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(championList);
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(this,Constants.API_ERROR, Toast.LENGTH_SHORT).show();
    }
}
