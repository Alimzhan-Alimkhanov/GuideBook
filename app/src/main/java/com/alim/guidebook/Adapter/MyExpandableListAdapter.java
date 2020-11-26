package com.alim.guidebook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.alim.guidebook.R;

/**
 * Created by dmitry on 22.04.15.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;

    public MyExpandableListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View groupView, ViewGroup parent) {
        if (groupView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = inflater.inflate(R.layout.listview_groupview, null);
        }

        switch (groupPosition) {
            case 0: {
                ((TextView) groupView.findViewById(R.id.question)).setText("Group 1");
            } break;

            case 1: {
                ((TextView) groupView.findViewById(R.id.question)).setText("Group 2");
            } break;

            case 2: {
                ((TextView) groupView.findViewById(R.id.question)).setText("Group 3");
            } break;
        }

        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        childView = inflater.inflate(R.layout.listview_groupview_childview, null);

        switch (groupPosition) {
            case 0: {
                switch (childPosition) {
                    case 0: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 1 child 1");
                    } break;

                    case 1: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 1 child 2");
                    } break;

                    case 2: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 1 child 3");
                    } break;
                }
            } break;

            case 1: {
                switch (childPosition) {
                    case 0: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 2 child 1");
                    } break;

                    case 1: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 2 child 2");
                    } break;

                    case 2: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 2 child 3");
                    } break;
                }
            } break;

            case 2: {
                switch (childPosition) {
                    case 0: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 3 child 1");
                    } break;

                    case 1: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 3 child 2");
                    } break;

                    case 2: {
                        ((TextView) childView.findViewById(R.id.answer)).setText("Group 3 child 3");
                    } break;
                }
            } break;
        }

        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
