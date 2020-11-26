//package com.alim.guidebook.Fragments;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.alim.guidebook.Helper.FbsDataHelperComment;
//import com.alim.guidebook.MyObject.Comment;
//import com.alim.guidebook.R;
//
//import java.util.List;
//
//public class Fragment_add extends Fragment  {
//
//    public Context _context;
//
//    public Fragment_add() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    public RecyclerView recyclerview;
//
//
//    EditText edittown;
//    EditText tit;
//    EditText editcomment;
//    EditText editdate;
//
//    Button btn_post;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_comment, container, false);
//
//        editemail = (EditText) view.findViewById(R.id.ed_email);
//        editraiting = (EditText) view.findViewById(R.id.ed_raiting);
//        editcomment = (EditText) view.findViewById(R.id.ed_comment);
//        editdate = (EditText) view.findViewById(R.id.ed_date);
//        btn_post = (Button) view.findViewById(R.id.btn_post);
//
//
//
//
//
//        btn_post.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                Comment review = new Comment();
//
//                review.setEmail(editemail.getText().toString());
//                review.setRating(Integer.valueOf(editraiting.getText().toString()));
//                review.setComment(editcomment.getText().toString());
//                review.setDate(editdate.getText().toString());
//
//
//                new FbsDataHelperComment().addReviews(review, new FbsDataHelperComment.RevDataStatus() {
//                    @Override
//                    public void DataIsLoaded(List<Comment> lreviews, List<String> keys) {
//
//                    }
//
//                    @Override
//                    public void DataIsInserted() {
//                        Toast.makeText(getContext(),"Succes",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void DataIsUpdated() {
//
//                    }
//
//                    @Override
//                    public void DataIsDeleted() {
//
//                    }
//                });
//            }
//        });
//
//
//        return  view;
//    }
//
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        _context = context;
//    }
//
//}
