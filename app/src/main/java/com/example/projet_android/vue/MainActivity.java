package com.example.projet_android.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet_android.R;
import com.example.projet_android.Singletons;
import com.example.projet_android.controleur.MainController;
import com.example.projet_android.model.Champion;
import com.example.projet_android.model.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;
    private Dialog myDialog;
    private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolBar);
        setSupportActionBar(toolbar);

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
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mAdapter = new ListAdapter(championList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Champion item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(this,Constants.API_ERROR, Toast.LENGTH_SHORT).show();
    }

    public void showSuccess() {
        Toast.makeText(this, Constants.API_Success, Toast.LENGTH_SHORT).show();
    }

    public void showSavedList() {
        Toast.makeText(this, Constants.LIST_SAVED, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void showPopUp(Champion champion) {

        myDialog = new Dialog(this);
        String tag;
        TextView championName, championTittle, championBlurb, championRoleText,championDifficulty ,txtclose;
        TextView hp, hpPerLvl, mp, mpPerLvl, moveSpeed, armor, armorPerLvl, spellBlock, spellBlockPerLvl, attackRange;
        TextView hpRegen, hpRegenPerLvl, mpRegen, mpRegenPerLvl, critique, critiquePerLvl, attackDamage, attackDamagePerLvl;
        TextView attackSpeedPerLvl, attackSpeed;
        ImageView championImage, championRoleImage,championDifficultyImage ;

        myDialog.setContentView(R.layout.custompopup);

        championName = myDialog.findViewById(R.id.championname);
        txtclose = myDialog.findViewById(R.id.txtclose);
        championImage =  myDialog.findViewById(R.id.championImage);
        championTittle = myDialog.findViewById(R.id.championtittle);
        championBlurb = myDialog.findViewById(R.id.championBlurb);
        championRoleText = myDialog.findViewById(R.id.championRoleText);
        championRoleImage = myDialog.findViewById(R.id.championRoleImage);
        championDifficulty = myDialog.findViewById(R.id.difficultyText);
        championDifficultyImage = myDialog.findViewById(R.id.difficulty);

        hp = myDialog.findViewById(R.id.hpTxt);
        hpPerLvl = myDialog.findViewById(R.id.hpPerLvlTxt);
        mp = myDialog.findViewById(R.id.mpTxt);
        mpPerLvl = myDialog.findViewById(R.id.mpPerLvlTxt);
        moveSpeed = myDialog.findViewById(R.id.moveSpeedTxt);
        armor  = myDialog.findViewById(R.id.armorTxt);
        armorPerLvl = myDialog.findViewById(R.id.armorperlevelTxt);
        spellBlock = myDialog.findViewById(R.id.spellblockTxt);
        spellBlockPerLvl = myDialog.findViewById(R.id.spellblockperlevelTxt);
        attackRange = myDialog.findViewById(R.id.attackrangeTxt);
        hpRegen = myDialog.findViewById(R.id.hpregenTxt);
        hpRegenPerLvl = myDialog.findViewById(R.id.hpregenperlevelTxt);
        mpRegen = myDialog.findViewById(R.id.mpregenTxt);
        mpRegenPerLvl = myDialog.findViewById(R.id.mpregenperlevelTxt);
        critique = myDialog.findViewById(R.id.critTxt);
        critiquePerLvl = myDialog.findViewById(R.id.critperlevelTxt);
        attackDamage = myDialog.findViewById(R.id.attackdamageTxt);
        attackDamagePerLvl = myDialog.findViewById(R.id.attackdamageperlevelTxt);
        attackSpeedPerLvl = myDialog.findViewById(R.id.attackspeedperlevelTxt);
        attackSpeed = myDialog.findViewById(R.id.attackspeedTxt);

        hp.setText(""+champion.getStats().getHp());
        hpPerLvl.setText(""+champion.getStats().getHpperlevel());
        mp.setText(""+champion.getStats().getMp());
        mpPerLvl.setText(""+champion.getStats().getMpperlevel());
        moveSpeed.setText(""+champion.getStats().getMovespeed());
        armor.setText(""+champion.getStats().getArmor());
        armorPerLvl.setText(""+champion.getStats().getArmorperlevel());
        spellBlock.setText(""+champion.getStats().getSpellblock());
        spellBlockPerLvl.setText(""+champion.getStats().getSpellblockperlevel());
        attackRange.setText(""+champion.getStats().getAttackrange());
        hpRegen.setText(""+champion.getStats().getHpregen());
        hpRegenPerLvl.setText(""+champion.getStats().getHpregenperlevel());
        mpRegen.setText(""+champion.getStats().getMpregen());
        mpRegenPerLvl.setText(""+champion.getStats().getMpregenperlevel());
        critique.setText(""+champion.getStats().getCrit());
        critiquePerLvl.setText(""+champion.getStats().getCritperlevel());
        attackDamage.setText(""+champion.getStats().getAttackDamage());
        attackDamagePerLvl.setText(""+champion.getStats().getAttackDamageperlevel());
        attackSpeedPerLvl.setText(""+champion.getStats().getAttackspeedperlevel());
        attackSpeed.setText(""+champion.getStats().getAttackspeed());

        String imageUri = "https://github.com/chewriken/Projet_Android/blob/master/app/src/main/res/mipmap-mdpi/"+champion.getName().toLowerCase()+"_0.jpg?raw=true";
        Picasso.get().load(imageUri).into(championImage);
        championName.setText(champion.getName());
        championTittle.setText(champion.getTitle());
        championBlurb.setText(champion.getBlurb());

        List<String> listTags = new ArrayList<>(champion.getTags());
        tag = listTags.get(0);

        championRoleText.setText(tag);

        switch (tag){
            case "Fighter":
                championRoleImage.setImageResource(R.drawable.fighter);
                break;
            case "Assassin":
                championRoleImage.setImageResource(R.drawable.assassin);
                break;
            case "Mage":
                championRoleImage.setImageResource(R.drawable.mage);
                break;
            case "Marksman":
                championRoleImage.setImageResource(R.drawable.marksman);
                break;
            case "Support":
                championRoleImage.setImageResource(R.drawable.support);
                break;
            case "Tank":
                championRoleImage.setImageResource(R.drawable.tank);
                break;
        }

        switch(champion.getInfo().getDifficulty()){
            case 1:
            case 2:
            case 3:
                championDifficulty.setText("Low");
                championDifficultyImage.setImageResource(R.drawable.faible);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                championDifficulty.setText("Moderate");
                championDifficultyImage.setImageResource(R.drawable.medium);
                break;
            case 8:
            case 9:
            case 10:
                championDifficulty.setText("High");
                championDifficultyImage.setImageResource(R.drawable.fort);
                break;

        }

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    public void onSelected(String item) {
        Toast.makeText(this, "Selected: " + item, Toast.LENGTH_LONG).show();
    }
}
