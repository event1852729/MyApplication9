package com.shot.community.go;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017/11/30.
 */

public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.MyViewholder> {

    Context context;
    ArrayList<Home_Auto_model> mData;

    public AutoPollAdapter(Context context,  ArrayList<Home_Auto_model> list) {
        this.context = context;
        this.mData = list;
    }


    @Override
    public AutoPollAdapter.MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_autopollrecycler_item , parent ,false);
        final AutoPollAdapter.MyViewholder mViewHolder = new AutoPollAdapter.MyViewholder(view, new AutoPollAdapter.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(AutoPollAdapter.MyViewholder holder, int position) {

//        holder.textView.setText(homeAutoModels.getText().toString());
//        holder.imageView.setImageResource(homeAutoModels.getImageid());

        Home_Auto_model home_auto_model = mData.get(position%mData.size());
        holder.imageView.setImageResource(home_auto_model.getImageid());
        holder.textView.setText(home_auto_model.getText());

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AutoPollAdapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView textView;
        ImageView imageView;

        public MyViewholder(final View itemView ,AutoPollAdapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.text);
            imageView = (ImageView)itemView.findViewById(R.id.imaage);

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

