package com.shot.community.go.Message_class;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/21.
 */

public class Message_intent_itemHer_holder extends Message_intent_item_abstractViewHolder {
    TextView number;
    TextView content;
    TextView date;
    Button button;
    public Message_intent_itemHer_holder(View itemView) {
        super(itemView);
        button = (Button)itemView.findViewById(R.id.message_onclick_intent_imageMEher) ;
        content = (TextView)itemView.findViewById(R.id.message_onclick_intent_contenther);
        date = (TextView)itemView.findViewById(R.id.message_onclick_intent_dateher);

    }

    @Override
    public void bindHolder(Message_item_model message_item_model) {
        String s = UserData.comunity_name + "的主委";
        String s2 = UserData.comunity_name + "的物業管理員";
        String s3 = UserData.comunity_name + "的警衛";
        if(message_item_model.getMessage_item_numberName().equals(s))
        {
            button.setText("主委");
        }else if(message_item_model.getMessage_item_numberName().equals(s2))
        {
            button.setText("管理員");
        }else if(message_item_model.getMessage_item_numberName().equals(s3)){
            button.setText("警衛");
        }else if(UserData.comunity_id.equals("3"))
        {
            button.setText("住戶");
        }else
             button.setText("鄰居");
        content.setText(message_item_model.getMessage_item_content());
        date.setText(message_item_model.getMessage_item_date());

        Message_http message_http = new Message_http( 20);
        message_http.execute("http://140.136.155.79/message/update_look_finish.php" , message_item_model.getMessage_id());

    }
}
