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
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;
import com.alim.guidebook.directionhelpers.FetchURL;
import com.alim.guidebook.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import okhttp3.internal.connection.RouteException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Map_Page extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener
{


    public Context _context;

    private static GoogleMap mMap;
    private MapView mMapView;

    Marker marker;
    MarkerOptions markerOptions;

    Sight sight;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    private MarkerOptions place1, place2;
    private static Polyline currentPolyline;

    private double longitude = 0;
    private double latitude = 0;

    private GoogleApiClient googleApiClient;



    
    public Fragment_Map_Page(Sight sight) {
        this.sight = sight;
        storageReference = firebaseStorage.getInstance().getReference();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }


    Button btndirect;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_page, container, false);

        btndirect = view.findViewById(R.id.btn_direct);

        if(MainActivity.language.equals("kz")) {
            btndirect.setText(getString(R.string.Kz_btn_direct));
        }else { btndirect.setText(getString(R.string.Ru_btn_direct));  }

        mMapView = (MapView) view.findViewById(R.id.fmap);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

       
        mMapView.getMapAsync(this);



        return  view;
    }

  

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;


        if(checkPermission()) {
            mMap.setMyLocationEnabled(true);
        }
        else {askPermission();}


//        LatLng start = new LatLng(18.015365, -77.499382);
//        LatLng waypoint= new LatLng(18.01455, -77.499333);
//        LatLng end = new LatLng(18.012590, -77.500659);
//
//        Routing routing = new Routing.Builder()
//                .travelMode(Routing.TravelMode.WALKING)
//                .withListener(this)
//                .waypoints(start, waypoint, end)
//                .key(getResources().getString(R.string.google_api_key))
//                .build();
//        routing.execute();




          float cor1 = Float.parseFloat(sight.getAdd_f2());
          float cor2 = Float.parseFloat(sight.getAdd_f3());


        final LatLng point = new LatLng( cor1, cor2);
        markerOptions = new MarkerOptions().position(point).title(sight.getTitle_kz());


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15.0f));


        ref = storageReference.child(sight.getId_img()+".jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        Log.d("Testlog","B:"+bitmap.toString());

                        Bitmap nbitmap = Bitmap.createScaledBitmap(bitmap,100,100,false);

                        nbitmap =  getCroppedBitmap(nbitmap);

                        mMap.addMarker(new MarkerOptions().position(point).title(sight.getTitle_kz())
                        .icon(BitmapDescriptorFactory.fromBitmap(nbitmap))
                        );


                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });


        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(getContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
               latitude = location.getLatitude();
               longitude = location.getLongitude();
               //Log.d("Testlog",String.valueOf(latitude + "  " +longitude));
            }
        });

        LatLng nlatlang = new LatLng(latitude,longitude);

        place1 = new MarkerOptions().position(nlatlang).title("Location 1");
        place2 = markerOptions;

        mMap.addMarker(place1);
        mMap.addMarker(place2);



        btndirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchURL().execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            }
        });

    }

    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
        }
    }





    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }



    public static void DrawLine(PolylineOptions polylineOptions)
    {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) polylineOptions);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


//    @Override
//    public void onRoutingFailure(RouteException e) {
//        if(e != null) {
//            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//            Log.d("Testlog","Error: " + e.getMessage());
//        }else {
//            Toast.makeText(getContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onRoutingStart() {
//
//    }
//
//    @Override
//    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
//
//    }
//
//    @Override
//    public void onRoutingCancelled() {
//
//    }
}