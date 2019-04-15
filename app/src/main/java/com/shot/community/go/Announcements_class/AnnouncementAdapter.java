package com.shot.community.go.Announcements_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shot.community.go.Announcements_class.Announcements_model.Announcements;
import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by user on 2017/4/23.
 */

public class AnnouncementAdapter extends BaseAdapter {
    private LayoutInflater myAnnAdapter;
    private ArrayList<com.shot.community.go.Announcements_class.Announcements_model.Announcements> Announcements = new ArrayList<>();

    static class ViewHolder{
        TextView titles;
        TextView dates;
    }

    public AnnouncementAdapter(Context context , ArrayList<Announcements> list)
    {
        context = context;
        myAnnAdapter = LayoutInflater.from(context);
        this.Announcements = list;
    }

    @Override
    public int getCount() {
        return this.Announcements.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView = myAnnAdapter.inflate(R.layout.announcement_text,null);
            holder = new ViewHolder();
            holder.titles = (TextView) convertView.findViewById(R.id.AnnounceTitles);
            holder.dates = (TextView) convertView.findViewById(R.id.Announce_date);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Announcements announcements = Announcements.get(position);
        holder.titles.setText(announcements.getAnnouncements_titles());
        holder.dates.setText(announcements.getAnnouncements_dates());
        return convertView;

    }
}
