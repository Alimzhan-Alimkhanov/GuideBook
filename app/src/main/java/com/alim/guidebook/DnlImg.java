package com.alim.guidebook;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;

import com.alim.guidebook.Helper.FbsDatabaseHelper;
import com.alim.guidebook.MyObject.Sight;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class DnlImg {



    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;



    public DnlImg()
    {
       storageReference = firebaseStorage.getInstance().getReference();
    }

    public interface Status{
        void DataIsLoaded(Bitmap bitmap);
    }


    public void drawicon(Sight sight, final Status status)
    {
        Log.d("Testlog",sight.getTitle_kz());
        ref = storageReference.child(sight.getId_img()+".jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Bitmap nbitmap = Bitmap.createScaledBitmap(bitmap,75,75,false);
                        nbitmap =  getCroppedBitmap(nbitmap);
                        status.DataIsLoaded(nbitmap);
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
    }

//    public void draw()
//    {
//        double cor1 = Double.valueOf("49.326474");
//        double cor2 = Double.valueOf("81.573888");
//        LatLng latLng = new LatLng(cor1, cor2);
//        markerOptions = new MarkerOptions().position(latLng).title("xd");
//
//        ref = storageReference.child("0.jpg");
//        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//
//                Log.d("Testlog",uri.toString());
//                Picasso.get().load(uri).into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        Bitmap nbitmap = Bitmap.createScaledBitmap(bitmap,75,75,false);
//                        nbitmap =  getCroppedBitmap(nbitmap);
//                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(nbitmap));
//                        map.addMarker(markerOptions);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
//            }
//        });

 //   }



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
