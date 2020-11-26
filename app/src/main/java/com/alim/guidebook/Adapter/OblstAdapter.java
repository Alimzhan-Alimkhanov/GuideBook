package com.alim.guidebook.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.OblstClass;
import com.alim.guidebook.R;

import java.util.List;

public class OblstAdapter extends ArrayAdapter<OblstClass> {

    private LayoutInflater inflater;
    private int layout;
    private List<OblstClass> oblst_list;

    public OblstAdapter(Context context, int resource, List<OblstClass> list) {
        super(context, resource, list);
        this.oblst_list = list;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=  inflater.inflate(this.layout, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.oblst_picture);
        TextView txt_name = (TextView) view.findViewById(R.id.oblst_text);

        OblstClass oblstClass = oblst_list.get(position);

        imageView.setImageResource(oblstClass.getImage());
        Log.d("Testlog",oblstClass.getRu_name());

        if(MainActivity.language.equals("kz")) {
            txt_name.setText(oblstClass.getName());
           // Log.d("Testlog","Settextkz");
        }else {
            txt_name.setText(oblstClass.getRu_name());
            //Log.d("Testlog","Settextru");
        }

        return view;
    }

}
