package com.shot.community.go.Dicuss_class;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/26.
 */

public class Dicuss_content_message_recycleadatper extends RecyclerView.Adapter<Dicuss_content_message_recycleadatper.Myholder> {
    static Context context;
    static ArrayList<Dicuss_content_message_item> list_dicuss_item_model;

    public Dicuss_content_message_recycleadatper(Context context, ArrayList<Dicuss_content_message_item> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Dicuss_content_message_recycleadatper.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dicuss_content_message , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Toast.makeText(context , "檢舉" , Toast.LENGTH_LONG).show();
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(final Dicuss_content_message_recycleadatper.Myholder holder, int position) {
        Dicuss_content_message_item dicuss_content_message_item = list_dicuss_item_model.get(position);
        holder.count.setText("B"+dicuss_content_message_item.getCount());
        holder.content.setText(dicuss_content_message_item.getContent());
        holder.date.setText(dicuss_content_message_item.getDate());
        holder.message_numbername.setText(dicuss_content_message_item.getName());
        holder.button.setText(dicuss_content_message_item.getLm_id());
        if(dicuss_content_message_item.getName().equals(UserData.name)){
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                    real_delete.setTitle("確定刪除?");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Dicuss_http dicuss_http = new Dicuss_http(6);
                            dicuss_http.execute("http://140.136.155.79/lm/delete_finish.php" , holder.button.getText().toString());
                            Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    real_delete.show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView count;
        TextView content;
        TextView date;
        TextView nobodymessage;
        TextView message_numbername;
        Button button;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            count = (TextView)itemView.findViewById(R.id.dicuss_content_message_count);
            content = (TextView)itemView.findViewById(R.id.dicuss_content_message_content);
            date = (TextView)itemView.findViewById(R.id.dicuss_content_message_date);
            button = (Button)itemView.findViewById(R.id.update_diciss_message_manager_button);
            message_numbername = (TextView)itemView.findViewById(R.id.dicuss_content_message_numbername);
            this.myViewHolderClick = myViewHolderClick;
            itemView.setOnClickListener(this);


            if((UserData.manger.toString()).equals("3"))
            {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                        real_delete.setTitle("確定刪除?");
                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Dicuss_http dicuss_http = new Dicuss_http(6);
                                dicuss_http.execute("http://140.136.155.79/lm/delete_finish.php" , button.getText().toString());
                                Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        real_delete.show();

                    }
                });
            }else
            {
                button.setVisibility(View.INVISIBLE);
            }

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
