package com.alim.guidebook.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alim.guidebook.Adapter.ImgAdapter;
import com.alim.guidebook.Adapter.MyExpAdapter;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Page.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Page extends Fragment {


    Sight sight;


    private Context _context;


    public int[] ImgIds;

    public  TextView title;
    public ExpandableListView exlistview;

    private  LinearLayout slider;
    private  ViewPager viewPager;
    private  int dotcount;
    private  ImageView[] dots;

    private ImgAdapter imgAdapter;





    public Fragment_Page(Sight sight) {
        this.sight = sight;
    }


    private String[] groups;
    private String[][] children;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pg, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        slider = (LinearLayout) view.findViewById(R.id.slidedots);


        title = (TextView) view.findViewById(R.id.txv_title);




        setImages();



        String t = sight.getTitle_kz();
        title.setText(t);


        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setinitialdata();
        exlistview = (ExpandableListView) view.findViewById(R.id.expanlistview);
        exlistview.setAdapter(new MyExpAdapter(getContext(),groups, children));
        //  explistview.setGroupIndicator(null);
    }


    public void setImages()
    {
        imgAdapter = new ImgAdapter(getContext(),Integer.valueOf(sight.getAdd_f1()),Integer.valueOf(sight.getId_img()));
        viewPager.setAdapter(imgAdapter);

        dotcount = imgAdapter.getCount();
        dots = new ImageView[dotcount];

        for (int i=0;i<dotcount;i++)
        {
            dots[i] = new ImageView(_context);
            dots[i].setImageDrawable(ContextCompat.getDrawable(_context,R.drawable.item_noactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            slider.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.item_active));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {

                for (int in=0;in<dotcount;in++)
                {
                    dots[in].setImageDrawable(ContextCompat.getDrawable(_context,R.drawable.item_noactive));
                }

                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.item_active));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    public void  setinitialdata()
    {
        if(MainActivity.language.equals("kz")) {
            groups = new String[]{getString(R.string.Kz_page_desctipion),
                    getString(R.string.Kz_page_time), getString(R.string.Kz_page_Street),
                    getString(R.string.Kz_page_cost), getString(R.string.Kz_page_contactnumber)};
        }else {

            groups = new String[]{getString(R.string.Ru_page_desctipion),
                    getString(R.string.Ru_page_time), getString(R.string.Ru_page_Street),
                    getString(R.string.Ru_page_cost), getString(R.string.Ru_page_contactnumber)};
        }

        children = new String [][] {
                { sight.getText_kz() },
                {sight.getTime()},
                {sight.getStreet()},
                {sight.getCost()},
                {sight.getContact()}
        };
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;

    }


}
