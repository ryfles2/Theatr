package com.example.ryfles.theatre.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.R;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class RepertoireViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RepertoireViewHolder(View itemView) {
        super(itemView);

        txtMenuName=(TextView)itemView.findViewById(R.id.menuItemName);
        imageView=(ImageView) itemView.findViewById(R.id.menuItemImage);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
