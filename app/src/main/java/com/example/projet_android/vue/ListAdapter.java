package com.example.projet_android.vue;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet_android.R;
import com.example.projet_android.model.Champion;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Champion> values;
    private List<Champion> itemsFiltered;
    private OnItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

    ListAdapter(List<Champion> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
        itemsFiltered = myDataset;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Champion champion = itemsFiltered.get(position);
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
        return itemsFiltered.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Champion item);
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<Champion> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered = values;
                } else {
                    for (Champion champion : values) {
                        if (champion.getName().toLowerCase().contains(query.toLowerCase())) {
                            filtered.add(champion);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                itemsFiltered = (ArrayList<Champion>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    /*public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }*/
}

