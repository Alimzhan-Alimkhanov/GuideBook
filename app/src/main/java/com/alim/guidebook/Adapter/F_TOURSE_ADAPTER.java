package com.alim.guidebook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alim.guidebook.MainActivity;
import com.alim.guidebook.MyObject.Comment;
import com.alim.guidebook.MyObject.Tour;
import com.alim.guidebook.R;

import java.util.List;

public class F_TOURSE_ADAPTER extends RecyclerView.Adapter<F_TOURSE_ADAPTER.ViewHolder> {

        private LayoutInflater inflater;
        private List<Tour> ltours;
        private Context _context;


        public F_TOURSE_ADAPTER(Context context, List<Tour> tours) {
            this.ltours = tours;
            this._context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public F_TOURSE_ADAPTER.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.iitem_tours, parent, false);
            return new F_TOURSE_ADAPTER.ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(final F_TOURSE_ADAPTER.ViewHolder holder, int position) {


        final Tour tour = ltours.get(position);

        holder.txv_title.setText(tour.getTitle());
        holder.txv_cost.setText(tour.getCost());
        holder.txv_time.setText(tour.getTime());

        if(MainActivity.language.equals("kz"))
        {
            holder.txv_cost1.setText(_context.getString(R.string.Kz_txv_cost));
            holder.txv_time1.setText(_context.getString(R.string.Kz_txv_time));
            holder.btn_link.setText(_context.getString(R.string.Kz_btn_link));
        }else{
            holder.txv_cost1.setText(_context.getString(R.string.Ru_txv_cost));
            holder.txv_time1.setText(_context.getString(R.string.Ru_txv_time));
            holder.btn_link.setText(_context.getString(R.string.Ru_btn_link));
        }

        holder.btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(tour.getLink()));
                _context.startActivity(intent);
            }
        });



    }




    @Override
        public int getItemCount() {
            return ltours.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView txv_title,txv_cost,txv_time;
            final TextView txv_cost1,txv_time1;
            final Button btn_link;
            ViewHolder(View view){
                super(view);
                txv_title = (TextView) view.findViewById(R.id.txv_title);
                txv_cost = (TextView) view.findViewById(R.id.txv_cost2);
                txv_time = (TextView) view.findViewById(R.id.txv_time2);
                btn_link = (Button) view.findViewById(R.id.btn_link);

                txv_cost1 = (TextView) view.findViewById(R.id.txv_cost1);
                txv_time1 = (TextView) view.findViewById(R.id.txv_time1);


            }

        }


}
