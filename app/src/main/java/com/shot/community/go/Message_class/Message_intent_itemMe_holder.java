package com.shot.community.go.Message_class;

import android.view.View;
import android.widget.TextView;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/21.
 */

public class Message_intent_itemMe_holder  extends Message_intent_item_abstractViewHolder {

    TextView number;
    TextView content;
    TextView date;

    public Message_intent_itemMe_holder(View itemView) {
        super(itemView);
        content = (TextView)itemView.findViewById(R.id.message_onclick_intent_contentME);
        date = (TextView)itemView.findViewById(R.id.message_onclick_intent_dareME);
    }

    @Override
    public void bindHolder(Message_item_model message_item_model) {
        content.setText(message_item_model.getMessage_item_content());
        date.setText(message_item_model.getMessage_item_date());

    }
}
