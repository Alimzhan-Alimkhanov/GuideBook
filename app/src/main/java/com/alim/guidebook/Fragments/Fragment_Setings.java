package com.alim.guidebook.Fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Setings extends Fragment implements View.OnClickListener {


    public Fragment_Setings() {
        // Required empty public constructor
    }

    private RadioButton radiobtn_kz;
    private RadioButton radiobtn_ru;
    private TextView txv_lang;

    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__setings, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Log.d("Testlog",sharedPreferences.getString("Language","null"));
        Log.d("Testlog","MainAc:"+ MainActivity.language);


        radiobtn_kz = view.findViewById(R.id.radio_kz);
        radiobtn_ru = view.findViewById(R.id.radio_ru);
        txv_lang = view.findViewById(R.id.txv_lang);

        setlanguage();



        radiobtn_kz.setOnClickListener(this);
        radiobtn_ru.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton)v;

        SharedPreferences.Editor editor = sharedPreferences.edit();


        //Хз код взят из stackoverfllow
        Intent mStartActivity = new Intent(getActivity(), MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(), mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        switch (rb.getId())
        {
            case R.id.radio_kz:
                editor.putString("Language","kz");
                editor.commit();

                //Хз код взят из stackoverfllow
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            break;
            case R.id.radio_ru:
                editor.putString("Language","ru");
                editor.commit();

                //Хз код взят из stackoverfllow
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            break;

        }


    }

    public void setlanguage()
    {
        if(MainActivity.language.equals("kz"))
        {
            txv_lang.setText(getString(R.string.Kz_Language_text_name));
            radiobtn_ru.setText(getString(R.string.KZ_Language_Name_ru));
            radiobtn_kz.setText(getString(R.string.Kz_Language_Name_kz));
            radiobtn_kz.setChecked(true);
        }else{
            txv_lang.setText(getString(R.string.Ru_Language_text_name));
            radiobtn_ru.setText(getString(R.string.Ru_Language_Name_ru));
            radiobtn_kz.setText(getString(R.string.Ru_Language_Name_kz));
            radiobtn_ru.setChecked(true);
        }

    }




}
