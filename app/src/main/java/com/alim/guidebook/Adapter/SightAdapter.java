package com.alim.guidebook.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import java.util.List;

public class SightAdapter extends RecyclerView.Adapter<SightAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<Sight> sights;

    public  SightAdapter(Context context, List<Sight> sights) {
        this.sights = sights;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_sight, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(SightAdapter.ViewHolder holder, int position) {
        Sight lsight = sights.get(position);
        holder.txv.setText(lsight.getTown());

    }


    @Override
    public int getItemCount() {
        return sights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv;
        ViewHolder(View view){
            super(view);
            txv = (TextView) view.findViewById(R.id.txv);
        }
    }






}
