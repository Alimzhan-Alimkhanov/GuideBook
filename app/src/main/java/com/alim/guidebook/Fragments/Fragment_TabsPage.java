//package com.alim.guidebook.Fragments;
//
//
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.design.widget.AppBarLayout;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.alim.guidebook.Adapter.FragmentTabsAdapter;
//import com.alim.guidebook.R;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class Fragment_TabsPage extends Fragment {
//
//
//    public Fragment_TabsPage() {
//        // Required empty public constructor
//    }
//
//
//
//    public AppBarLayout appbar;
//    public TabLayout tabLayout;
//    public ViewPager viewpager;
//
//
//    private Context _context;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        _context = context;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment__tabs_page, container, false);
//
//        View parent = (View) container.getParent();
//
//        appbar = (AppBarLayout) parent.findViewById(R.id.appbar);
//        tabLayout = new TabLayout(getActivity());
//        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
//        viewpager = view.findViewById(R.id.viewpager);
//        viewpager.setAdapter(new FragmentTabsAdapter(getChildFragmentManager()));
//        tabLayout.setupWithViewPager(viewpager);
//        appbar.addView(tabLayout);
//
//
//
////        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
////        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        return  view;
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        appbar.removeView(tabLayout);
//    }
//}
