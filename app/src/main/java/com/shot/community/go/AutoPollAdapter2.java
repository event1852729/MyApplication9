package com.shot.community.go;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.Announcements_class.Announcements_model.Announcements;
import com.shot.community.go.Announcements_class.announce_content;

import java.util.ArrayList;

/**
 * Created by user on 2017/11/30.
 */

public class AutoPollAdapter2 extends RecyclerView.Adapter<AutoPollAdapter2.MyViewholder> {

    Context context;
    ArrayList<Announcements> mData;
    static ArrayList<String> s;
    int flag =0;

    public AutoPollAdapter2(Context context, ArrayList<Announcements> list , ArrayList<String> s ,  int flag) {
        this.context = context;
        this.mData = list;
        this.s = s;
        this.flag = flag;
    }
    public AutoPollAdapter2(Context context, ArrayList<Announcements> list ,  int flag) {
        this.context = context;
        this.mData = list;
        this.flag = flag;
    }


    @Override
    public AutoPollAdapter2.MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_item_for_new_ann , parent ,false);
        final AutoPollAdapter2.MyViewholder mViewHolder = new AutoPollAdapter2.MyViewholder(view, new AutoPollAdapter2.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                if(flag==1)
                {
                    Announcements home_auto_model1 = mData.get(position % mData.size());
                    String s1 = s.get(position% s.size());
                    Intent intent = new Intent(context, announce_content.class);
                    intent.putExtra("Announcement_content", s1);
                    intent.putExtra("Announcement_title", home_auto_model1.getAnnouncements_titles());
                    intent.putExtra("Announcement_file", home_auto_model1.getAnnouncement_file());
                    context.startActivity(intent);
                }else if(flag==0){

                }
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(AutoPollAdapter2.MyViewholder holder, int position) {

//        holder.textView.setText(homeAutoModels.getText().toString());
//        holder.imageView.setImageResource(homeAutoModels.getImageid());



            Announcements home_auto_model = mData.get(position % mData.size());
//        holder.imageView.setImageResource(home_auto_model.getImageid());
            holder.textView.setText(home_auto_model.getAnnouncements_titles() + "");


    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AutoPollAdapter2.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView textView;



        public MyViewholder(final View itemView ,AutoPollAdapter2.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.home_new_announcement);

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

