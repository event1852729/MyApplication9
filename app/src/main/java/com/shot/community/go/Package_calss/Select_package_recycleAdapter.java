package com.shot.community.go.Package_calss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/15.
 */

public class Select_package_recycleAdapter extends RecyclerView.Adapter<Select_package_recycleAdapter.MyViewholder>{
    static Package_http package_http;
    static ArrayList<Select_package_model> select_number_item_modelslist;
  static   Select_number_item_model select_number_item_model;
    static Context context;

    public Select_package_recycleAdapter(Context context , ArrayList<Select_package_model>  list) {
        this.select_number_item_modelslist = list;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_package_dio , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Select_package_model select_number_item_model = select_number_item_modelslist.get(position);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Select_package_model select_number_item_model = select_number_item_modelslist.get(position);

    }

    @Override
    public int getItemCount() {
        return select_number_item_modelslist.size();
    }



        public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
                MyViewHolderClick myViewHolderClick;
                TextView name;

                public MyViewholder(View itemView , MyViewHolderClick myViewHolderClickCu) {
                    super(itemView);
                    name = (TextView) itemView.findViewById(R.id.search_pacakge_number_text);
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
    public void setFiter(ArrayList<Select_number_item_model> arrayList)
    {
        select_number_item_modelslist = new ArrayList<>();
        notifyDataSetChanged();
    }




}
