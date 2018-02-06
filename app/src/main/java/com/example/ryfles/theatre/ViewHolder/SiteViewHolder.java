package com.example.ryfles.theatre.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.R;

/**
 * Created by Ryfles2 on 05.02.2018.
 */

public class SiteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     public TextView textView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SiteViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.btnSwitch);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
