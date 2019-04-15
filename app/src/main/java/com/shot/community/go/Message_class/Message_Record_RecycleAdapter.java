package com.shot.community.go.Message_class;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shot.community.go.R;

import java.util.List;

/**
 * Created by god on 2017/10/21.
 */

public class Message_Record_RecycleAdapter extends RecyclerView.Adapter<Message_Record_RecycleAdapter.MyHolder> {
    List<Message_item_model> mMessage_item_model;
    Context context;

    public Message_Record_RecycleAdapter(List<Message_item_model> mMessage_item_model, Context context) {
        this.mMessage_item_model = mMessage_item_model;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycle_item , parent ,false);
        MyHolder myHolder = new MyHolder(view, new MyHolder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Message_item_model message_item_model = mMessage_item_model.get(position);
                Intent intent = new Intent(context , Message_record_content.class);
                intent.putExtra("number_NAME" , message_item_model.getMessage_item_numberName());
                intent.putExtra("content" , message_item_model.getMessage_item_content());
                intent.putExtra("date" , message_item_model.getMessage_item_date());
                context.startActivity(intent);
            }
        });
        return myHolder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Message_item_model message_item_model = new Message_item_model();
        message_item_model =    mMessage_item_model.get(position);
        holder.content.setText(message_item_model.getMessage_item_content());
        holder.number.setText("收件者:  " + message_item_model.getMessage_item_numberName());
        holder.date.setText("      "+message_item_model.getMessage_item_date());
holder.button.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return mMessage_item_model.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView number;
        TextView content;
        TextView date;
        Button button;
        MyViewHolderClick myViewHolderClick;
        public MyHolder(View itemView ,   MyViewHolderClick myViewHolderClick) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.message_recycle_buttonnumber) ;
            number = (TextView)itemView.findViewById(R.id.message_RecycleView_item_number);
            content = (TextView)itemView.findViewById(R.id.message_RecycleView_item_content);
            date = (TextView)itemView.findViewById(R.id.message_RecycleView_date);
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
