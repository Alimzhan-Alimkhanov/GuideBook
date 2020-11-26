package com.alim.guidebook.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alim.guidebook.MyObject.Comment;
import com.alim.guidebook.R;


import java.util.List;

public class F_Comment_Adater extends RecyclerView.Adapter<F_Comment_Adater.ViewHolder> {


    private LayoutInflater inflater;
    private List<Comment> lcomments;

    private TextView txv;

    public F_Comment_Adater(TextView txv,Context context, List<Comment> comments) {
        this.lcomments = comments;
        this.inflater = LayoutInflater.from(context);
        this.txv = txv;
        if(comments.size()==0){txv.setVisibility(View.VISIBLE);}
        else {txv.setVisibility(View.GONE);}
    }

    @Override
    public F_Comment_Adater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new F_Comment_Adater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final F_Comment_Adater.ViewHolder holder, int position) {

        Comment comment = lcomments.get(position);

        holder.txv_text.setText(comment.getComment());
        holder.txv_email.setText(comment.getEmail());
        holder.txv_date.setText(comment.getDate());
        holder.txv_raiting.setText(String.valueOf(comment.getRating()));
    }




    @Override
    public int getItemCount() {
        return lcomments.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_text,txv_email,txv_date,txv_raiting;
        ViewHolder(View view){
            super(view);
            txv_text = (TextView) view.findViewById(R.id.txv_comment);
            txv_email = (TextView) view.findViewById(R.id.txv_mail);
            txv_date = (TextView) view.findViewById(R.id.txv_date);
            txv_raiting = (TextView) view.findViewById(R.id.txv_rait);
        }

    }
}
