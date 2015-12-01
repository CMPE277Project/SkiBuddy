package edu.sjsu.cmpe277.termproject.models;

/**
 * Created by emy on 11/22/15.
 */
public class Event {

    private String title = "Test Title";
    private String description = "Test Description";
    private String startTime = "";
    private String endTime = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

/*
    public void setStartTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.startTime = simpleDateFormat.format(date);
    }
    */

    //Constructor
    public Event(){
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
//    Event(String title, String description, String startTime, String endTime){
//        this.title = title;
//        this.description = description;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }

    public String getEndTime() {
        return endTime;
    }

    /*
    public void setEndTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.endTime = simpleDateFormat.format(date);
    }
    */


    public void setStartTime(String date_string) {
            this.startTime = date_string;
    }

    public void setEndTime(String date_string) {
        this.endTime = date_string;
    }

}
