package com.alim.guidebook.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alim.guidebook.Adapter.F_TOURSE_ADAPTER;
import com.alim.guidebook.Helper.FbsDatabaseTourHelper;
import com.alim.guidebook.MyObject.Tour;
import com.alim.guidebook.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Tour extends Fragment {


    public Fragment_Tour() {
        // Required empty public constructor
    }

    Context _context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__tour, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recview_tours);



        new FbsDatabaseTourHelper().readTours(new FbsDatabaseTourHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Tour> tours, List<String> keys) {
                F_TOURSE_ADAPTER f_tourse_adapter = new F_TOURSE_ADAPTER(getContext(),tours);
                recyclerView.setAdapter(f_tourse_adapter);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });



        return view;
    }

}
