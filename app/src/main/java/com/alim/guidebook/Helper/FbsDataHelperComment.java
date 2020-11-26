package com.alim.guidebook.Helper;

import android.support.annotation.NonNull;

import com.alim.guidebook.MyObject.Comment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDataHelperComment {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceReviews;
    private List<Comment> lcomments = new ArrayList<>();
    private String id;


    public FbsDataHelperComment(String id) {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceReviews = mDataBase.getReference("Reviews");
        this.id = id;
    }


    public interface RevDataStatus{
        void DataIsLoaded(List<Comment> lcomments, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readReviews(final FbsDataHelperComment.RevDataStatus dataStatus){
        mRefenceReviews.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lcomments.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Comment comment = keyNode.getValue(Comment.class);
                    if(comment.getId_sight().equals(id)) {
                        lcomments.add(comment);
                    }
                }

                dataStatus.DataIsLoaded(lcomments,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addReviews(Comment comment, final  RevDataStatus dataStatus)
    {
         String key = mRefenceReviews.push().getKey();
         mRefenceReviews.child(key).setValue(comment)
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {
                         dataStatus.DataIsInserted();
                     }
                 });
    }

}
