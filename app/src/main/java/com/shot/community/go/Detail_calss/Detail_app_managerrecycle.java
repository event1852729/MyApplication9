package com.shot.community.go.Detail_calss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Detail_app_managerrecycle extends RecyclerView.Adapter<Detail_app_managerrecycle.Myholder> {
    static Context context;
    static ArrayList<Detail_app_item_model> list_dicuss_item_model;

    public Detail_app_managerrecycle(Context context, ArrayList<Detail_app_item_model> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Detail_app_managerrecycle.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_appointment_recycle_manageritem , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {

            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Detail_app_managerrecycle.Myholder holder, int position) {
        Detail_app_item_model  detail_app_item_model= list_dicuss_item_model.get(position);
        holder.fa_name.setText(detail_app_item_model.getFa_name());
        holder.fa_status.setText("已審核");
        holder.fa_systemdate.setText(detail_app_item_model.getFa_apply_finishdate());
        holder.fa_usercount.setText(  detail_app_item_model.getFa_usercount());
        holder.fa_use_time.setText(detail_app_item_model.getFa_apply_date());
        holder.fa_usedate.setText(detail_app_item_model.getFa_usetime());
        holder.fa_applydate.setText(detail_app_item_model.getFa_apply_date2());
        holder.fa_numbername.setText(detail_app_item_model.getFa_mb_name());
    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView fa_name;
        TextView fa_usedate;
        TextView fa_usercount;
        TextView fa_use_time;
        TextView fa_status;
        TextView fa_applydate;
        TextView fa_systemdate;
        TextView fa_numbername;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            fa_name = (TextView)itemView.findViewById(R.id.detail_app_fa_name);
            fa_usedate = (TextView)itemView.findViewById(R.id.detail_app_date);
            fa_use_time = (TextView)itemView.findViewById(R.id.detail_app_time);
            fa_usercount = (TextView) itemView.findViewById(R.id.detail_app_usercount);
            fa_status = (TextView) itemView.findViewById(R.id.detail_app_status_);
            fa_applydate = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_applydatetext) ;
            fa_systemdate = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_systemtime);
            fa_numbername = (TextView)itemView.findViewById(R.id.detail_app_fa_numbertext);
            this.myViewHolderClick = myViewHolderClick;

            itemView.setOnClickListener(this);

        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */

        @Override
        public void onClick(View v) {
            if(myViewHolderClick!=null)
                myViewHolderClick.clickOnView(v, getLayoutPosition());
        }

        public interface MyViewHolderClick {
            void clickOnView(View v, int position);
        }

    }
}
