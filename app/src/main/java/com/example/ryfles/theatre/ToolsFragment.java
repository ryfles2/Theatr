package com.example.ryfles.theatre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ryfles on 2018-01-23.
 */

public class ToolsFragment extends Fragment {


    private TextView txtTools;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tools, container, false);
        view.findViewById(R.id.txtTools);

        //txtTools.setText();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //getActivity().setTitle("Tools");
        getActivity().setTitle(getString(R.string.category_help));
    }
}
