package com.example.projet_android.vue;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet_android.R;
import com.example.projet_android.model.Champion;
import com.example.projet_android.model.Stats;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Champion> values;
    private Dialog myDialog;

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView imageIcon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imageIcon = v.findViewById(R.id.imageIcon);
            myDialog = new Dialog(v.getContext());
        }
    }

    public ListAdapter(List<Champion> myDataset) {
        values = myDataset;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Champion champion = values.get(position);

        holder.txtHeader.setText(champion.getName());
        holder.txtHeader.setTextColor(Color.WHITE);

        holder.txtFooter.setText(champion.getTitle());
        holder.txtFooter.setTextColor(Color.WHITE);

        setOnClick(holder.txtHeader, champion);
        setOnClick(holder.imageIcon, champion);

        switch (champion.getName()){
            case "Aurelion Sol":
                champion.setName("aurelionsol");
                break;
            case "Cho'Gath":
                champion.setName("chogath");
                break;
            case "Dr. Mundo":
                champion.setName("drmundo");
                break;
            case "Jarvan IV":
                champion.setName("jarvaniv");
                break;
            case "Kai'Sa":
                champion.setName("kaisa");
                break;
            case "Kha'Zix":
                champion.setName("khazix");
                break;
            case "Kog'Maw":
                champion.setName("kogmaw");
                break;
            case "Lee Sin":
                champion.setName("leesin");
                break;
            case "Master Yi":
                champion.setName("masteryi");
                break;
            case "Miss Fortune":
                champion.setName("missfortune");
                break;
            case "Nunu & Willump":
                champion.setName("nunu");
                break;
            case "Rek'Sai":
                champion.setName("reksai");
                break;
            case "Tahm Kench":
                champion.setName("tahmkench");
                break;
            case "Twisted Fate":
                champion.setName("twistedfate");
                break;
            case "Vel'Koz":
                champion.setName("velkoz");
                break;
            case "Xin Zhao":
                champion.setName("xinzhao");
                break;
        }

        String imageUri = "https://github.com/chewriken/Projet_Android/blob/master/app/src/main/res/mipmap-mdpi/"+champion.getName().toLowerCase()+"_square.png?raw=true";
        Picasso.get().load(imageUri).into(holder.imageIcon);
    }

    private void setOnClick(TextView txtHeader, final Champion champion) {
        txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(champion);
            }
        });
    }

    private void setOnClick(ImageView imageView, final Champion champion) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(champion);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    @SuppressLint("SetTextI18n")
    private void showPopup(final Champion champion) {

        String tag;
        TextView championName, championTittle, championBlurb, championRoleText,championDifficulty ,txtclose;
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

        InitialisationStats(champion);

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

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    private void InitialisationStats(final Champion champion) {
        TextView hp, hpPerLvl, mp, mpPerLvl, moveSpeed, armor, armorPerLvl, spellBlock, spellBlockPerLvl, attackRange;
        TextView hpRegen, hpRegenPerLvl, mpRegen, mpRegenPerLvl, critique, critiquePerLvl, attackDamage, attackDamagePerLvl;
        TextView attackSpeedPerLvl, attackSpeed;

        Stats stats = champion.getStats();

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
    }

}

