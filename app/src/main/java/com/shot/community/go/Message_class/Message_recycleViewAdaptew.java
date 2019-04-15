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
import com.shot.community.go.http_meth.UserData;

import java.util.List;

/**
 * Created by god on 2017/10/20.
 */

public class Message_recycleViewAdaptew extends RecyclerView.Adapter<Message_recycleViewAdaptew.MyHolder> {
    Context mcontext;
    List<Message_item_model> mMessage_item_model;
  static   RecyclerView recyclerView;


    public Message_recycleViewAdaptew(Context context , List<Message_item_model> list) {
        this.mMessage_item_model = list;
        this.mcontext = context;
    }

    public Message_recycleViewAdaptew(Context context , List<Message_item_model> list , RecyclerView recyclerView) {
        this.mMessage_item_model = list;
        this.mcontext = context;
        this.recyclerView = recyclerView;
    }



    @Override
    public Message_recycleViewAdaptew.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycle_item , parent ,false);
        MyHolder myHolder = new MyHolder(view, new MyHolder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Message_item_model message_item_model = mMessage_item_model.get(position);
                Message_http message_http = new Message_http(mcontext , 20);
                message_http.execute("http://140.136.155.79/message/update_look_finish.php" , message_item_model.getMessage_id());
                Message_http message_http3 = new Message_http(mcontext , 21);
                message_http3.execute("http://140.136.155.79/test/message.php" , UserData.id , message_item_model.getMessage_id());
                Intent intent = new Intent(mcontext , Message_onclick_intent_talkabout.class);
                intent.putExtra("number_name" , message_item_model.getMessage_item_numberHerName());
                intent.putExtra("number_Cname" , message_item_model.getMessage_item_numberName());
                intent.putExtra("number_myid" , message_item_model.getMessage_item_numberMYName());
                Message_http message_http2 = new Message_http(mcontext, 0 , recyclerView);
                message_http2.execute("http://140.136.155.79/message/content.php" , UserData.id);
                mcontext.startActivity(intent);

            }
        });
        return myHolder;
    }


    @Override
    public void onBindViewHolder(Message_recycleViewAdaptew.MyHolder holder, int position) {
        Message_item_model message_item_model = new Message_item_model();
        message_item_model =    mMessage_item_model.get(position);

            holder.content.setText(message_item_model.getMessage_item_content());
            if(message_item_model.getRead().equals("1"))
            {
                holder.button.setText("已讀");
                holder.button.setBackgroundResource(R.drawable.message_readfinish_button_style);
            }

        holder.number.setText(message_item_model.getMessage_item_numberName());
        holder.date.setText(message_item_model.getMessage_item_date());

    }

    @Override
    public int getItemCount() {
        return mMessage_item_model.size();
    }


        public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            MyViewHolderClick myViewHolderClick;
            TextView number;
            TextView content;
            TextView date;
            Button button;
            public MyHolder(View itemView , MyViewHolderClick myViewHolderClickCu) {
                super(itemView);
                button = (Button)itemView.findViewById(R.id.message_recycle_buttonnumber);
                number = (TextView)itemView.findViewById(R.id.message_RecycleView_item_number);
                content = (TextView)itemView.findViewById(R.id.message_RecycleView_item_content);
                date = (TextView)itemView.findViewById(R.id.message_RecycleView_date);
                myViewHolderClick = myViewHolderClickCu;
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
