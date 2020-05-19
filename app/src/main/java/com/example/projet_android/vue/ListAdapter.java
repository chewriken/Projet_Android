package com.example.projet_android.vue;

import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet_android.R;
import com.example.projet_android.model.Champion;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Champion> values;
    private OnItemClickListener listener;
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

    public ListAdapter(List<Champion> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(champion);
            }
        });

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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Champion item);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

}

