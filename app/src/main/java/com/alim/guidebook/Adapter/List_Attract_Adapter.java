package com.alim.guidebook.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class List_Attract_Adapter extends RecyclerView.Adapter<List_Attract_Adapter.ViewHolder>
{


    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    private LayoutInflater inflater;
    private List<Sight> sights;


    private final OnItemClickListener listener;

    public List_Attract_Adapter(Context context, List<Sight> sights,OnItemClickListener listener) {
        this.sights = sights;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        storageReference = firebaseStorage.getInstance().getReference();


    }
    @Override
    public List_Attract_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_attract, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final List_Attract_Adapter.ViewHolder holder, int position) {

        Sight sight = sights.get(position);

        String ntxt = "";

        if(MainActivity.language.equals("kz")) {
                ntxt = sight.getTitle_kz();
        }else {  ntxt = sight.getTitle_ru();   }

        if (ntxt.length() > 28) {
            ntxt = ntxt.substring(0, 25) + "...";
        }
        holder.txv_title.setText(ntxt);


            ref = storageReference.child(sight.getId_img() + ".jpg");
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(holder.img);
                }
            });


            holder.bind(sight, listener);
        }




    @Override
    public int getItemCount() {
        return sights.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView txv_title;
        ViewHolder(View view){
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            txv_title = (TextView) view.findViewById(R.id.title);
        }

        public void bind(final Sight item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }


}
