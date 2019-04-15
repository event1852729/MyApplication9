package com.shot.community.go.Detail_calss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Detail_fixrecord_recycle extends RecyclerView.Adapter<Detail_fixrecord_recycle.Myholder> {
    static Context context;
    static ArrayList<Detail_fixrecord_item_model> list_dicuss_item_model;

    public Detail_fixrecord_recycle(Context context, ArrayList<Detail_fixrecord_item_model> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Detail_fixrecord_recycle.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_fixrecord_recycle_item , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Detail_fixrecord_item_model  detail_app_item_model= list_dicuss_item_model.get(position);
                Intent intent = new Intent(context , Fix_recotd_content.class);
                intent.putExtra("splace" , detail_app_item_model.getFixrecord_space());
                intent.putExtra("status" , detail_app_item_model.getFixrecord_status());
                intent.putExtra("name" , detail_app_item_model.getFixrecord_name());
                intent.putExtra("content" , detail_app_item_model.getFixrecord_content());
                intent.putExtra("systemdate1" , detail_app_item_model.getFixrecord_replydate());

                if(detail_app_item_model.getFixrecord_status().equals("0"))
                {

                }else if(detail_app_item_model.getFixrecord_status().equals("1"))
                {
                    intent.putExtra("systemdate" , detail_app_item_model.getFixrecord_make_date());

                }else if(detail_app_item_model.getFixrecord_status().equals("2"))
                {
                    intent.putExtra("systemdate" , detail_app_item_model.getFixrecord_finish_date());
                }
                intent.putStringArrayListExtra("image" , detail_app_item_model.getImage());

                context.startActivity(intent);
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Detail_fixrecord_recycle.Myholder holder, int position) {
        Detail_fixrecord_item_model  detail_app_item_model= list_dicuss_item_model.get(position);
        holder.fa_splace.setText(detail_app_item_model.getFixrecord_space());
        if(detail_app_item_model.getFixrecord_status().equals("0"))
        {
            holder.fix_status.setText("未處理");
            holder.fa_systemdate.setText(detail_app_item_model.getFixrecord_replydate());
        }else if(detail_app_item_model.getFixrecord_status().equals("1"))
        {
            holder.fix_status.setText("處理中");
            holder.fa_systemdate.setText(detail_app_item_model.getFixrecord_make_date());
        }else if(detail_app_item_model.getFixrecord_status().equals("2"))
        {
            holder.fix_status.setText("已完成");
            holder.fa_systemdate.setText(detail_app_item_model.getFixrecord_finish_date());
        }
        holder.fa_content.setText(detail_app_item_model.getFixrecord_content());
        holder.fa_applydate.setText(detail_app_item_model.getFixrecord_replydate());
        Glide.with(context)
                .load("http://140.136.155.79/fix/uploads/"+detail_app_item_model.getFixrecord_imageurl())
                .centerCrop()
                .into(holder.fa_image);

    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView fix_status;
        TextView fa_content;
        TextView fa_splace;
        TextView fa_applydate;
        TextView fa_systemdate;
        ImageView fa_image;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            fix_status = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_staustext);
            fa_content = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_contenttext);
            fa_splace = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_splacetext);
            fa_applydate = (TextView) itemView.findViewById(R.id.detail_fixrecord_recycle_item_replydatetext);
            fa_systemdate = (TextView) itemView.findViewById(R.id.detail_fixrecord_recycle_item_systemtime);
            fa_image = (ImageView) itemView.findViewById(R.id.detail_fixrecord_recycle_item_image);
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
