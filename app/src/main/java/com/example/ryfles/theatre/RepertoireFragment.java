package com.example.ryfles.theatre;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ryfles.theatre.Interface.ItemClickListener;
import com.example.ryfles.theatre.Models.DataModel;
import com.example.ryfles.theatre.Models.RepertoireModel;
import com.example.ryfles.theatre.Models.SiteModel;
import com.example.ryfles.theatre.ViewHolder.DataViewHolder;
import com.example.ryfles.theatre.ViewHolder.RepertoireViewHolder;
import com.example.ryfles.theatre.ViewHolder.SiteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * Created by Ryfles on 2018-01-23.
 */

public class RepertoireFragment extends Fragment {

    private FirebaseRecyclerAdapter<RepertoireModel,RepertoireViewHolder> adapter;
    FirebaseRecyclerAdapter<DataModel, DataViewHolder> adapterDate;
    private FirebaseRecyclerAdapter<SiteModel,SiteViewHolder> adapterSite;
    private View view;
    private ImageView imageView;
    private FirebaseDatabase database;
    private DatabaseReference repertuar;
    private RecyclerView recyclerRepertuar;
    private RecyclerView.LayoutManager layoutManager;
    String chooseFilm,chooseData,posterURL,seatId,branchSeat;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repertoire, container, false);

        database= FirebaseDatabase.getInstance();
        repertuar = database.getReference("Repertual");
        imageView = view.findViewById(R.id.LogoImageView);
        recyclerRepertuar=(RecyclerView)view.findViewById(R.id.repertuaRecyclerMenu);
        recyclerRepertuar.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),1);
       // layoutManager.layoutDecoratedWithMargins(view.findViewById(R.id.repertuaRecyclerMenu),20,0,0,20);
        recyclerRepertuar.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();


        loadMenu();
        return view;
    }

    private void loadMenu() {
        final Context context = getContext();
        adapter = new FirebaseRecyclerAdapter<RepertoireModel, RepertoireViewHolder>(RepertoireModel.class,R.layout.menu_repertuar,RepertoireViewHolder.class,repertuar) {
            @Override
            protected void populateViewHolder(RepertoireViewHolder viewHolder, final RepertoireModel model, int position) {
                viewHolder.txtMenuName.setText(model.getTytul());
                Picasso.with(getActivity().getBaseContext()).load(model.getUrl()).into(viewHolder.imageView);
                final RepertoireModel clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                         chooseFilm = Integer.toString(position+1); // pobranie id filmu i wrzucenie do stringa, +1 bo rekordy w bazie danych zaczynaja sie od 1 a w na liscie od 0
                         imageView.setVisibility(View.VISIBLE);
                         Picasso.with(getActivity().getBaseContext()).load(model.getUrl()).into(imageView);
                         loadDate();

                    }
                });
            }
        };
        recyclerRepertuar.setAdapter(adapter);
    }

    private void loadDate()
    {
        DatabaseReference data = database.getReference("idData");
        adapterDate = new FirebaseRecyclerAdapter<DataModel, DataViewHolder>(DataModel.class,R.layout.menu_id_data,DataViewHolder.class,data.orderByChild("idTytul").equalTo(chooseFilm)) {//like select from data where idTytuł =
            @Override
            protected void populateViewHolder(DataViewHolder viewHolder, final DataModel model, int position) {
                viewHolder.txtDataName.setText(model.getData());
                viewHolder.txtDataGodzina.setText(model.getGodzina());

                final DataModel local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        seatId = "0" + Integer.toString(position+1);
                        loadSites();
                    }
                });
            }
        };
        recyclerRepertuar.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRepertuar.setAdapter(adapterDate);
    }

    private void loadSites()
    {
        final DatabaseReference data = database.getReference("idMiejsce/" + seatId );
        adapterSite = new FirebaseRecyclerAdapter<SiteModel, SiteViewHolder>(SiteModel.class,R.layout.menu_id_data_list,SiteViewHolder.class,data) {
            @Override
            protected void populateViewHolder(final SiteViewHolder viewHolder, final SiteModel model, final int position) {
                viewHolder.textView.setText(Integer.toString(position+1));
                final String status = model.getStatus();
                final String IdKupujacego = model.getIdKupujacego();
                final FirebaseUser currentUser = mAuth.getCurrentUser();
                boolean user=false;
                if (currentUser != null)
                    user =model.getIdKupujacego().equals(currentUser.getEmail().toString());
                if( user && model.getStatus().equals("3") ){
                    viewHolder.textView.setBackgroundColor(Color.BLUE);
                }
                else if(model.getStatus().equals("0")) {
                    viewHolder.textView.setBackgroundColor(Color.GREEN);
                }//|| model.getStatus().equals("3")
//                else if(model.getStatus().equals("1")   ) {
//                    viewHolder.textView.setBackgroundColor(Color.YELLOW);
//                }
                else if(model.getStatus().equals("2")) {
                    viewHolder.textView.setBackgroundColor(Color.RED);
                }
                else {
                    viewHolder.textView.setBackgroundColor(Color.YELLOW);
                }




                viewHolder.setItemClickListener(new ItemClickListener() {

                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        String position1 = Integer.toString(position+1);
                        if (currentUser != null)
                        {
                            if((status.equals("3")) && IdKupujacego.equals(currentUser.getEmail().toString()) ) {
                                SiteModel model= new SiteModel("0","0");
                                data.child(position1).setValue(model);
                                viewHolder.textView.setBackgroundColor(Color.GREEN);

                            }
                            else if(status.equals("0")) {

                                SiteModel model= new SiteModel(currentUser.getEmail().toString(),"3");
                                data.child(position1).setValue(model);
                                viewHolder.textView.setBackgroundColor(Color.BLUE);
                            }
                        }
                        else {
                            Toast.makeText(getContext(),"Please log in",Toast.LENGTH_SHORT).show();
                        }
//                        if((status.equals("3"))) {
//                            SiteModel model= new SiteModel("0","0");
//                            data.child(position1).setValue(model);
//                            viewHolder.textView.setBackgroundColor(Color.GREEN);
//
//                        }
//                        else if(status.equals("0")) {
//
//                            SiteModel model= new SiteModel(currentUser.getEmail().toString(),"3");
//                            data.child(position1).setValue(model);
//                            viewHolder.textView.setBackgroundColor(Color.BLUE);
//                        }
                    }
                });
            }
        };
        recyclerRepertuar.setLayoutManager(new GridLayoutManager(getContext(),10));
        recyclerRepertuar.setAdapter(adapterSite);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Repertoire");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadMenu();
    }

}

