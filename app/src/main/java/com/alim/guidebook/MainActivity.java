package com.alim.guidebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alim.guidebook.Fragments.Fragment_Comment;
import com.alim.guidebook.Fragments.Fragment_CulHer;
import com.alim.guidebook.Fragments.Fragment_Find;
import com.alim.guidebook.Fragments.Fragment_Info;
import com.alim.guidebook.Fragments.Fragment_Item_Maps;
import com.alim.guidebook.Fragments.Fragment_OBLST;
import com.alim.guidebook.Fragments.Fragment_Setings;
import com.alim.guidebook.Fragments.Fragment_Tour;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public  ActionBar actionBar;

    private Fragment_OBLST fragment_oblst;

    private Fragment_CulHer fragment_culHer;

    private Fragment_Find fragment_find;

    private Fragment_Item_Maps fragment_item_maps;

    private Fragment_Tour fragment_tour;

    private Fragment_Setings fragment_setings;

    public static String language;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.setBackgroundColor(Color.BLACK);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String langsave = sharedPreferences.getString("Language", "null");

        if (langsave.equals("null")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Language", "kz");
            editor.commit();
            language = "kz";
        } else {
            language = langsave;
        }

        Log.d("Testlog", langsave);

        fragment_oblst = new Fragment_OBLST();
        fragment_culHer = new Fragment_CulHer();
        fragment_find = new Fragment_Find();
        fragment_item_maps = new Fragment_Item_Maps();
        fragment_tour = new Fragment_Tour();
        fragment_setings = new Fragment_Setings();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_oblst).commit();
        if (language.equals("kz")) {
            actionBar.setTitle(getString(R.string.Kz_fragment_OBLST));
        } else {
            actionBar.setTitle(getString(R.string.Ru_fragment_OBLST));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView txv_maintitle = headerView.findViewById(R.id.txv_maintitle);
        if (language.equals("kz")) {
            txv_maintitle.setText(getString(R.string.Kz_VKO));
        } else {
            txv_maintitle.setText(getString(R.string.Ru_VKO));
        }


        Menu menu = navigationView.getMenu();

        if (language.equals("kz")) {

            menu.findItem(R.id.id_fragment_OBLST).setTitle(getString(R.string.Kz_fragment_OBLST));
            menu.findItem(R.id.id_fragment_CulHer).setTitle(getString(R.string.Kz_fragment_culher));
            menu.findItem(R.id.id_fragment_FinDer).setTitle(getString(R.string.Kz_fragment_finder));
            menu.findItem(R.id.id_fragment_tur).setTitle(getString(R.string.Kz_fragment_tour));
            menu.findItem(R.id.id_fragment_Setting).setTitle(getString(R.string.Kz_fragment_setting));
            menu.findItem(R.id.id_fragment_InfoApp).setTitle(getString(R.string.Kz_fragment_infoapp));
            menu.findItem(R.id.id_exit).setTitle(getString(R.string.Kz_exit));

        }else {
            menu.findItem(R.id.id_fragment_OBLST).setTitle(getString(R.string.Ru_fragment_OBLST));
            menu.findItem(R.id.id_fragment_CulHer).setTitle(getString(R.string.Ru_fragment_culher));
            menu.findItem(R.id.id_fragment_FinDer).setTitle(getString(R.string.Ru_fragment_finder));
            menu.findItem(R.id.id_fragment_tur).setTitle(getString(R.string.Ru_fragment_tour));
            menu.findItem(R.id.id_fragment_Setting).setTitle(getString(R.string.Ru_fragment_setting));
            menu.findItem(R.id.id_fragment_InfoApp).setTitle(getString(R.string.Ru_fragment_infoapp));
            menu.findItem(R.id.id_exit).setTitle(getString(R.string.Ru_exit));

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        FragmentManager fragmentManager = getSupportFragmentManager();


        Fragment_Info fragment_info = new Fragment_Info();





        switch (item.getItemId())
        {

            case R.id.id_fragment_OBLST: fragmentManager.beginTransaction().replace(R.id.container,fragment_oblst).commit();
            if(language.equals("kz")) {
                actionBar.setTitle(getString(R.string.Kz_fragment_OBLST));
            }else {actionBar.setTitle(getString(R.string.Ru_fragment_OBLST));}
            break;

            case R.id.id_fragment_CulHer: fragmentManager.beginTransaction().replace(R.id.container,fragment_culHer).commit();
                if(language.equals("kz")) {
                    actionBar.setTitle(getString(R.string.Kz_fragment_culher));
                }else{actionBar.setTitle(getString(R.string.Ru_fragment_culher));}
            break;


            case  R.id.id_fragment_FinDer: fragmentManager.beginTransaction().replace(R.id.container,fragment_find).commit();
                if(language.equals("kz")) {
                    actionBar.setTitle(getString(R.string.Kz_fragment_finder));
                }else{actionBar.setTitle(getString(R.string.Ru_fragment_finder));}
            break;


            case R.id.id_fragment_Map: fragmentManager.beginTransaction().replace(R.id.container,fragment_item_maps).commit();
            actionBar.setTitle(getString(R.string.fragment_map));
            break;

            case R.id.id_fragment_tur: fragmentManager.beginTransaction().replace(R.id.container,fragment_tour).commit();
                if(language.equals("kz")) {
                    actionBar.setTitle(getString(R.string.Kz_fragment_tour));
                }else{actionBar.setTitle(getString(R.string.Ru_fragment_tour));}
            break;

            case R.id.id_fragment_Setting: fragmentManager.beginTransaction().replace(R.id.container,fragment_setings).commit();
                if(language.equals("kz")) {
                    actionBar.setTitle(getString(R.string.Kz_fragment_setting));
                }else{actionBar.setTitle(getString(R.string.Ru_fragment_setting));}
            break;

            case R.id.id_fragment_InfoApp: fragmentManager.beginTransaction().replace(R.id.container,fragment_info).commit();
                if(language.equals("kz")) {
                    actionBar.setTitle(getString(R.string.Kz_fragment_infoapp));
                }else{actionBar.setTitle(getString(R.string.Ru_fragment_infoapp));}
            break;

            case R.id.id_exit:
                Intent intent = new Intent(MainActivity.this, Exit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                finish();
                startActivity(intent);
            break;


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
