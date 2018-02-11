package com.example.ryfles.theatre;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ryfles.theatre.Common.ConfigPayPal;
import com.example.ryfles.theatre.Models.MyTicketsModel;
import com.example.ryfles.theatre.Models.SiteModel;
import com.example.ryfles.theatre.ViewHolder.MyTicketsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


/**
 * Created by Ryfles on 2018-01-23.
 */

public class MyTicketsFragment extends Fragment  {

    private View view;
    private FirebaseRecyclerAdapter<MyTicketsModel,MyTicketsViewHolder> adapterTicket;
    private FirebaseDatabase database;
    private DatabaseReference myTickets;
    private RecyclerView recyclerMyTickets;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth mAuth;
    private  FirebaseUser currentUser;
    private String tempIdMiejsca, tempMiejsce;


    //paypal
    private static PayPalConfiguration confing = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)//use sandbox when test, later change
            .clientId(ConfigPayPal.PAYPAL_CLIENT_ID);
    private static final int PAYPAL_REQUEST_CODE=9999;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mytickets, container, false);
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        //int paypal
        Intent intent = new Intent(getActivity(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,confing);
        getActivity().startService(intent);

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
            protected void populateViewHolder(final MyTicketsViewHolder viewHolder, final MyTicketsModel model, final int position) {
                viewHolder.txtTytul.setText("Title "+model.getTytul());
                viewHolder.txtMiejsce.setText("Seat "+model.getMiejsce());
                viewHolder.txtGodzina.setText("Time "+model.getGodzina());
                viewHolder.txtData.setText("Data "+model.getData());
                viewHolder.txtStatus.setText(model.getStatus());
                viewHolder.txtPrice.setText("Price "+model.getPrice());


                tempIdMiejsca=model.getIdMiejsca();
                tempMiejsce=model.getMiejsce();

                if(model.getStatus().equals("Reserved"))
                {
                    viewHolder.btnReservation.setVisibility(View.VISIBLE);
                    viewHolder.btnBuy.setVisibility(View.VISIBLE);
                }
                else
                {
                    viewHolder.btnReservation.setVisibility(View.INVISIBLE);
                    viewHolder.btnBuy.setVisibility(View.INVISIBLE);
                }

                viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startPayment(Integer.parseInt(model.getPrice().toString()));
                    }
                });

                viewHolder.btnReservation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"you canceled the place reservation "+model.getMiejsce(),Toast.LENGTH_SHORT).show();

                        database.getReference().child("myTickets/"+currentUser.getUid()+"/"+model.getIdMiejsca()+model.getMiejsce()).removeValue();

                        DatabaseReference data = database.getReference("idMiejsce/" + model.getIdMiejsca() );
                        SiteModel modelSite= new SiteModel("0","0","0");
                        data.child(model.getMiejsce()).setValue(modelSite);


                    }
                });

            }
        };

        recyclerMyTickets.setAdapter(adapterTicket);

    }
    private void startPayment(int amount) {
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD","Theater Payment",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, confing);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PAYPAL_REQUEST_CODE)
        {
            if(resultCode== getActivity().RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null)
                {
                    try
                    {
                        String  paymentDetails =    confirmation.toJSONObject().toString(4);
                        JSONObject jsonObject = new JSONObject(paymentDetails);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    database.getReference().child("myTickets/"+currentUser.getUid()+"/"+tempIdMiejsca+tempMiejsce).child("status").setValue("Bought");

                    DatabaseReference dataTmp = database.getReference("idMiejsce/" + tempIdMiejsca );
                    dataTmp.child(tempMiejsce).child("status").setValue("2");

                }
            }
            else if (resultCode == Activity .RESULT_CANCELED )
            {
                Toast.makeText(getContext(),"Payments Failed",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID)
                Toast.makeText(getContext(), "Payments Invalid", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("MyTickets");
    }

}
