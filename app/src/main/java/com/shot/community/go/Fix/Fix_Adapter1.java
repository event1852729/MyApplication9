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
 * Created by user on 2017/11/9.
 */

public class Fix_Adapter1 extends RecyclerView.Adapter<Fix_Adapter1.MyViewholder> {

    static List<Fix_model1> fixModelList;
    static Fix_model1 fixModel;
    static Context context;
    static Fix_http fixHttp;

    public Fix_Adapter1(Context context ,List<Fix_model1>  list) {
        this.fixModelList = list;
        this.context = context;
    }


    @Override
    public Fix_Adapter1.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_fixrecord_recycle_manager_item , parent ,false);
        final Fix_Adapter1.MyViewholder mViewHolder = new Fix_Adapter1.MyViewholder(view, new Fix_Adapter1.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                Fix_item.pos1 = position;
                fixModel = fixModelList.get(position);
                Intent intent = new Intent(context,Fix_status1_inside.class);
                intent.putExtra("imageurl" , "http://140.136.155.79/fix/uploads/" + fixModel.getImage());
                intent.putExtra("detail",fixModel.getDetail());
                intent.putExtra("place",fixModel.getPlace());
                intent.putExtra("id",fixModel.getId());
                intent.putExtra("fname",fixModel.getF_name());
                intent.putExtra("fphone",fixModel.getF_phone());
                intent.putExtra("fixid",String.valueOf(fixModel.getFixid()));
                intent.putExtra("status",String.valueOf(fixModel.getStatus()));
                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(Fix_Adapter1.MyViewholder holder, int position) {
        fixModel = fixModelList.get(position);
        String name = UserData.name;

//        String s = String.valueOf(fixModel.getFixid());

        holder.Detail.setText(fixModel.getDetail());
        holder.Place.setText(fixModel.getPlace());
        holder.Time.setText(fixModel.getTime());
        holder.Id.setText(fixModel.getId());
//        holder.fffff.setText(s);
        holder.Status.setText(fixModel.getStatustoString());
        holder.make.setText(fixModel.getMakeTime());

        String ss;
        ss = "http://140.136.155.79/fix/uploads/" + fixModel.getImage().toString();

        Glide.with(context)
                .load(ss)
                .centerCrop()
                .into(holder.imageView);

        Fix_item.idnumber1[position] = fixModel.getFixid();
    }

    @Override
    public int getItemCount() {
        return fixModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Fix_Adapter1.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView Detail,Place,Time,Id,Status,make,fixid;
        ImageView imageView;
        //Button write,fffff;

        public MyViewholder(final View itemView ,Fix_Adapter1.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            Detail = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_contenttext);
            Place = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_splacetext);
            Time = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_replydatetext);
            Id = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_numbernametext);
            Status = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_staustext);
//            write = (Button)itemView.findViewById(R.id.write);
//            fffff = (Button)itemView.findViewById(R.id.button);
            make = (TextView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_systemtime);
            fixid = (TextView)itemView.findViewById(R.id.fixid);

//            write.setVisibility(View.VISIBLE);
//            write.setBackgroundResource(R.drawable.sign_button);
            imageView = (ImageView)itemView.findViewById(R.id.detail_fixrecord_recycle_item_image);
            make.setVisibility(View.VISIBLE);
            Status.setTextColor(Color.RED);


//            write.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(context,Fix_Sign_package.class);
//                    intent.putExtra("FixID",fffff.getText().toString());
//                    context.startActivity(intent);
////                    fixHttp = new Fix_http(5);
////                    fixHttp.execute("http://140.136.155.79/fix/update_sign.php",fix,s);
//
//                }
//            });


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
