package com.alim.guidebook.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alim.guidebook.Adapter.List_Attract_Adapter;
import com.alim.guidebook.Adapter.OblstAdapter;
import com.alim.guidebook.Adapter.OnItemClickListener;
import com.alim.guidebook.AttractActivity;
import com.alim.guidebook.Helper.FbsDatabaseHelper;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.OblstClass;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_List_Attract extends Fragment {


    public Fragment_List_Attract() {
        // Required empty public constructor
    }

    private static List<OblstClass> list_atr ;
    private static boolean check_atr = false;


    public Context _context;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }


    public RecyclerView recyclerView;
    public List_Attract_Adapter list_attract_adapter;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_attract, container, false);



        recyclerView = view.findViewById(R.id.recyclerview);


        Bundle bundle = this.getArguments();
        final String name = bundle.getString("keyname");

        if(MainActivity.language.equals("kz")) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name + " көрікті жерлер");
        }else {((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name+" cписок");}


        new FbsDatabaseHelper(name,true).readBooks(new FbsDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Sight> sights, List<String> keys) {



                list_attract_adapter = new List_Attract_Adapter(getContext(), sights, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Sight sight) {

                        Intent intent = new Intent(getActivity(),AttractActivity.class);

                        if(MainActivity.language.equals("kz"))
                        {
                            intent.putExtra("title",sight.getTitle_kz());
                            intent.putExtra("text",sight.getText_kz());
                        }else{
                            intent.putExtra("title",sight.getTitle_ru());
                            intent.putExtra("text",sight.getText_ru());
                        }

                        intent.putExtra("town",sight.getTown());
                        intent.putExtra("time",sight.getTime());
                        intent.putExtra("street",sight.getStreet());
                        intent.putExtra("id_img",sight.getId_img());
                        intent.putExtra("cost",sight.getCost());
                        intent.putExtra("contact",sight.getContact());
                        intent.putExtra("add_f1",sight.getAdd_f1());
                        intent.putExtra("add_f2",sight.getAdd_f2());
                        intent.putExtra("add_f3",sight.getAdd_f3());

                        startActivity(intent);

                    }
                });

                recyclerView.setAdapter(list_attract_adapter);
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
