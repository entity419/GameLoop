package com.dtt.arenaofvalue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dtt.arenaofvalue.R;
import com.dtt.arenaofvalue.model.Gem;
import com.dtt.arenaofvalue.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context mContext;
    private List<Skill> listSkill;

    public DetailAdapter(Context mContext, List<Skill> listSkill) {
        this.mContext = mContext;
        this.listSkill = listSkill;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_skills, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String linkImage = listSkill.get(position).getImg();
        String title = listSkill.get(position).getTileSkill();
        String countdown = listSkill.get(position).getCountDown();
        String mana = listSkill.get(position).getMana();
        String detail = listSkill.get(position).getDetail();
        holder.tvTitle0.setText(title);
        holder.tvTitle1.setText(countdown);
        holder.tvTitle2.setText(mana);
        holder.tvTitle3.setText(detail);
        Glide.with(mContext).load(linkImage).into(holder.imgDetail);

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tungkasl;djaskl", "onClick: "+linkImage);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listSkill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle0, tvTitle1, tvTitle2, tvTitle3;
        ImageView imgDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle0 = itemView.findViewById(R.id.tv_is0);
            tvTitle1 = itemView.findViewById(R.id.tv_is1);
            tvTitle2 = itemView.findViewById(R.id.tv_is2);
            tvTitle3 = itemView.findViewById(R.id.tv_is3);
            imgDetail = itemView.findViewById(R.id.img_item_skill);

        }
    }

    public void setData(ArrayList<Skill> arrSkill) {
        this.listSkill = arrSkill;
        notifyDataSetChanged();

    }
    public void setDataGem(ArrayList<Gem> arrGem){

    }
}
