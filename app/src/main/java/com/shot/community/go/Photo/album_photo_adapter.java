package com.shot.community.go.Photo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;

import java.util.List;

/**
 * Created by user on 2017/11/17.
 */

public class album_photo_adapter extends RecyclerView.Adapter<album_photo_adapter.MyViewholder> {


    static List<album_photo_model> albumPhotoModelList;
    static album_photo_model albumPhotoModel;
    static Context context;


    public album_photo_adapter(Context context , List<album_photo_model> list) {
        this.albumPhotoModelList = list;
        this.context = context;
    }


    @Override
    public album_photo_adapter.MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_recycler_item , parent ,false);
        final album_photo_adapter.MyViewholder mViewHolder = new album_photo_adapter.MyViewholder(view, new album_photo_adapter.MyViewholder.MyViewHolderClick() {

            @Override
            public void clickOnView(View v, int position) {
                albumPhotoModel = albumPhotoModelList.get(position);
                Intent intent = new Intent(context,Photo_LookImage.class);
                intent.putExtra("imageurl","http://140.136.155.79/photo/uploads/"+ albumPhotoModel.getImage().toString());
                context.startActivity(intent);
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(album_photo_adapter.MyViewholder holder, int position) {
        albumPhotoModel = albumPhotoModelList.get(position);
        String ss;
        ss = "http://140.136.155.79/photo/uploads/" + albumPhotoModel.getImage().toString();
        Glide.with(context)
                .load(ss)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumPhotoModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        album_photo_adapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        ImageView imageView;

        public MyViewholder(final View itemView , album_photo_adapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);
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

