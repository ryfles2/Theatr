package com.example.ryfles.theatre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ryfles.theatre.Models.RepertoireModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ryfles on 2018-01-23.
 */

public class PaymentFragment extends Fragment {

    private View view;
    private TextView txtTytul;
    private TextView txtUrl;
    private Button btnGet;
    private EditText etxtUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_payment, container, false);

        txtTytul=(TextView)view.findViewById(R.id.txtTytul);
        txtUrl=(TextView)view.findViewById(R.id.txtUrl);
        btnGet=(Button)view.findViewById(R.id.btnGet);
        etxtUrl=(EditText)view.findViewById(R.id.etxtUrl);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableRepertual = database.getReference("Repertual");

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableRepertual.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("01").exists()) {
                            //Get Repertual information
                            //zczytywanie z bazy
                            RepertoireModel repertoireModel = dataSnapshot.child("01").getValue(RepertoireModel.class);
                            txtTytul.setText(repertoireModel.getTytul().toString());
                            txtUrl.setText(repertoireModel.getUrl().toString());
                            //RepertoireModel user = new RepertoireModel("tutul1","https://i.jeded.com/i/troy.11710.jpg");
                            //tableRepertual.child("01").setValue(user);
                        }
                        else{
                            txtUrl.setText("Nie ma");
                        }
                        //dodawanie do bazy
//                        RepertoireModel user = new RepertoireModel("tutul1","https://i.jeded.com/i/troy.11710.jpg");
//                        tableRepertual.child("01").setValue(user);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Payments");
    }
}
