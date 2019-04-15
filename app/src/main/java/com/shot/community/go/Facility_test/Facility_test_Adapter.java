package com.shot.community.go.Facility_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.facility_manager.Facility_add_Http;
import com.shot.community.go.facility_manager.Facility_updata;
import com.shot.community.go.http_meth.UserData;

import java.util.List;

/**
 * Created by user on 2017/11/2.
 */

public class Facility_test_Adapter extends RecyclerView.Adapter<Facility_test_Adapter.MyViewholder> {

    static List<Facility_test_model> facility_model_modelList;
    static   Facility_test_model facility_test_model;
    static Context context;
    static Facility_test_http facility_test_http;

    public Facility_test_Adapter(Context context ,List<Facility_test_model>  list) {
        this.facility_model_modelList = list;
        this.context = context;
    }

    @Override
    public Facility_test_Adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_test_item , parent ,false);
        final MyViewholder mViewHolder = new MyViewholder(view, new MyViewholder.MyViewHolderClick() {

            @Override
            public void clickOnView(View v, int position) {
                Facility_position.positionn = position;
                int mgid = Integer.parseInt(UserData.manger);
                if (mgid==3){

                    facility_test_model = facility_model_modelList.get(position);
                    Intent intent = new Intent(context, Facility_updata.class);
                    intent.putExtra("name",facility_test_model.getName());
                    intent.putExtra("limit",facility_test_model.getLimit());
                    intent.putExtra("day",String.valueOf(facility_test_model.getDay()));
                    intent.putExtra("St",String.valueOf(facility_test_model.getSt()));
                    intent.putExtra("Et",String.valueOf(facility_test_model.getEt()));
                    intent.putExtra("status",String.valueOf(facility_test_model.getStatusid()));
                    intent.putExtra("fid",String.valueOf(facility_test_model.getNameid()));
                    Facility_position.fip = position;
                    context.startActivity(intent);
                }
                else {
                    facility_test_model = facility_model_modelList.get(position);
                    if (facility_test_model.getStatus()=="不開放")
                    {
                        Toast.makeText(context,"此設施目前\"不開放\"",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(context,Facility_reserve.class);
                        intent.putExtra("nameid",String.valueOf(facility_test_model.getNameid()));
                        intent.putExtra("name",facility_test_model.getName());
                        context.startActivity(intent);
                    }
                }

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(Facility_test_Adapter.MyViewholder holder, int position) {

        facility_test_model = facility_model_modelList.get(position);
        holder.name.setText(facility_test_model.getName());
        holder.time.setText(facility_test_model.getTime());
        holder.date.setText(facility_test_model.getDate());
        holder.status.setText(facility_test_model.getStatus());
        holder.limit.setText(facility_test_model.getLimit());
        holder.delbtn.setText(String.valueOf(facility_test_model.getNameid()));
        holder.idnum.setText(String.valueOf("ID : "+facility_test_model.getNameid()));
        int g[] = new int[25];
        g[position] = facility_test_model.getNameid();
        Facility_position.idnumber[position] =g[position];

    }

    @Override
    public int getItemCount() {
        return facility_model_modelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Facility_test_Adapter.MyViewholder.MyViewHolderClick myViewHolderClick;
        TextView name;
        TextView time;
        TextView date;
        TextView status;
        TextView limit;
        TextView idnum;
        ImageView imageView;
        Button button;
        Button delbtn;
        TextView St,Et;
        public MyViewholder(final View itemView ,MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);
            name  = (TextView)itemView.findViewById(R.id.name);
            time = (TextView)itemView.findViewById(R.id.time);
            date = (TextView)itemView.findViewById(R.id.day);
            status = (TextView) itemView.findViewById(R.id.status);
            limit = (TextView)itemView.findViewById(R.id.limit);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            button = (Button)itemView.findViewById(R.id.announcement_button);
            delbtn = (Button)itemView.findViewById(R.id.delete_button);
            idnum = (TextView)itemView.findViewById(R.id.idnum);
            St = (TextView)itemView.findViewById(R.id.st);
            Et = (TextView)itemView.findViewById(R.id.et);

            idnum.setVisibility(View.INVISIBLE);

            myViewHolderClick = myViewHolderClickCu;
            itemView.setOnClickListener(this);//重要

            if (UserData.manger.toString().equals("3")){
                delbtn.setVisibility(View.VISIBLE);
                delbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = delbtn.getText().toString();
//                        List<NameValuePair> params = new ArrayList<NameValuePair>();
//                        params.add(new BasicNameValuePair("it_id" ,delbtn.getText().toString()));
                        Facility_position.max = Integer.parseInt(a);
                        Facility_add_Http facilityAddHttp = new Facility_add_Http(3);
                        facilityAddHttp.execute("http://140.136.155.79/item/delete_finish.php",a);

                        Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                    }
                });
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
