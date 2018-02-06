package com.example.ryfles.theatre.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ryfles.theatre.Models.RepertoireModel;
import com.example.ryfles.theatre.R;

import java.util.List;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class ListViewAdapter{// extends BaseAdapter{

//    public ListViewAdapter(Activity activity, List<RepertoireModel> lstRepertoire) {
//        this.activity = activity;
//        this.lstRepertoire = lstRepertoire;
//    }
//
//    Activity activity;
//    List<RepertoireModel> lstRepertoire;
//    LayoutInflater inflater;
//
//    @Override
//    public int getCount() {
//        return lstRepertoire.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return lstRepertoire.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View itemView = inflater.inflate(R.layout.fragment_repertoire,null);
//
//        TextView textTytul = (TextView)itemView.findViewById(R.id.txtTytul);
//        TextView textUrl = (TextView)itemView.findViewById(R.id.txtUrl);
//
//        textTytul.setText(lstRepertoire.get(i).getTytul());
//        textUrl.setText(lstRepertoire.get(i).getUrl());
//
//        return  itemView;
//    }
}
