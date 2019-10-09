package com.dtt.arenaofvalue.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dtt.arenaofvalue.OnItemClick;
import com.dtt.arenaofvalue.R;
import com.dtt.arenaofvalue.model.Heroes;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ViewHolder> {

    private Context mContext;
    private List<Heroes> listHero;
    private OnItemClick onItemClick;

    public ListHeroAdapter(Context mContext, List<Heroes> listHero, OnItemClick onItemClick) {
        this.mContext = mContext;
        this.listHero = listHero;
        this.onItemClick = onItemClick;
    }

    public void setData(ArrayList<Heroes> arrHeroes) {
        this.listHero = arrHeroes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_heroes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Heroes heroes = listHero.get(position);
        final String name = listHero.get(position).getName();
        final String image = listHero.get(position).getImage();
        Glide.with(mContext).load(image).into(holder.imgHero);

        holder.tvNameHero.setText(name);

        holder.imgHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick(heroes);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameHero;
        private CircleImageView imgHero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHero = itemView.findViewById(R.id.tv_item_hero);
            imgHero = itemView.findViewById(R.id.img_item_hero);

        }
    }
}
