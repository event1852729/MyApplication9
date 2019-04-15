package com.shot.community.go.Dicuss_class;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Dicuss_fragment_recycleadatper extends RecyclerView.Adapter<Dicuss_fragment_recycleadatper.Myholder> {
    static Context context;

    public ArrayList<Dicuss_item_model> getList_dicuss_item_model() {
        return list_dicuss_item_model;
    }

    static ArrayList<Dicuss_item_model> list_dicuss_item_model;

    public Dicuss_fragment_recycleadatper(Context context,  ArrayList<Dicuss_item_model> list_dicuss_item_model ) {
        this.context = context;
        this.list_dicuss_item_model = list_dicuss_item_model;
    }


    @Override
    public Dicuss_fragment_recycleadatper.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dicuss_recycle_item , parent ,false);
        Myholder myholder = new Myholder(view, new Myholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Intent intent = new Intent(context , Dicuss_content.class);
                Dicuss_item_model dicuss_item_model = list_dicuss_item_model.get(position);
                intent.putExtra("title" , dicuss_item_model.getList_title());
                intent.putExtra("dicuss_id" , dicuss_item_model.getList_dicuss_id());
                intent.putExtra("number_name" , dicuss_item_model.getList_number_name());
                intent.putExtra("date" , dicuss_item_model.getList_date());
                intent.putExtra("content" , dicuss_item_model.getList_content());
                context.startActivity(intent);
            }
        });
        return myholder;
    }


    @Override
    public void onBindViewHolder(Dicuss_fragment_recycleadatper.Myholder holder, int position) {
        Dicuss_item_model dicuss_item_model = this.list_dicuss_item_model.get(position);
        holder.title.setText(dicuss_item_model.getList_title());
        holder.content.setText(dicuss_item_model.getList_content());
        holder.date.setText(dicuss_item_model.getList_date());
        holder.name.setText(dicuss_item_model.getList_number_name());
        if(holder.name.getText().toString().equals(UserData.name) || (UserData.manger.toString().equals("3")) ) {
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText(dicuss_item_model.getList_dicuss_id() + "");
        }
        else
            holder.button.setVisibility(View.INVISIBLE);
        holder.buttonholder.setText(dicuss_item_model.getList_dicuss_id());

    }


    @Override
    public int getItemCount() {
        return list_dicuss_item_model.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyViewHolderClick myViewHolderClick;
        TextView title;
        TextView content;
        TextView date;
        TextView name;
        Button button;
        Button buttonholder;

        public Myholder(View itemView ,  MyViewHolderClick myViewHolderClick) {
            super(itemView);
            int count=0;
            button = (Button) itemView.findViewById(R.id.diciss_item_manager_button);
            buttonholder = (Button) itemView.findViewById(R.id.dicuss_item_button_holder);
            name = (TextView)itemView.findViewById(R.id.dicuss_item_show_announcement_numbername) ;
            title = (TextView)itemView.findViewById(R.id.dicuss_item_title);
            content = (TextView)itemView.findViewById(R.id.dicuss_item_content);
            date = (TextView)itemView.findViewById(R.id.dicuss_item_date);
            this.myViewHolderClick = myViewHolderClick;
            itemView.setOnClickListener(this);
            count++;


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            String[] s ={" 刪除"};
                            final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                            myalerdualog.setItems(s, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(which==0)
                                    {
                                        dialog.dismiss();
                                        final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                                        real_delete.setTitle("確定刪除?");
                                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Dicuss_http dicuss_http = new Dicuss_http(2);

                                                dicuss_http.execute("http://140.136.155.79/board/delete_finish.php" , button.getText().toString());
                                                Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        });
                                        real_delete.show();
                                    }

                                }
                            });

                            myalerdualog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            myalerdualog.show();




                    }
                });




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
