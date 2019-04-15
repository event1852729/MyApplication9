package com.shot.community.go.Message_class;

/**
 * Created by god on 2017/10/20.
 */

public class Message_item_model{
    static final int TYPE_ONE = 1;
    static final  int TYPE_TEW = 2;

    public String getRead() {
        return read;
    }

    String read;

    public String getMessage_id() {
        return message_id;
    }

    String message_id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type=0;
    String message_item_numberName;
    String message_item_content;

    public String getMessage_item_numberMYName() {
        return message_item_numberMYName;
    }

    public void setMessage_item_numberMYName(String message_item_numberMYName) {
        this.message_item_numberMYName = message_item_numberMYName;
    }

    String message_item_numberMYName;

    public String getMessage_item_numberHerName() {
        return message_item_numberHerName;
    }

    public void setMessage_item_numberHerName(String message_item_numberHerName) {
        this.message_item_numberHerName = message_item_numberHerName;
    }

    String message_item_numberHerName;

    public Message_item_model(){

    }

    public String getMessage_item_numberName() {
        return message_item_numberName;
    }

    public void setMessage_item_numberName(String message_item_numberName) {
        this.message_item_numberName = message_item_numberName;
    }

    public String getMessage_item_content() {
        return message_item_content;
    }

    public void setMessage_item_content(String message_item_content) {
        this.message_item_content = message_item_content;
    }

    public String getMessage_item_date() {
        return message_item_date;
    }

    public void setMessage_item_date(String message_item_date) {
        this.message_item_date = message_item_date;
    }

    String message_item_date;

    public Message_item_model(String message_item_numberName, String message_item_content, String message_item_date) {
        this.message_item_numberName = message_item_numberName;
        this.message_item_content = message_item_content;
        this.message_item_date = message_item_date;
    }
    public Message_item_model(String message_item_numberMYName , String message_item_numberHerName,String message_item_numberName, String message_item_content, String message_item_date , int type ,
                              String message_id , String read){

        this.message_item_numberMYName = message_item_numberMYName;
        this.message_item_numberHerName = message_item_numberHerName;
        this.message_item_numberName = message_item_numberName;
        this.message_item_content = message_item_content;
        this.message_item_date = message_item_date;
        this.type = type;
        this.message_id = message_id;
        this.read = read;
    }
    public Message_item_model(String message_item_numberMYName , String message_item_numberHerName,String message_item_numberName, String message_item_content, String message_item_date , int type ){

        this.message_item_numberMYName = message_item_numberMYName;
        this.message_item_numberHerName = message_item_numberHerName;
        this.message_item_numberName = message_item_numberName;
        this.message_item_content = message_item_content;
        this.message_item_date = message_item_date;
        this.type = type;
    }
    public Message_item_model(String message_item_numberName, String message_item_content, String message_item_date , int type){
        this.message_item_numberName = message_item_numberName;
        this.message_item_content = message_item_content;
        this.message_item_date = message_item_date;
        this.type = type;
    }
}
