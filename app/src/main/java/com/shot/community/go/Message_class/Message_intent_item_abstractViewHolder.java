package com.shot.community.go.Message_class;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by god on 2017/10/21.
 */

public abstract class Message_intent_item_abstractViewHolder extends RecyclerView.ViewHolder {

    public Message_intent_item_abstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(Message_item_model message_item_model);

}
