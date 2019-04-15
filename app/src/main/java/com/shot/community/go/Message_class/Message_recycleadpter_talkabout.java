package com.shot.community.go.Message_class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shot.community.go.R;

import java.util.List;

/**
 * Created by god on 2017/10/20.
 */

public class Message_recycleadpter_talkabout extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater mlayoutinflater;
    Context mcontent;
   static int flag = 0;
   RecyclerView recyclerView;
    List<Message_item_model> list;


    public Message_recycleadpter_talkabout(Context context , List<Message_item_model> list) {
        this.list = list;
        mlayoutinflater = LayoutInflater.from(context);
        this.mcontent = context;
    }
    public Message_recycleadpter_talkabout(   RecyclerView recyclerView,Context context , List<Message_item_model> list) {
        this.list = list;
        mlayoutinflater = LayoutInflater.from(context);
        this.mcontent = context;
        this.recyclerView = recyclerView;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case Message_item_model.TYPE_ONE:
                return  new Message_intent_itemMe_holder(mlayoutinflater.inflate(R.layout.message_onclick_item_me , parent , false));
            case Message_item_model.TYPE_TEW:
                return new Message_intent_itemHer_holder(mlayoutinflater.inflate(R.layout.message_intent_item_her , parent ,false));
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Message_intent_item_abstractViewHolder)holder).bindHolder(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
