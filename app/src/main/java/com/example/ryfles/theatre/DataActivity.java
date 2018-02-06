package com.example.ryfles.theatre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.Models.DataModel;
import com.example.ryfles.theatre.Models.RepertoireModel;
import com.example.ryfles.theatre.ViewHolder.DataViewHolder;
import com.example.ryfles.theatre.ViewHolder.RepertoireViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class DataActivity extends Activity {

    View view;
    FirebaseDatabase database;
    DatabaseReference data;
    RecyclerView recyclerData;
    RecyclerView.LayoutManager layoutManager;
    String tytulId="";
    FirebaseRecyclerAdapter<DataModel, DataViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_id_data_list);

      /*  //Intent intent = getIntent();

        database= FirebaseDatabase.getInstance();
        data = database.getReference("idData");

        recyclerData=(RecyclerView)findViewById(R.id.repertuaDataMenu);
        recyclerData.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerData.setLayoutManager(layoutManager);

        //Get intent here
        if(getIntent()!=null)
            tytulId=getIntent().getStringExtra("RepertualId");
        if(tytulId.isEmpty() && tytulId != null)
        {
            LoadListTytul(tytulId);
        }
        */

    }

    private void LoadListTytul(String tytulId) {
        adapter = new FirebaseRecyclerAdapter<DataModel, DataViewHolder>(DataModel.class,R.layout.menu_id_data,DataViewHolder.class,data.orderByChild("idTytul").equalTo(tytulId)) {//like select from data where idTytuł =
            @Override
            protected void populateViewHolder(DataViewHolder viewHolder, DataModel model, int position) {
            viewHolder.txtDataName.setText(model.getData());
            viewHolder.txtDataGodzina.setText(model.getGodzina());

            final DataModel local = model;
            viewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Toast.makeText(DataActivity.this,""+local.getData(),Toast.LENGTH_SHORT).show();
                }
            });
            }
        };
        recyclerData.setAdapter(adapter);
    }


}
