package com.shot.community.go.Detail_calss;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Detail_fragment_recycleAdatpar extends RecyclerView.Adapter<Detail_fragment_recycleAdatpar.Myholder> {
    static Context context;
    static ArrayList<Detail_record_item_model> list_dicuss_item_model;

    public Detail_fragment_recycleAdatpar(Context context, ArrayList<Detail_record_item_model> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Detail_fragment_recycleAdatpar.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_recycle_item , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Detail_fragment_recycleAdatpar.Myholder holder, int position) {
        Detail_record_item_model  detail_record_item_model= list_dicuss_item_model.get(position);
        holder.title.setText(detail_record_item_model.getRecord_record_name());
        holder.content.setText(detail_record_item_model.getRecord_content());
        holder.date.setText( detail_record_item_model.getRecord_date());
        if(detail_record_item_model.getRecord_urlname().length()>15)
        {
            holder.button.setText(detail_record_item_model.getRecord_urlname());
        }else
        {
            holder.button.setText(detail_record_item_model.getRecord_urlname());
        }


    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView title;
        TextView date;
        TextView content;
        Button button;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.detail_record_recycle_item_content);
            title = (TextView)itemView.findViewById(R.id.detail_record_recycle_item_detail_title);
            date = (TextView)itemView.findViewById(R.id.detail_record_recycle_item_date);
            button = (Button)itemView.findViewById(R.id.detail_record_recycle_item_button);
            this.myViewHolderClick = myViewHolderClick;
            itemView.setOnClickListener(this);



            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse("http://140.136.155.79/record/uploads/"+button.getText().toString()));

                        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                        req.setTitle(button.getText().toString().replace(" " ,"_"));

                        DownloadManager dm = (DownloadManager)context.getSystemService(context.DOWNLOAD_SERVICE);
                        long downloadId = dm.enqueue(req);
                }
            });

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
