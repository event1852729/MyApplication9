package com.shot.community.go;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.http_meth.SigninActivity;

/**
 * Created by god on 2017/10/26.
 */

public class Home_selectnumber_recycleAdatpar extends RecyclerView.Adapter<Home_selectnumber_recycleAdatpar.Myholder> {
    static Context context;
   static String[] mSearchHistoryArray;
    static SigninActivity signinActivity;
    public Home_selectnumber_recycleAdatpar(Context context,  String[] mSearchHistoryArray ) {
        this.context = context;
        this.mSearchHistoryArray = mSearchHistoryArray;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_select_number_text , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                if(mSearchHistoryArray[0].equals("暫無用戶 ! \n請使用啟動碼進行開通!"))
                {

                } else {
                    login_index.home_select_edit.setText(mSearchHistoryArray[position]);
                }
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        holder.name.setText(mSearchHistoryArray[position]);

    }


    @Override
    public int getItemCount() {
        return mSearchHistoryArray.length;
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView name;
        public Myholder(final View itemView , final MyViewHolderClick myViewHolderClick) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.home_select_number_text);


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




}
