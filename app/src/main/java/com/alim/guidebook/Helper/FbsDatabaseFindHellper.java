package com.alim.guidebook.Helper;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.guidebook.Fragments.Fragment_Find;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Sight;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FbsDatabaseFindHellper {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceSights;
    private List<Sight> sights = new ArrayList<>();



    public FbsDatabaseFindHellper() {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceSights = mDataBase.getReference("Sights");
    }


    public interface DataStatus{
        void DataIsLoaded(List<Sight> sights,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readSights(final String tfind, final String oblst_name, final String type, final FbsDatabaseFindHellper.DataStatus dataStatus){
        mRefenceSights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sights.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Sight sight = keyNode.getValue(Sight.class);

                 //   Log.d("Testlog","Findtexts: " + tfind + " , " + oblst_name + " , " + type);
                   // Log.d("Testlog","FindFindarrays: " + tfind + " , " + Fragment_Find.oblst[0] + " , " + Fragment_Find.types_1[0]);

                    String findtitle = "";

                    if(MainActivity.language.equals("kz"))
                    {
                        findtitle = sight.getTitle_ru();
                    }else {
                        findtitle = sight.getTitle_ru();
                    }

                    findtitle = findtitle.toLowerCase();


                  //  Log.d("Testlog","Findtitle: "+ findtitle);


                    if (tfind.equals("") && oblst_name.equals(Fragment_Find.oblst[0]) && type.equals(Fragment_Find.types_1[0]))
                    {Log.d("Testlog","nothing");break;}


                    if(tfind.equals("") && !oblst_name.equals(Fragment_Find.oblst[0]) && type.equals(Fragment_Find.types_1[0] ))
                    {
                        if (sight.getTown().equals(oblst_name))
                        {
                            sights.add(sight);
                        }
                    }

                    if(tfind.equals("") && oblst_name.equals(Fragment_Find.oblst[0]) && !type.equals(Fragment_Find.types_1[0] ))
                    {
                        int index = Arrays.asList(Fragment_Find.types_1).indexOf(type);
                        if (sight.getType().equals(Fragment_Find.types_2[index]))
                        {
                            sights.add(sight);
                        }
                    }

                    if(tfind.equals("") && !oblst_name.equals(Fragment_Find.oblst[0]) && !type.equals(Fragment_Find.types_1[0] ))
                    {
                        int index = Arrays.asList(Fragment_Find.types_1).indexOf(type);
                        if (sight.getTown().equals(oblst_name) && sight.getType().equals(Fragment_Find.types_2[index]))
                        {
                            sights.add(sight);
                        }
                    }

                    if(!tfind.equals("") && oblst_name.equals(Fragment_Find.oblst[0]) && type.equals(Fragment_Find.types_1[0]))
                    {
                        if(findtitle.contains(tfind.toLowerCase()))
                        {
                            Log.d("Testlog","Findonlyfindtext: "+tfind + " , " + oblst_name + " , " + type);
                            sights.add(sight);
                        }
                    }
                    if(!tfind.equals("") && !oblst_name.equals(Fragment_Find.oblst[0]) && type.equals(Fragment_Find.types_1[0]))
                    {
                        if(findtitle.contains(tfind.toLowerCase()) && sight.getTown().equals(oblst_name))
                        {
                            sights.add(sight);
                        }
                    }
                    if(!tfind.equals("") && oblst_name.equals(Fragment_Find.oblst[0]) && !type.equals(Fragment_Find.types_1[0]))
                    {
                        int index = Arrays.asList(Fragment_Find.types_1).indexOf(type);
                        if(findtitle.contains(tfind.toLowerCase()) && sight.getType().equals(Fragment_Find.types_2[index]))
                        {
                            sights.add(sight);
                        }
                    }
                    if(!tfind.equals("") && !oblst_name.equals(Fragment_Find.oblst[0]) && !type.equals(Fragment_Find.types_1[0]))
                    {
                        int index = Arrays.asList(Fragment_Find.types_1).indexOf(type);
                        if(findtitle.contains(tfind.toLowerCase()) && sight.getTown().equals(oblst_name) && sight.getType().equals(Fragment_Find.types_2[index]))
                        {
                            sights.add(sight);
                        }
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
