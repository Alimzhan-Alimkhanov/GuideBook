package com.alim.guidebook.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ImgAdapter extends PagerAdapter {

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    private Context _context;
    private int countimg;
    private int id_img;

    public ImgAdapter(Context context,int countimg,int id_img) {
        _context = context;
        this.countimg = countimg;
        this.id_img = id_img;
        storageReference = firebaseStorage.getInstance().getReference();
    }

    @Override
    public int getCount() {
        return countimg;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = new ImageView(_context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


       // ref = storageReference.child("DetailImages/"+id_img +"-"+ position + ".jpg");
        ref = storageReference.child("DetailImages/"+id_img+"-"+position+".jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        });


        container.addView(imageView, 0);



        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

}
