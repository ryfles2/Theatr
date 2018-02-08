package com.example.ryfles.theatre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.Models.MyTicketsModel;
import com.example.ryfles.theatre.ViewHolder.MyTicketsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ryfles on 2018-01-23.
 */

public class MyTicketsFragment extends Fragment {

    private View view;
    private FirebaseRecyclerAdapter<MyTicketsModel,MyTicketsViewHolder> adapterTicket;
    private FirebaseDatabase database;
    private DatabaseReference myTickets;
    private RecyclerView recyclerMyTickets;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth mAuth;
    //public Button btnBuy;
    //public Button btnReservation;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mytickets, container, false);
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        myTickets = database.getReference("myTickets/"+currentUser.getUid());//+"/01");
        recyclerMyTickets=(RecyclerView)view.findViewById(R.id.myTicketRecyclerMenu);
        recyclerMyTickets.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),1);
        recyclerMyTickets.setLayoutManager(layoutManager);

        loadMyTickets();

        return view;
    }

    private void loadMyTickets() {
        adapterTicket = new FirebaseRecyclerAdapter<MyTicketsModel, MyTicketsViewHolder>(MyTicketsModel.class, R.layout.menu_mytickets1,MyTicketsViewHolder.class,myTickets) {
            @Override
            protected void populateViewHolder(MyTicketsViewHolder viewHolder, MyTicketsModel model, final int position) {
                viewHolder.txtTytul.setText("Title "+model.getTytul());
                viewHolder.txtMiejsce.setText("Seat "+model.getMiejsce());
                viewHolder.txtGodzina.setText("Time "+model.getGodzina());
                viewHolder.txtData.setText("Data "+model.getData());
                viewHolder.txtStatus.setText(model.getStatus());
                //viewHolder.btnBuy = (Button) view.findViewById(R.id.btnBuy);
                //viewHolder.btnReservation = (Button) view.findViewById(R.id.btnReservation);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }
        };

        recyclerMyTickets.setAdapter(adapterTicket);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("MyTickets");
    }

}
