package com.alim.guidebook.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alim.guidebook.Adapter.List_Attract_Adapter;
import com.alim.guidebook.Adapter.OnItemClickListener;
import com.alim.guidebook.AttractActivity;
import com.alim.guidebook.DnlImg;
import com.alim.guidebook.Helper.FbsDatabaseHelper;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Fragment_Item_Maps extends Fragment implements OnMapReadyCallback{

    public Fragment_Item_Maps() {
        // Required empty public constructor
        storageReference = firebaseStorage.getInstance().getReference();
    }


    private Context _context;
    private MapView mMapView;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    GoogleMap mMap;

    MarkerOptions markerOptions;
    Marker marker;

    private List<Sight> lsight;

   private Target target;


   View gview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment__item__maps, container, false);

        mMapView = (MapView) view.findViewById(R.id.fimap);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);


        gview = view;



        return  view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context =
                context;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("Testlog",marker.getTitle());

                String snbr_btn_text = "";

                if(MainActivity.language.equals("kz"))
                {
                    snbr_btn_text = "АШУ";
                }else {
                    snbr_btn_text = "ОТКРЫТЬ";
                }


                for (final Sight sht: lsight)
                {
                    String ltitle ="";

                    if(MainActivity.language.equals("kz"))
                    {
                        ltitle = sht.getTitle_kz();
                    }else {
                        ltitle = sht.getTitle_ru();

                    }

                    if(ltitle.equals(marker.getTitle()))
                    {
                        Snackbar snackbar = Snackbar.make(gview,ltitle,Snackbar.LENGTH_LONG).
                                setAction(snbr_btn_text, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(),AttractActivity.class);

                                        if(MainActivity.language.equals("kz"))
                                        {
                                            intent.putExtra("title",sht.getTitle_kz());
                                            intent.putExtra("text",sht.getText_kz());
                                        }else{
                                            intent.putExtra("title",sht.getTitle_ru());
                                            intent.putExtra("text",sht.getText_ru());
                                        }

                                        intent.putExtra("town",sht.getTown());
                                        intent.putExtra("time",sht.getTime());
                                        intent.putExtra("street",sht.getStreet());
                                        intent.putExtra("id_img",sht.getId_img());
                                        intent.putExtra("cost",sht.getCost());
                                        intent.putExtra("contact",sht.getContact());
                                        intent.putExtra("add_f1",sht.getAdd_f1());
                                        intent.putExtra("add_f2",sht.getAdd_f2());
                                        intent.putExtra("add_f3",sht.getAdd_f3());
                                        startActivity(intent);

                                    }
                                }).setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        snackbar.show();

                    }else {continue;}
                }





                return false;
            }
        });




        if(checkPermission()) {
            mMap.setMyLocationEnabled(true);
        }
        else {askPermission();}

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.694411, 81.495543), 5.0f));




        new FbsDatabaseHelper("",false).readBooks(new FbsDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Sight> sights, List<String> keys) {

                lsight = sights;

                for (final Sight sight: sights)
                {

                    double cor1 = Double.valueOf(sight.getAdd_f2());
                    double cor2 = Double.valueOf(sight.getAdd_f3());
                    LatLng latLng = new LatLng(cor1, cor2);

                    String title = "";

                    if(MainActivity.language.equals("kz"))
                    {
                        title = sight.getTitle_kz();
                    }else{
                        title = sight.getTitle_ru();
                    }

                    final MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);


                            ref = storageReference.child(sight.getId_img()+".jpg");
                   ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            target = new Target() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    Bitmap nbitmap = Bitmap.createScaledBitmap(bitmap,75,75,false);
                                    nbitmap =  getCroppedBitmap(nbitmap);
                                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(nbitmap));
                                    mMap.addMarker(markerOptions);

                                }

                                @Override
                                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {

                                }
                            };
                            Picasso.get().load(uri).into(target);
                        }
                    });
                }
            }


            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });




    }

   // Check for permission to access Location
    private boolean checkPermission() {
        //  Log.d("Testlag", "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }

    public  static final  int REQ_PERMISSION = 111;

    // Asks for permission
    private void askPermission() {
        Log.d("Testlag", "askPermission()");
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("TestLag", "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    if (checkPermission())
                        mMap.setMyLocationEnabled(true);
                } else {
                    Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                    startActivity(intent);
                }
                break;
            }
        }
    }
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


}
