package com.shot.community.go.Package_calss;

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
 * Created by god on 2017/10/15.
 */

public class Package_content_image_recycleAdapter extends RecyclerView.Adapter<Package_content_image_recycleAdapter.MyViewholder>{
    static ArrayList<String> package_image;
    static Context context;
   static String ss="";

    public Package_content_image_recycleAdapter(Context context , ArrayList<String>  list) {
        this.package_image = list;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_content_recycle_image_item , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Intent intent5 = new Intent(context, Look_image.class);

                intent5.putExtra("imageurl" , "http://140.136.155.79/package/uploads/" + package_image.get(position));
                context.startActivity(intent5);
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
            ss = "http://140.136.155.79/package/uploads/" + package_image.get(position);

        Glide.with(context)
                .load(ss)
                .into(holder.package_image3);

        if(position==0 && package_image.size()==1)
        {
            holder.textView.setText("包裹照片");
        }
        else if(position==0 &&package_image.size()==2)
        {
            holder.textView.setText("簽收照片");
        }else if(position==1 &&package_image.size()==2)
        {
            holder.textView.setText("包裹照片");
        }

    }

    @Override
    public int getItemCount() {
        return package_image.size();
    }



        public static class MyViewholder extends RecyclerView.ViewHolder  implements View.OnClickListener{
            ImageView package_image3;
            TextView textView;

           MyViewHolderClick myViewHolderClick;
            public MyViewholder(View itemView ,     MyViewHolderClick myViewHolderClick) {
                super(itemView);
                package_image3 = (ImageView) itemView.findViewById(R.id.package_content_recycle_image);
                package_image3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                textView = (TextView)itemView.findViewById(R.id.package_content_recycle_Text);
                this.myViewHolderClick = myViewHolderClick;
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
