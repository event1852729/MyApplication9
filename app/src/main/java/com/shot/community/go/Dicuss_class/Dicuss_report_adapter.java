package com.shot.community.go.Dicuss_class;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.R;

import java.util.List;

/**
 * Created by user on 2017/11/19.
 */

public class Dicuss_report_adapter extends RecyclerView.Adapter<Dicuss_report_adapter.MyViewholder> {

    static List<Dicuss_report_model> dicussReportModelList;
    static Dicuss_report_model dicussReportModel;
    static Context context;

    public Dicuss_report_adapter(Context context ,List<Dicuss_report_model>  list) {
        this.dicussReportModelList = list;
        this.context = context;
    }

    @Override
    public Dicuss_report_adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dicuss_report_item , parent ,false);
        final Dicuss_report_adapter.MyViewholder mViewHolder = new Dicuss_report_adapter.MyViewholder(view, new Dicuss_report_adapter.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                dicussReportModel = dicussReportModelList.get(position);
                Intent intent = new Intent(context,Dicuss_report_Audit.class);
                intent.putExtra("name",dicussReportModel.getName());
                intent.putExtra("detail",dicussReportModel.getDetail());
                intent.putExtra("bd_id",String.valueOf(dicussReportModel.getArticle_id()));
                intent.putExtra("rp_id",String.valueOf(dicussReportModel.getRpid()));
                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(Dicuss_report_adapter.MyViewholder holder, int position) {
        dicussReportModel = dicussReportModelList.get(position);
        holder.name.setText(dicussReportModel.getName());
        holder.re_id.setText(String.valueOf(dicussReportModel.getArticle_id()));
        holder.status.setText(dicussReportModel.getStatusToString());
        holder.reson.setText(dicussReportModel.getReson());
        holder.date.setText(dicussReportModel.getDate());
        holder.rp_id.setText(String.valueOf(dicussReportModel.getRpid()));
        holder.detail.setText(dicussReportModel.getDetail());

    }

    @Override
    public int getItemCount() {
        return dicussReportModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Dicuss_report_adapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView name,re_id,status,reson,date,rp_id,detail;

        public MyViewholder(final View itemView ,Dicuss_report_adapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.person);
            re_id = (TextView)itemView.findViewById(R.id.ID);
            status = (TextView)itemView.findViewById(R.id.status);
            reson = (TextView)itemView.findViewById(R.id.reson);
            date = (TextView)itemView.findViewById(R.id.date);
            rp_id = (TextView)itemView.findViewById(R.id.rp_id);
            detail = (TextView)itemView.findViewById(R.id.detail);


            myViewHolderClick = myViewHolderClickCu;
            itemView.setOnClickListener(this);//重要


        }
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
