package com.shot.community.go.Package_calss;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.List;

/**
 * Created by god on 2017/10/15.
 */

public class Package_recycleAdapter extends RecyclerView.Adapter<Package_recycleAdapter.MyViewholder>{
    static Package_http package_http;
    static  List<Package_item_model>  package_item_modelList;
    static   Package_item_model package_item_model;
    Package_http package_http2;
    static Context context;

    public Package_recycleAdapter(Context context ,List<Package_item_model>  list) {
        this.package_item_modelList = list;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_recycle_item , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                package_item_model = package_item_modelList.get(position);
                String s1 = Integer.toString(package_item_model.getPackage_number_id());
                package_http2 = new Package_http(15);
                package_http2.execute("http://140.136.155.79/package/content.php" , UserData.id , package_item_model.getPackage_number_id());


                Intent intent = new Intent(context,Package_content.class);
                intent.putStringArrayListExtra("package_image" , package_item_model.getImage());
                intent.putExtra("httpurl" ,"http://140.136.155.79/package/uploads/" );
                intent.putExtra("package_Name" ,package_item_model.getNumberName() );
                intent.putExtra("package_Id" ,s1);
                intent.putExtra("package_date" ,package_item_model.getPackage_time() );
                intent.putExtra("package_status" ,package_item_model.getPackage_statusToString() );
                intent.putExtra("package_finish_date" ,package_item_model.getPackage_finish_date() );
                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        package_item_model = package_item_modelList.get(position);
        String s = Integer.toString(package_item_model.getPackage_number_id());
        String imagehttpStr;
        imagehttpStr = "http://140.136.155.79/package/uploads/" + package_item_model.getImag_url().toString();
        Glide.with(context)
                .load(imagehttpStr)
                .centerCrop()
                .into(holder.package_image);
        holder.package_Catch_toDelete.setText(s);
        holder.package_update_holder.setText(package_item_model.getImage().toString());
        holder.package_Name.setText(package_item_model.getNumberName());
        holder.package_Id.setText("PG00" + s);
        if(package_item_model.getPackage_statusToString().equals("已領取"))
        {
            holder.package_date.setText(package_item_model.getPackage_finish_date());
        }else if(package_item_model.getPackage_statusToString().equals("已退貨")){
            holder.package_date.setText(package_item_model.getPackage_finish_date());
        }else {
            holder.package_date.setText(package_item_model.getPackage_time());
        }
        holder.package_status.setText(package_item_model.getPackage_statusToString());
    }

    @Override
    public int getItemCount() {
        return package_item_modelList.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MyViewHolderClick myViewHolderClick;
        Button package_manager_button;
        ImageView package_image;
        TextView package_Catch_toDelete;
        TextView package_Name;
        TextView package_Id;
        TextView package_status;
        TextView package_date;
        Button package_update_holder;
        public MyViewholder(View itemView , MyViewHolderClick myViewHolderClickCu) {
            super(itemView);
            package_image = (ImageView)itemView.findViewById(R.id.package_imageView);
            package_update_holder = (Button)itemView.findViewById(R.id.package_recycle_item_Button_toupdate);
            package_Catch_toDelete = (TextView) itemView.findViewById(R.id.holder_Catch_TO_delete);
            package_manager_button = (Button)itemView.findViewById(R.id.package_recycle_item_Button_addupdata);
            package_Name =(TextView) itemView.findViewById(R.id.package_recycle_item_numberName_text_text);
            package_Id =(TextView) itemView.findViewById(R.id.package_recycle_item_numberID_text_text);
            package_status =(TextView) itemView.findViewById(R.id.package_recycle_item_staus_text);
            package_date =(TextView) itemView.findViewById(R.id.package_recycle_item_Time_text_text);
            myViewHolderClick = myViewHolderClickCu;
            itemView.setOnClickListener(this);//重要

            if((UserData.manger.toString()).equals("2") || UserData.manger.equals("3")) {
                package_manager_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        if(package_status.getText().toString().equals("已退貨") || package_status.getText().toString().equals("已領取"))
                        {
                            String[] s = {"  刪除"};
                            final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                            myalerdualog.setItems(s, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        final AlertDialog.Builder real_delete = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                                        real_delete.setTitle("確定刪除");
                                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                package_http = new Package_http(2);
                                                package_http.execute("http://140.136.155.79/package/delete_finish.php", package_Catch_toDelete.getText());
                                                dialog.dismiss();
                                            }
                                        });
                                        real_delete.show();
                                        dialog.dismiss();
                                    }
                                    if (which == 1) {
                                        Toast.makeText(context, "修改", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, Package_UPdata_package.class);
                                        intent.putExtra("package_Catch_toDelete", package_Catch_toDelete.getText());
                                        intent.putExtra("package_Name", package_Name.getText());
                                        intent.putExtra("package_status", package_status.getText());
                                        intent.putExtra("package_Id", package_Id.getText());
                                        intent.putExtra("package_date", package_date.getText());
                                        intent.putExtra("package_image" , "http://140.136.155.79/package/uploads/" + package_item_model.getImag_url().toString());
                                        context.startActivity(intent);
                                        dialog.dismiss();
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
                        }else {
                            String[] s = {"  刪除", "  修改",};
                            final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                            myalerdualog.setItems(s, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        final AlertDialog.Builder real_delete = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                                        real_delete.setTitle("確定刪除");
                                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                package_http = new Package_http(2);
                                                package_http.execute("http://140.136.155.79/package/delete_finish.php", package_Catch_toDelete.getText());
                                                dialog.dismiss();
                                            }
                                        });
                                        real_delete.show();
                                        dialog.dismiss();
                                    }
                                    if (which == 1) {
                                        Toast.makeText(context, "修改", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, Package_UPdata_package.class);
                                        intent.putExtra("package_Catch_toDelete", package_Catch_toDelete.getText());
                                        intent.putExtra("package_Name", package_Name.getText());
                                        intent.putExtra("package_status", package_status.getText());
                                        intent.putExtra("package_Id", package_Id.getText());
                                        intent.putExtra("package_date", package_date.getText());
                                        intent.putExtra("package_image" , "http://140.136.155.79/package/uploads/" + package_item_model.getImag_url().toString());
                                        context.startActivity(intent);
                                        dialog.dismiss();
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
                    }
                });
            }else{
                package_manager_button.setVisibility(View.INVISIBLE);
            }





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
