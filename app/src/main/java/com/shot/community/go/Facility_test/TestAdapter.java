package com.shot.community.go.Facility_test;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.Creat_comunity.Creat_comunity_http;
import com.shot.community.go.Message_class.Message_http;
import com.shot.community.go.Message_class.Message_send;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;
import com.shot.community.go.number_apply_Fragments;

import java.util.List;

/**
 * Created by user on 2017/10/29.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewholder> {

    static Test_http test_http;
    static List<Test_model> test_model_modelList;
    static   Test_model test_model;
    static Context context;

    public TestAdapter(Context context ,List<Test_model>  list) {
        this.test_model_modelList = list;
        this.context = context;
    }

    @Override
    public TestAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {

            @Override
            public void clickOnView(View v, int position) {
//                test_model = test_model_modelList.get(position);
//                String s1 = Integer.toString(test_model.getNumberID());
//
//
//                Intent intent = new Intent(context,Package_content.class);
//                intent.putExtra("facility_name" ,test_model.getFacility() );
//                intent.putExtra("facility_Id" ,s1);
//                intent.putExtra("facility_date" ,test_model.getDate());
//                intent.putExtra("facility_time" ,test_model.getTime());
//                intent.putExtra("facility_status" ,test_model.getStatusToString());
//                intent.putExtra("member_name" ,test_model.getMemberid());
//                intent.putExtra("facility_finishtime" ,test_model.getFinishtime());
//                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TestAdapter.MyViewholder holder, int position) {
        test_model = test_model_modelList.get(position);
        String s = Integer.toString(test_model.getNumberID());
        holder.member_name.setText(test_model.getMemberid());
        holder.facility_Id.setText("FC" + s);
        holder.facility_date.setText(test_model.getDate());
        holder.facility_time.setText(test_model.getTime());
        holder.facility_finishtime.setText(test_model.getFinishtime());
        holder.facility_status.setText(test_model.getStatusToString());
        holder.facility_name.setText(test_model.getFacility());
        holder.people.setText("申請人數 : "+test_model.getPeople());
        holder.button.setText(s);
        holder.button2.setText(test_model.getId());



    }

    @Override
    public int getItemCount() {
        return test_model_modelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MyViewHolderClick myViewHolderClick;
        TextView facility_status;
        TextView facility_name;
        TextView member_name;
        TextView facility_Id;
        TextView facility_date;
        TextView facility_time;
        TextView facility_finishtime;
        TextView people;
        Button pass;
        Button nopass;
        Button button,button2;
        ImageView imageView;
        public MyViewholder(final View itemView , MyViewHolderClick myViewHolderClickCu) {
            super(itemView);
            facility_status  = (TextView)itemView.findViewById(R.id.status);
            facility_name = (TextView)itemView.findViewById(R.id.facility);
            member_name = (TextView)itemView.findViewById(R.id.ID);
            facility_Id = (TextView) itemView.findViewById(R.id.idnumber);
            facility_date = (TextView)itemView.findViewById(R.id.date);
            facility_time = (TextView)itemView.findViewById(R.id.time);
            people = (TextView)itemView.findViewById(R.id.people);
            facility_finishtime = (TextView)itemView.findViewById(R.id.textView5);
            pass = (Button)itemView.findViewById(R.id.pass);
            nopass = (Button)itemView.findViewById(R.id.nopass);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            button = (Button)itemView.findViewById(R.id.text);
            button2 = (Button)itemView.findViewById(R.id.memberid);



            pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle3);
                    real_delete.setTitle("確定審核通過");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String fg = button.getText().toString();
                            test_http = new Test_http(1);
                            test_http.execute("http://140.136.155.79/reserve/update_status_finish.php",fg);


                            Toast.makeText(context,"審核通過",Toast.LENGTH_SHORT).show();

                            Test_http test_http2 = new Test_http( Test.recyclerView , context,0);
                            test_http2.execute("http://140.136.155.79/reserve/manager_content.php", UserData.comunity_id);

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

            nopass.setOnClickListener(new View.OnClickListener() {
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

                            String fg = button.getText().toString();
                            String memid = button2.getText().toString();

                            Log.d("sdfsafasfvgfsg",memid);

                            Message_http message_http = new Message_http(3 );
                            message_http.execute("http://140.136.155.79/message/register_finish.php" , UserData.id , memid, "您的預約不通過! 不通過原因 : "+s);

                            test_http = new Test_http(1);
                            test_http.execute("http://140.136.155.79/reserve/update_status_finish.php",fg);

                            Toast.makeText(context,"審核不通過",Toast.LENGTH_SHORT).show();


                            //重整recyclerview
                            Test_http test_http3 = new Test_http( Test.recyclerView , context,0);
                            test_http3.execute("http://140.136.155.79/reserve/manager_content.php", UserData.comunity_id);
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
//                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
//                    real_delete.setTitle("確定審核不通過");
//                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String fg = button.getText().toString();
//                            test_http = new Test_http(1);
//                            test_http.execute("http://140.136.155.79/reserve/update_status_finish.php",fg);
//                            Toast.makeText(context,"審核不通過",Toast.LENGTH_SHORT).show();
//
//                            Test_http test_http3 = new Test_http( Test.recyclerView , context,0);
//                            test_http3.execute("http://140.136.155.79/reserve/manager_content.php", UserData.comunity_id);
//
//                            dialog.dismiss();
//                        }
//                    });
//                    real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    real_delete.show();
                }
            });



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
