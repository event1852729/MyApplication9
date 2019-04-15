package com.shot.community.go.Fix;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.List;

/**
 * Created by user on 2017/11/8.
 */

public class Fix_Adapter extends RecyclerView.Adapter<Fix_Adapter.MyViewholder> {

    static List<Fix_model> fixModelList;
    static Fix_model fixModel;
    static Context context;

    public Fix_Adapter(Context context ,List<Fix_model>  list) {
        this.fixModelList = list;
        this.context = context;
    }


    @Override
    public Fix_Adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_fixrecord_recycle_manager_item , parent ,false);
        final Fix_Adapter.MyViewholder mViewHolder = new Fix_Adapter.MyViewholder(view, new Fix_Adapter.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                Fix_item.pos = position;
                fixModel = fixModelList.get(position);
                Intent intent = new Intent(context,Fix_manager_application.class);
                intent.putExtra("imageurl" , "http://140.136.155.79/fix/uploads/" + fixModel.getImage());
                intent.putExtra("con",fixModel.getDetail());
                intent.putExtra("name",fixModel.getPlace());
                intent.putExtra("id",fixModel.getId());
                intent.putExtra("fixid",String.valueOf(fixModel.getFixid()));
                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(Fix_Adapter.MyViewholder holder, int position) {
        fixModel = fixModelList.get(position);
        String name = UserData.name;
        holder.detail.setText(fixModel.getDetail());
        holder.place.setText(fixModel.getPlace());
        holder.time.setText(fixModel.getTime());
        holder.Id.setText(fixModel.getId());
        holder.status.setText(fixModel.getStatustoString());
        String ss;
        ss = "http://140.136.155.79/fix/uploads/" + fixModel.getImage().toString();

        Glide.with(context)
                .load(ss)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return fixModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Fix_Adapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView Id,status,place,detail,time,localtime;
        ImageView imageView;

        public MyViewholder(final View itemView ,Fix_Adapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

           Id = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_numbernametext);
           status = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_staustext);
           place = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_splacetext);;
            detail = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_contenttext);
            time = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_replydatetext);
            imageView = (ImageView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_image);
            localtime = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_systemtime);
            localtime.setVisibility(View.INVISIBLE);

            status.setTextColor(Color.RED);
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
