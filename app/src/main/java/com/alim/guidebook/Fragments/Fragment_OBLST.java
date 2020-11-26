package com.alim.guidebook.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alim.guidebook.Adapter.OblstAdapter;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.OblstClass;
import com.alim.guidebook.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_OBLST extends Fragment {


    private static List<OblstClass> list = new ArrayList<>();
    private Context _context;
    private ListView oblstlistview;
    public  static boolean check = false;

    public Fragment_OBLST() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        _context=context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__oblst_layout, container, false);

        if (!check){setInitialData(); check=true;}
        oblstlistview = view.findViewById(R.id.listView);
        OblstAdapter oblstAdapter = new OblstAdapter(_context,R.layout.oblst_list_item,list);
        oblstlistview.setAdapter(oblstAdapter);
        AdapterView.OnItemClickListener itemclicklistener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OblstClass oblstclass = list.get(position);

                Fragment_Oblst_page_1 fragment_oblst_page_1 = new Fragment_Oblst_page_1();
                Bundle bundle = new Bundle();
                bundle.putString("keyname",oblstclass.getName());
                bundle.putString("keylink",oblstclass.getLink());
                fragment_oblst_page_1.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container,fragment_oblst_page_1).addToBackStack(null).commit();
            }
        };

        oblstlistview.setOnItemClickListener(itemclicklistener);
        return view;
    }


    private  void setInitialData()
    {
        if(MainActivity.language.equals("kz")) {
            list.add(new OblstClass("Семей", "Семей", R.drawable.semei, "217 км", getResources().getString(R.string.kz_link_oblst_info_semei)));
            list.add(new OblstClass("Катон-Қарағай", "Катон-Карагай", R.drawable.katon, "330 км", getResources().getString(R.string.kz_link_oblst_info_katon)));
            list.add(new OblstClass("Күршім", "Курчум", R.drawable.kurshum, "212 км", getResources().getString(R.string.ru_link_oblst_info_kurshum)));
            list.add(new OblstClass("Тарбағатай", "Тарбагатай", R.drawable.tarbagatai, "310 км", getResources().getString(R.string.ru_link_oblst_info_tarbagatai)));
        }else{
            list.add(new OblstClass("Семей", "Семей", R.drawable.semei, "217 км", getResources().getString(R.string.ru_link_oblst_info_semei)));
            list.add(new OblstClass("Катон-Қарағай", "Катон-Карагай", R.drawable.katon, "330 км", getResources().getString(R.string.ru_link_oblst_info_katon)));
            list.add(new OblstClass("Күршім", "Курчум", R.drawable.kurshum, "212 км", getResources().getString(R.string.ru_link_oblst_info_kurshum)));
            list.add(new OblstClass("Тарбағатай", "Тарбагатай", R.drawable.tarbagatai, "310 км", getResources().getString(R.string.ru_link_oblst_info_tarbagatai)));
        }
    }
}

