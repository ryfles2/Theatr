package com.example.ryfles.theatre.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.R;

/**
 * Created by Ryfles on 2018-02-07.
 */

public class MyTicketsViewHolder extends RecyclerView.ViewHolder{// implements View.OnClickListener {

    public TextView txtData;
    public TextView txtGodzina;
    public TextView txtMiejsce;
    public TextView txtTytul;
    public TextView txtStatus;
    public TextView txtPrice;
    public Button btnBuy;
    public Button btnReservation;



    public MyTicketsViewHolder(View itemView) {
        super(itemView);
        txtData = itemView.findViewById(R.id.ticketData);
        txtGodzina = itemView.findViewById(R.id.ticketGodzina);
        txtMiejsce = itemView.findViewById(R.id.ticketMiejsce);
        txtTytul = itemView.findViewById(R.id.ticketTytul);
        txtStatus = itemView.findViewById(R.id.ticketStatus);
        txtPrice = itemView.findViewById(R.id.ticketPrice);
        btnBuy = itemView.findViewById(R.id.btnBuy);
        btnReservation = itemView.findViewById(R.id.btnReservation);



    }


}
