package com.shot.community.go.Announcements_class;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.Announcements_class.Announcements_model.Announcements;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by god on 2017/10/13.
 */

public class Announcement_RecycleViewAdaptere extends RecyclerView.Adapter<Announcement_RecycleViewAdaptere.MyViewholder> {
   static View view;
    static LayoutInflater myAlertAnnAdapter;
    static  SigninActivity signinActivity;
    static Context context;
    static ArrayList<String> list_content;;
    static Button flagButton;
   static  View view1;
    private LayoutInflater myAnnAdapter;
   static private ArrayList<com.shot.community.go.Announcements_class.Announcements_model.Announcements> Announcements = new ArrayList<>();



    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public MyViewHolderClick mListener;
        public CardView cardView;
        Button button;
        Button button2;

        Button announcementclass;
        TextView titles;
        TextView content;
        TextView dates;
        Button buttoncontent;
        Button buttonfile;
        int announcementId;
        public MyViewholder(final View itemView , MyViewHolderClick listener) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.announcement_button);
            buttonfile = (Button)itemView.findViewById(R.id.announcement_button_holder3);
            button2 = (Button)itemView.findViewById(R.id.announcement_button_holder1);
            buttoncontent = (Button) itemView.findViewById(R.id.announcement_button_holder2);
            announcementclass = (Button)itemView.findViewById(R.id.announce_classbutton);
            flagButton = button;
            titles = (TextView)itemView.findViewById(R.id.AnnounceTitles);

            dates = (TextView)itemView.findViewById(R.id.Announce_date);
            mListener = listener;
            itemView.setOnClickListener(this);
            if((UserData.manger.toString()).equals("1") || UserData.manger.equals("3"))
            {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String[] s ={"  刪除","  修改"};
                        final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                        myalerdualog.setItems(s, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0)
                                {

                                    dialog.dismiss();
                                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
                                    real_delete.setTitle("確定刪除?");
                                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                                            params.add(new BasicNameValuePair("n_id" ,button2.getText().toString() ));
                                            signinActivity = new SigninActivity(1,params);
                                            signinActivity.execute("http://140.136.155.79/news/delete_finish.php");
                                            Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                                    real_delete.show();
                                }
                                if (which==1)
                                {
                                    Intent intent = new Intent(context,updata.class);
                                    intent.putExtra("an_id" , button2.getText().toString());
                                    intent.putExtra("Announcement_content"  , buttoncontent.getText().toString());
                                    intent.putExtra("Announcement_title" , titles.getText().toString());
                                    intent.putExtra("Announcement_file" , buttonfile.getText().toString());
//                                    intent.putExtra("Announcement_title" , Announcement_title);
                                    context.startActivity(intent);
                                }

                            }
                        });

                        myalerdualog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        myalerdualog.show();

                    }
                });
            }
            else
            {
                button.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            mListener.clickOnView(v, getLayoutPosition());
        }

        public interface MyViewHolderClick {
            void clickOnView(View v, int position);
        }



    }

    public Announcement_RecycleViewAdaptere(Context context,ArrayList<Announcements> list , ArrayList<String> list_content)
    {


        this.list_content = list_content;
        this.context = context;
        this.Announcements = list;
    }



    @Override
    public MyViewholder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_text , parent ,false);
        MyViewholder mViewHolder = new MyViewholder(view , new MyViewholder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, final int position) {
                String  Announcement_content = list_content.get(position);
                Announcements announcements = Announcements.get(position);
                SigninActivity signinActivity = new SigninActivity(35);
                signinActivity.execute("http://140.136.155.79/news/countent.php" ,announcements.getAnnouncementsID() , UserData.id);
                Intent intent = new Intent(context,announce_content.class);
                intent.putExtra("Announcement_content" , Announcement_content);
                intent.putExtra("Announcement_title" , announcements.getAnnouncements_titles());
                intent.putExtra("Announcement_file" , announcements.getAnnouncement_file());
                context.startActivity(intent);
            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Announcements announcements_modle = Announcements.get(position);
        String subContent = announcements_modle.getAnnouncements_dates();
        holder.button.setText(announcements_modle.getAnnouncements_titles());
        holder.titles.setText( announcements_modle.getAnnouncements_titles());
        holder.dates.setText(subContent.substring(0,10));
        holder.announcementclass.setText(announcements_modle.getAnnouncementclass());
        holder.button2.setText(announcements_modle.getAnnouncementsID()+"");
        holder.buttoncontent.setText(list_content.get(position));
        holder.buttonfile.setText(announcements_modle.getAnnouncement_file());
        if(announcements_modle.getAnnouncementclass().equals("社區活動"))
        {
            holder.announcementclass.setBackgroundColor(Color.BLUE);
        }

        holder.announcementId = announcements_modle.getAnnouncementsID();
    }

    @Override
    public int getItemCount() {
        return Announcements.size();
    }
}
