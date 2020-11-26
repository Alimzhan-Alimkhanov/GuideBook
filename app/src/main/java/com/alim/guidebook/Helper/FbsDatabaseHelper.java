package com.alim.guidebook.Helper;

import android.support.annotation.NonNull;

import com.alim.guidebook.MyObject.Sight;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDatabaseHelper {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceSights;
    private List<Sight> sights = new ArrayList<>();

    private String filtername;
    private boolean b;


    public FbsDatabaseHelper(String filtername,boolean b) {
       mDataBase = FirebaseDatabase.getInstance();
       mRefenceSights = mDataBase.getReference("Sights");
       this.filtername = filtername;
       this.b = b;
    }


    public interface DataStatus{
        void DataIsLoaded(List<Sight> sights,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readBooks(final  DataStatus dataStatus){
        mRefenceSights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              sights.clear();
              List<String> keys = new ArrayList<>();
              for (DataSnapshot keyNode: dataSnapshot.getChildren())
              {
                  keys.add(keyNode.getKey());
                  Sight sight = keyNode.getValue(Sight.class);
                  if(b) {
                      if (sight.getTown().equals(filtername)) {
                          sights.add(sight);
                      }
                  }else
                  {
                      sights.add(sight);
                  }
              }

              dataStatus.DataIsLoaded(sights,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void readSightofTypes(final String type, final  DataStatus dataStatus)
    {
        mRefenceSights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sights.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Sight sight = keyNode.getValue(Sight.class);
                    if (type.equals(sight.getType()))
                    {
                        sights.add(sight);
                    }
                }

                dataStatus.DataIsLoaded(sights,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
