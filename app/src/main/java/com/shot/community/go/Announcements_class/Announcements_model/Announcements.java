package com.shot.community.go.Announcements_class.Announcements_model;

/**
 * Created by user on 2017/4/23.
 */

public class Announcements {
    public Announcements()
    {}

    public String getAnnouncement_file() {
        return announcement_file;
    }

    public void setAnnouncement_file(String announcement_file) {
        this.announcement_file = announcement_file;
    }

    String announcement_file;
    public String getAnnouncementclass() {
        return announcementclass;
    }

    public void setAnnouncementclass(String announcementclass) {
        this.announcementclass = announcementclass;
    }

    String announcementclass;

    public Announcements(int imageId , String announcements_titles , String announcements_dates , int id){
        setImageId(imageId);
        setAnnouncements_dates(announcements_dates);
        setAnnouncements_titles(announcements_titles);
        setAnnouncementsID(id);

    }
    public Announcements(int imageId , String announcements_titles , String announcements_dates){
        setImageId(imageId);
        setAnnouncements_dates(announcements_dates);
        setAnnouncements_titles(announcements_titles);

    }

    public Announcements(int imageId , String announcements_titles , String announcements_dates , String announcementclass){
        setImageId(imageId);
        setAnnouncements_dates(announcements_dates);
        setAnnouncements_titles(announcements_titles);
        this.announcementclass = announcementclass;

    }
    public Announcements(int imageId , String announcements_titles , String announcements_dates , String announcementclass , String announcement_file , String id){
        setImageId(imageId);
        setAnnouncements_dates(announcements_dates);
        setAnnouncements_titles(announcements_titles);
        this.announcementclass = announcementclass;
        this.announcement_file = announcement_file;
        this.setAnnouncementsID(Integer.parseInt(id));

    }
    public String getAnnouncements_titles() {
        return Announcements_titles;
    }

    public void setAnnouncements_titles(String announcements_titles) {
        Announcements_titles = announcements_titles;
    }

    public String getAnnouncements_dates() {
        return Announcements_dates;
    }

    public void setAnnouncements_dates(String announcements_dates) {
        Announcements_dates = announcements_dates;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private int imageId;



    public int getAnnouncementsID() {
        return AnnouncementsID;
    }

    public void setAnnouncementsID(int announcementsID) {
        AnnouncementsID = announcementsID;
    }

    private int AnnouncementsID;
    private String Announcements_titles;
    private String Announcements_dates;

}
