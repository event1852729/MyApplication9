package com.shot.community.go.Creat_comunity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.ForgetPassword.Forget_Password;
import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Seracch_recycleAdatpar extends RecyclerView.Adapter<Seracch_recycleAdatpar.Myholder> {
    static Context context;
    static ArrayList<Search_com_item> list_dicuss_item_model;

    public Seracch_recycleAdatpar(Context context, ArrayList<Search_com_item> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }



    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serach_com_item , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                if(Serach_comunity.flag_to_search_com == 1)
                {
                    Search_com_item  Search_com_item= list_dicuss_item_model.get(position);
                    Intent intent = new Intent(context , Forget_Password.class);
                    intent.putExtra("com_name" ,Search_com_item.getName() );
                    context.startActivity(intent);
                    Creat_comunity_http creat_comunity_http = new Creat_comunity_http(7);
                    creat_comunity_http.execute("http://140.136.155.79/member/all_content.php" , Search_com_item.getId());
                    Forget_Password.flagfff = 2;
                    ((Serach_comunity)context).finish();
                }else if(Serach_comunity.flag_to_search_com==2)
                {
                    Search_com_item  Search_com_item= list_dicuss_item_model.get(position);
                    Intent intent = new Intent(context , Add_Comunity.class);
                    intent.putExtra("com_name" ,Search_com_item.getName() );
                    Creat_comunity_http creat_comunity_http = new Creat_comunity_http(7);
                    creat_comunity_http.execute("http://140.136.155.79/member/all_content.php" , Search_com_item.getId());
                    context.startActivity(intent);
                    Add_Comunity.flag = 1;
                    ((Serach_comunity)context).finish();
                }

            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        Search_com_item  Search_com_item= list_dicuss_item_model.get(position);
        holder.address.setText(Search_com_item.getAddress());
        holder.name.setText(Search_com_item.getName());
        holder.numbername.setText(Search_com_item.getNumbername());
        holder.phone.setText(Search_com_item.getPhone());
        holder.IDD.setText(Search_com_item.getId());
        holder.date.setText(Search_com_item.getDate());
    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView name;
        TextView address;
        TextView numbername;
        TextView phone;
        TextView IDD;
        TextView date;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            numbername = (TextView)itemView.findViewById(R.id.serach_com_numnername);
            name = (TextView)itemView.findViewById(R.id.serach_com_name);
            address = (TextView)itemView.findViewById(R.id.serach_com_adress);
            phone = (TextView)itemView.findViewById(R.id.serach_com_phone);
            IDD = (TextView)itemView.findViewById(R.id.idd);
            date = (TextView)itemView.findViewById(R.id.package_recycle_item_Time_text_text);
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
    public void setFiter(ArrayList<Search_com_item> arrayList)
    {
        list_dicuss_item_model = new ArrayList<>();
        list_dicuss_item_model.addAll(arrayList);
        notifyDataSetChanged();
    }
}
