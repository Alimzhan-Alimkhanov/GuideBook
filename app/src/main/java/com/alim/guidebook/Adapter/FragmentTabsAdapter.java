package com.alim.guidebook.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.alim.guidebook.Fragments.Fragment_Comment;
import com.alim.guidebook.Fragments.Fragment_Map_Page;
import com.alim.guidebook.Fragments.Fragment_Page;
import com.alim.guidebook.MyObject.Sight;

import java.security.acl.LastOwnerException;

public class FragmentTabsAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    private Sight sight;

    public FragmentTabsAdapter(Sight sight,String[] ltabs, FragmentManager fm) {
        super(fm);
        this.sight = sight;
        this.tabs = ltabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Fragment_Page(sight);
            case 1: return new Fragment_Map_Page(sight);
            case 2: return new Fragment_Comment(sight);
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

}
