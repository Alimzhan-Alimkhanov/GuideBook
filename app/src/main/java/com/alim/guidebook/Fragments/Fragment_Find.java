package com.alim.guidebook.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.guidebook.Adapter.List_Attract_Adapter;
import com.alim.guidebook.Adapter.OnItemClickListener;
import com.alim.guidebook.AttractActivity;
import com.alim.guidebook.Helper.FbsDatabaseFindHellper;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Find extends Fragment {


    public static String[] oblst = {"Бәрі","Семей","Катон-Қарағай"};
    public static String[] types_1 = new  String[6];
    public static String[] types_2 = {"Кезгелген","Arch","Muzeum","His","Nature","relax"};

    static String tfind = "";
    static String obls_name ="";
    static String type;
    static String  sort = "";

    public Fragment_Find() {
        // Required empty public constructor
    }

    public Context _context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    Button btn_find;
    EditText edt_find;
    Button btn_filter;
    TextView txbnotfound;
    RecyclerView recviewfind;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment__find, container, false);

        btn_find = (Button) view.findViewById(R.id.btn_find);
        btn_filter = (Button) view.findViewById(R.id.btn_filter);
        edt_find = (EditText) view.findViewById(R.id.ed_find);
        txbnotfound = (TextView) view.findViewById(R.id.txnotfind);
        recviewfind = (RecyclerView) view.findViewById(R.id.recview_find);

        if(MainActivity.language.equals("kz"))
        {
            txbnotfound.setText("Түкте табылмады");
        }else {
            txbnotfound.setText("Ничего не найдено");
        }


        setlngtypes();
        type = types_1[0];
        obls_name = oblst[0];


        if(MainActivity.language.equals("kz"))
        {
            btn_find.setText(R.string.Kz_btn_find);
        }else {
            btn_find.setText(R.string.Ru_btn_find);
        }

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_filter, null);

                final Button btn_push = mView.findViewById(R.id.btnpush);
                final Spinner sp_oblst = mView.findViewById(R.id.sp_oblst);
                final Spinner sp_type = mView.findViewById(R.id.sp_type);
                final TextView txv_oblst_name = mView.findViewById(R.id.txv_oblst_name);



                if(MainActivity.language.equals("kz"))
                {
                    txv_oblst_name.setText(R.string.Kz_txv_filter_oblst);
                }else {
                    txv_oblst_name.setText(R.string.Ru_txv_filter_oblst);
                }



                ArrayAdapter<String> adapter_oblst = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, oblst);
                adapter_oblst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_oblst.setAdapter(adapter_oblst);
                sp_oblst.setSelection(Arrays.asList(oblst).indexOf(obls_name));


                ArrayAdapter<String> adapter_type = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, types_1);
                adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_type.setAdapter(adapter_type);
                sp_type.setSelection(Arrays.asList(types_1).indexOf(type));

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                sp_oblst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        obls_name = oblst[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type = types_1[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn_push.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }});




        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txbnotfound.setVisibility(View.GONE);
                tfind = edt_find.getText().toString();

                Log.d("Testlog","Find: "+tfind + " "+ obls_name + " " + type);
                new FbsDatabaseFindHellper().readSights(tfind,obls_name,type,new FbsDatabaseFindHellper.DataStatus() {

                    @Override
                    public void DataIsLoaded(List<Sight> sights, List<String> keys) {
                       List_Attract_Adapter list_attract_adapter = new List_Attract_Adapter(getContext(), sights, new OnItemClickListener() {
                            @Override
                            public void onItemClick(Sight sight) {
                                Intent intent = new Intent(getActivity(), AttractActivity.class);

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
                       recviewfind.setAdapter(list_attract_adapter);
                       if(sights.size()==0)
                       {
                         txbnotfound.setVisibility(View.VISIBLE);
                       }
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

    public void setlngtypes()
    {
        if(MainActivity.language.equals("kz"))
        {
            oblst[0]="Бәрі";

            types_1[0]="Кезгелген";
            types_1[1]="Сәулет";
            types_1[2]="Мұражай";
            types_1[3]="Тарихи";
            types_1[4]="Табиғат";
            types_1[5]="Демалыс";
        }
        else {
            oblst[0]="Все";

            types_1[0]="Любой";
            types_1[1]="Архитектура";
            types_1[2]="Музей";
            types_1[3]="Историческая";
            types_1[4]="Природа";
            types_1[5]="Отдых";
        }
    }



}
