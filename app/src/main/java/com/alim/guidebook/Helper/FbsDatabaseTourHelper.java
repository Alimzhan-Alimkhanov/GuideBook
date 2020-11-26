package com.alim.guidebook.Helper;

import android.support.annotation.NonNull;

import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.MyObject.Tour;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDatabaseTourHelper {


    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceSights;
    private List<Tour> tours = new ArrayList<>();


    public FbsDatabaseTourHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceSights = mDataBase.getReference("Tours");
    }


    public interface DataStatus{
        void DataIsLoaded(List<Tour> tours,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readTours(final FbsDatabaseTourHelper.DataStatus dataStatus){
        mRefenceSights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tours.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Tour tour = keyNode.getValue(Tour.class);
                    tours.add(tour);
                }

                dataStatus.DataIsLoaded(tours,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
