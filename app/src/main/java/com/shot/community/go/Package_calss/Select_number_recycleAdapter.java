package com.shot.community.go.Package_calss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.Message_class.Message_send;
import com.shot.community.go.Message_class.ViewPager_Recycle1;
import com.shot.community.go.Number_manager;
import com.shot.community.go.Package_Fragements;
import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/15.
 */

public class Select_number_recycleAdapter extends RecyclerView.Adapter<Select_number_recycleAdapter.MyViewholder>{
    static Package_http package_http;
    static ArrayList<Select_number_item_model> select_number_item_modelslist;
  static   Select_number_item_model select_number_item_model;
    static Context context;

    public Select_number_recycleAdapter(Context context , ArrayList<Select_number_item_model>  list) {
        this.select_number_item_modelslist = list;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_number_item , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Select_number_item_model select_number_item_model = select_number_item_modelslist.get(position);
                if(Select_number.whoUsethisClassFlag==0)
                {
                    Intent intent = new Intent(context , Add_package.class);
                    intent.putExtra("name" , select_number_item_model.getName());
                    intent.putExtra("id" , select_number_item_model.getId());
                    context.startActivity(intent);
                    Add_package.flag5=1;
                    ((Select_number)context).finish();
                }else if(Select_number.whoUsethisClassFlag==1)
                {
                    Intent intent = new Intent(context , Message_send.class);
                    intent.putExtra("name" , select_number_item_model.getName());
                    intent.putExtra("id" , select_number_item_model.getId());
                    context.startActivity(intent);
                    Message_send.flag=1;
                    ((Select_number)context).finish();
                }else if(Select_number.whoUsethisClassFlag==2)
                {
                    Rm_select_package_number.packagenumber_id = select_number_item_model.getId();
                    Rm_select_package_number.packagenumber_name = select_number_item_model.getName();
                    Package_Fragements.editText.setText(select_number_item_model.getName());
                }else if(Select_number.whoUsethisClassFlag==3)
                {
                    Rm_select_package_number.packagenumber_id = select_number_item_model.getId();
                    Rm_select_package_number.packagenumber_name = select_number_item_model.getName();
                    ViewPager_Recycle1.editText.setText(select_number_item_model.getName());
                }else if(Select_number.whoUsethisClassFlag==4)
                {
                    Number_manager.editText.setText(select_number_item_model.getName());
                    Number_manager.numid=select_number_item_model.getId();
                }
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Select_number_item_model select_number_item_model = select_number_item_modelslist.get(position);
        holder.name.setText(select_number_item_model.getName());
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
                    name = (TextView) itemView.findViewById(R.id.select_number_text);
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
        select_number_item_modelslist.addAll(arrayList);
        notifyDataSetChanged();
    }




}
