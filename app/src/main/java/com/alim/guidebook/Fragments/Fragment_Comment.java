package com.alim.guidebook.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.guidebook.Adapter.F_Comment_Adater;
import com.alim.guidebook.Helper.FbsDataHelperComment;
import com.alim.guidebook.Helper.FbsDatabaseHelper;
import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Comment;
import com.alim.guidebook.MyObject.Sight;
import com.alim.guidebook.R;

import java.util.Date;
import java.util.List;

public class Fragment_Comment extends Fragment {

    public Context _context;

    Sight sight;

    public Fragment_Comment(Sight sight) {
        this.sight =sight;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    private RecyclerView recyclerView;
    private TextView textmessage;
    private Button btn_form;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        textmessage = (TextView) view.findViewById(R.id.txv_message);
        btn_form = (Button) view.findViewById(R.id.btn_form);

        if(MainActivity.language.equals("kz"))
        {
           textmessage.setText(getString(R.string.Kz_txv_notcomment));
           btn_form.setText(R.string.Kz_btn_comment);
        }else {
            textmessage.setText(getString(R.string.Ru_txv_notcomment));
            btn_form.setText(R.string.Ru_btn_comment);
        }


        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                final TextView txv_title = (TextView) mView.findViewById(R.id.textView);
                final EditText edEmail = (EditText) mView.findViewById(R.id.etEmail);
                final EditText edLogin = (EditText) mView.findViewById(R.id.etLogin);
                final EditText edCom = (EditText) mView.findViewById(R.id.ed_com);
                final RatingBar ratingBar = (RatingBar) mView.findViewById(R.id.ratingBar_default);

                Button btn_push = (Button) mView.findViewById(R.id.btnpush);

                if(MainActivity.language.equals("kz"))
                {
                    txv_title.setText(R.string.Kz_txv_comment_title);
                    edLogin.setHint(getString(R.string.Kz_ed_comment_fio));
                    edCom.setHint(getString(R.string.Kz_ed_comment));
                    btn_push.setText(R.string.Kz_btn_comment_push);
                }else {
                    txv_title.setText(R.string.Ru_txv_comment_title);
                    edLogin.setHint(getString(R.string.Ru_ed_comment_fio));
                    edCom.setHint(getString(R.string.Ru_ed_comment));
                    btn_push.setText(R.string.Ru_btn_comment_push);
                }


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();



                btn_push.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edEmail.getText().toString().isEmpty() || edLogin.getText().toString().isEmpty() || edCom.getText().toString().isEmpty() ||
                                (ratingBar.getRating()==0)){
                             if(MainActivity.language.equals("kz")) {
                                 Toast.makeText(getContext(), "Барлығы толық емес", Toast.LENGTH_LONG).show();
                             }else{Toast.makeText(getContext(), "Не всё заполнено", Toast.LENGTH_LONG).show();}
                        }
                        else{

                            Comment comment = new Comment();
                            comment.setComment(edCom.getText().toString());
                            comment.setEmail(edEmail.getText().toString());
                            comment.setLogin(edLogin.getText().toString());
                            comment.setId_sight(sight.getId_img());
                            comment.setRating(String.valueOf(Math.round(ratingBar.getRating())));
                            Date d = new Date();
                            CharSequence s  = DateFormat.format("dd.MM.yyyy", d.getTime());
                            Log.d("Testlog",s.toString());
                            comment.setDate(s.toString());

                            new FbsDataHelperComment("").addReviews(comment,new FbsDataHelperComment.RevDataStatus() {
                                @Override
                                public void DataIsLoaded(List<Comment> lreviews, List<String> keys) {

                                }

                                @Override
                                public void DataIsInserted() {
                                    if(MainActivity.language.equals("kz"))
                                    {
                                        Toast.makeText(getContext(), "Жіберілді", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getContext(), "Отправленно", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void DataIsUpdated() {

                                }

                                @Override
                                public void DataIsDeleted() {

                                }
                            });


                            dialog.dismiss();
                        }
                    }
                });



            }
        });



        new FbsDataHelperComment(sight.getId_img()).readReviews(new FbsDataHelperComment.RevDataStatus() {
            @Override
            public void DataIsLoaded(List<Comment> lcomments, List<String> keys) {

                if(getActivity()!= null) {
                    F_Comment_Adater f_comment_adater = new F_Comment_Adater(textmessage, getActivity(), lcomments);
                    recyclerView.setAdapter(f_comment_adater);
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


        return  view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }
}



