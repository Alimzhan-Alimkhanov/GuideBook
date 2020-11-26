package com.alim.guidebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alim.guidebook.Adapter.FragmentTabsAdapter;
import com.alim.guidebook.MyObject.Sight;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AttractActivity extends AppCompatActivity{



    private ActionBar actionBar;
    private AppBarLayout appbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attract_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();



        Intent intent = getIntent();

        String title =      intent.getStringExtra("title");
        String town =      intent.getStringExtra("town");
        String text  =      intent.getStringExtra("text");
        String time  =      intent.getStringExtra("time");
        String street =     intent.getStringExtra("street");
        String id_img =     intent.getStringExtra("id_img");
        String cost   =     intent.getStringExtra("cost");
        String contact =    intent.getStringExtra("contact");
        String add_f1 =    intent.getStringExtra("add_f1");
        String add_f2 =    intent.getStringExtra("add_f2");
        String add_f3 =    intent.getStringExtra("add_f3");

        actionBar.setTitle(town);

        Sight sight = new Sight();

        sight.setTitle_kz(title);
        sight.setText_kz(text);
        sight.setTime(time);
        sight.setStreet(street);
        sight.setId_img(id_img);
        sight.setCost(cost);
        sight.setContact(contact);
        sight.setAdd_f1(add_f1);
        sight.setAdd_f2(add_f2);
        sight.setAdd_f3(add_f3);

        appbar = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = new TabLayout(this);
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        viewpager = findViewById(R.id.viewpager);
        viewpager.setAdapter(new FragmentTabsAdapter(sight,gettabs(),getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        appbar.addView(tabLayout);








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed(); // Or what ever action you want here.
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void click(View view)
    {
        Intent intent = new Intent(AttractActivity.this,MapsActivity.class);
        startActivity(intent);
    }

    public String[] gettabs()
    {
        String[] ltabs = new String[3];
        if(MainActivity.language.equals("kz"))
        {
            ltabs[0]  = getString(R.string.kz_tabs_info);
            ltabs[1]  = getString(R.string.tabs_map);
            ltabs[2]  = getString(R.string.kz_tabs_comment);
        }else{
            ltabs[0]  = getString(R.string.ru_tabs_info);
            ltabs[1]  = getString(R.string.tabs_map);
            ltabs[2]  = getString(R.string.ru_tabs_comment);
        }

        return ltabs;
    }


}
