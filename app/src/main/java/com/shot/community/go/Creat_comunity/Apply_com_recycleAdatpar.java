package com.shot.community.go.Creat_comunity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;
import com.shot.community.go.number_apply_Fragments;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Apply_com_recycleAdatpar extends RecyclerView.Adapter<Apply_com_recycleAdatpar.Myholder> {
    static Context context;
    static ArrayList<Apply_com_numbername_item> list_dicuss_item_model;
    static Creat_comunity_http creat_comunity_http;
    static String e,apply_id,Nm,Ph,Ad;
    static  Apply_com_numbername_item  apply_com_numbername_item;
    public Apply_com_recycleAdatpar(Context context, ArrayList<Apply_com_numbername_item> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_comunity_recycle_item , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        apply_com_numbername_item= list_dicuss_item_model.get(position);
        holder.name.setText("姓名 : " + apply_com_numbername_item.getName());
        holder.phone.setText("電話 : " + apply_com_numbername_item.getPhone());
        holder.email.setText("電子郵件 : " + apply_com_numbername_item.getEmail());
        holder.date.setText(apply_com_numbername_item.getDate());
        holder.button1.setText(apply_com_numbername_item.getApplyId());
        holder.button2.setText(apply_com_numbername_item.getHolderId());
        holder.address.setText("地址 : " + apply_com_numbername_item.getAddress());

        e = apply_com_numbername_item.getEmail();
        Nm = apply_com_numbername_item.getName();
        Ph = apply_com_numbername_item.getPhone();
        Ad = apply_com_numbername_item.getAddress();
        apply_id = apply_com_numbername_item.getHolderId();




    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView name;
        TextView address;
        TextView phone;
        TextView email;
        TextView date;
        Button button , button1 , button2;
        Button non;

        public Myholder(final View itemView , final MyViewHolderClick myViewHolderClick) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.apply_comunity_numbername);
            address = (TextView)itemView.findViewById(R.id.apply_comunity_address);
            phone = (TextView)itemView.findViewById(R.id.apply_comunity_phone);
            email = (TextView) itemView.findViewById(R.id.apply_comunity_content);
            date = (TextView) itemView.findViewById(R.id.apply_comunity_date);
            button = (Button) itemView.findViewById(R.id.apply_comunity_button);
            button1 = (Button)itemView.findViewById(R.id.apply_comunity_button1);
            button2 = (Button)itemView.findViewById(R.id.apply_comunity_button2);
            non = (Button)itemView.findViewById(R.id.nonagree);




            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                    real_delete.setTitle("確定通過?");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Creat_comunity_http creat_comunity_http = new Creat_comunity_http(context , 4);
                            creat_comunity_http.execute("http://140.136.155.79/member/register_pass_finish.php" , button1.getText().toString() ,e ,
                                    Ad , randomString(20)  , button2.getText().toString(),Nm,Ph);

                            Toast.makeText(context,"審核通過",Toast.LENGTH_SHORT).show();

                            Creat_comunity_http creat_comunity_http2 = new Creat_comunity_http(context , 3 , number_apply_Fragments.recyclerView);
                            creat_comunity_http2.execute("http://140.136.155.79/applicant/content.php" , UserData.comunity_id);

                            dialog.dismiss();
                        }
                    });
                    real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    real_delete.show();

                }
            });

            non.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    final View item = layoutInflater.inflate(R.layout.apply_dialog,null);
                    final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);

                    myalerdualog.setView(item);
                    myalerdualog.setTitle("原因說明");



                    myalerdualog.setPositiveButton("送出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText editText = (EditText)item.findViewById(R.id.editText);
                            String s = editText.getText().toString();
                            creat_comunity_http = new Creat_comunity_http(5);
                            creat_comunity_http.execute("http://140.136.155.79/member/register_nopass_finish.php",s,e,apply_id);


                            Toast.makeText(context,"審核不通過",Toast.LENGTH_SHORT).show();

                            Creat_comunity_http creat_comunity_http2 = new Creat_comunity_http(context , 3 , number_apply_Fragments.recyclerView);
                            creat_comunity_http2.execute("http://140.136.155.79/applicant/content.php" , UserData.comunity_id);
                            dialog.dismiss();
                        }
                    });

                    myalerdualog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    myalerdualog.show();
                }
            });
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
    @NonNull
    public static String randomString(int len) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int idx = (int)(Math.random() * str.length());
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }




}
