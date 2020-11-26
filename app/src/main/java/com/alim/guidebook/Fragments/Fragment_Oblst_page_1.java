package com.alim.guidebook.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toolbar;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.R;

public class Fragment_Oblst_page_1 extends Fragment {



    WebView webView;

    String name;


    public Context _context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_obls_page_1, container, false);

        Button btn = view.findViewById(R.id.btn_list);
        if(MainActivity.language.equals("kz"))
        {
            btn.setText(getString(R.string.Kz_btn_list_attract));
        }else{
            btn.setText(getString(R.string.Ru_btn_list_attract));
        }

        Bundle bundle = this.getArguments();
        name = bundle.getString("keyname");
        String link = bundle.getString("keylink");


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(name);


        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Fragment_List_Attract fragment_list_attract = new Fragment_List_Attract();
                 Bundle b = new Bundle();
                 b.putString("keyname",name);
                 fragment_list_attract.setArguments(b);
                 getFragmentManager().beginTransaction().replace(R.id.container,fragment_list_attract).commit();
            }
        });

        return view;
    }
}
