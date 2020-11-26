package com.alim.guidebook.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.alim.guidebook.Adapter.List_Attract_Adapter;
import com.alim.guidebook.Adapter.OnItemClickListener;
import com.alim.guidebook.Adapter.SightAdapter;
import com.alim.guidebook.AttractActivity;
import com.alim.guidebook.Helper.FbsDatabaseHelper;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import java.util.Arrays;
import java.util.List;


public class Fragment_CulHer extends Fragment {


    public Context _context;



    public static String[] types_1 = new String[5];
    public static String[] types_2 = {"Arch","Muzeum","His","Nature","relax"};


    public static String sel_types;

    public Fragment_CulHer() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public RecyclerView recyclerview;
    private Spinner spinner;
    private Button btn_show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cul_her, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        spinner = (Spinner) view.findViewById(R.id.spr_types);
        btn_show = (Button) view.findViewById(R.id.btn_show);

        if(MainActivity.language.equals("kz"))
        {
            btn_show.setText(getString(R.string.Kz_btn_show));
        }else
        {
            btn_show.setText(getString(R.string.Ru_btn_show));
        }

        setlngtypes();
        sel_types = types_1[0];

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, types_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sel_types = types_1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = types_2[Arrays.asList(types_1).indexOf(sel_types)];
                new FbsDatabaseHelper("",false).readSightofTypes(type, new FbsDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Sight> sights, List<String> keys) {
                        List_Attract_Adapter list_attract_adapter = new List_Attract_Adapter(getContext(), sights, new OnItemClickListener() {
                            @Override
                            public void onItemClick(Sight sight) {
                                Intent intent = new Intent(getActivity(), AttractActivity.class);
                                intent.putExtra("title",sight.getTitle_kz());
                                intent.putExtra("town",sight.getTown());
                                intent.putExtra("text",sight.getText_kz());
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
                        recyclerview.setAdapter(list_attract_adapter);
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
            }
        });


        return  view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    public void setlngtypes()
    {
        if(MainActivity.language.equals("kz"))
        {
            types_1[0]="Сәулет";
            types_1[1]="Мұражай";
            types_1[2]="Тарихи";
            types_1[3]="Табиғат";
            types_1[4]="Демалыс";
        }
        else {
            types_1[0]="Архитектура";
            types_1[1]="Музей";
            types_1[2]="Историческая";
            types_1[3]="Природа";
            types_1[4]="Отдых";
        }
    }


}
