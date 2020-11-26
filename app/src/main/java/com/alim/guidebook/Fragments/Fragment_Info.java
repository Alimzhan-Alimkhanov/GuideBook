package com.alim.guidebook.Fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Fragment_Info extends Fragment {



    public Fragment_Info() {
        // Required empty public constructor
    }


    private Context _context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    TextView txv_info;
    TextView txv_version;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        txv_info = (TextView) view.findViewById(R.id.txv_info);
        txv_version = (TextView) view.findViewById(R.id.txv_version);

        if(MainActivity.language.equals("kz"))
        {
            txv_info.setText(getString(R.string.Kz_info_app_1));
            txv_version.setText(getString(R.string.Kz_version));

        }else{
            txv_info.setText(getString(R.string.Ru_info_app_1));
            txv_version.setText(getString(R.string.Ru_version));
        }


        return  view;
    }

}
