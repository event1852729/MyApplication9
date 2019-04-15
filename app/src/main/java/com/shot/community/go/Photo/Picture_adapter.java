package com.shot.community.go.Photo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;

import java.util.List;

/**
 * Created by user on 2017/11/16.
 */

public class Picture_adapter extends RecyclerView.Adapter<Picture_adapter.MyViewholder> {


    static List<Picture_model> pictureModelList;
    static Picture_model pictureModel;
    static Context context;


    public Picture_adapter(Context context , List<Picture_model> list) {
        this.pictureModelList = list;
        this.context = context;
    }


    @Override
    public Picture_adapter.MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_recycler_item , parent ,false);
        final Picture_adapter.MyViewholder mViewHolder = new Picture_adapter.MyViewholder(view, new Picture_adapter.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                pictureModel = pictureModelList.get(position);


                Intent intent3 = new Intent(context,album_photo.class);
                intent3.putExtra("id",pictureModel.getID());
                intent3.putExtra("name",pictureModel.getTitle());
                context.startActivity(intent3);
                Toast.makeText(context,pictureModel.getID(),Toast.LENGTH_SHORT).show();
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(Picture_adapter.MyViewholder holder, int position) {
        pictureModel = pictureModelList.get(position);
        holder.Time.setText(pictureModel.getTime().substring(0,7));
        holder.Title.setText(pictureModel.getTitle());
        holder.Abid.setText(pictureModel.getID());

    }

    @Override
    public int getItemCount() {
        return pictureModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Picture_adapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView Time,Title,Abid;
        ImageView imageView;

        public MyViewholder(final View itemView , Picture_adapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            Time = (TextView)itemView.findViewById(R.id.time);
            Title = (TextView)itemView.findViewById(R.id.title);
            Abid = (TextView)itemView.findViewById(R.id.abid);
            imageView = (ImageView)itemView.findViewById(R.id.image);
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
