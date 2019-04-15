package com.shot.community.go;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HOME_Adapter extends RecyclerView.Adapter<HOME_Adapter.MyViewholder> {

    private HOME_Model[] homeModels;
    static Context context;

    public HOME_Adapter(Context context,HOME_Model[] homeModels) {
        this.homeModels = homeModels;
        this.context = context;
    }


    @Override
    public HOME_Adapter.MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_new_recycler_item , parent ,false);
        final HOME_Adapter.MyViewholder mViewHolder = new HOME_Adapter.MyViewholder(view, new HOME_Adapter.MyViewholder.MyViewHolderClick() {


            @Override
            public void clickOnView(View v, int position) {
                Intent intent = new Intent(context,HOME_Inside.class);
                intent.putExtra("name",homeModels[position].getName());
                intent.putExtra("img",String.valueOf(homeModels[position].getImageid()));
                intent.putExtra("Posi",String.valueOf(position));
                context.startActivity(intent);

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(HOME_Adapter.MyViewholder holder, int position) {

        holder.Title.setText(homeModels[position].getName());
        holder.imageView.setImageResource(homeModels[position].getImageid());

    }

    @Override
    public int getItemCount() {
        return homeModels.length;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        HOME_Adapter.MyViewholder.MyViewHolderClick myViewHolderClick;

        TextView Title;
        ImageView imageView;

        public MyViewholder(final View itemView ,HOME_Adapter.MyViewholder.MyViewHolderClick myViewHolderClickCu) {
            super(itemView);

            Title = (TextView)itemView.findViewById(R.id.title);
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

