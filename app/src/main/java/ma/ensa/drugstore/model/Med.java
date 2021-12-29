package ma.ensa.drugstore.model;

import java.util.Date;

public class Med {

    private String pillName;
    private Integer photoId;
    private Date start;
    private String duration;
    private String frequency;
    private String reminder;
    private String status;

    public Med(){}

    public String getPillName() {
        return pillName;
    }
    public void setPillName(String pillName) {
        this.pillName = pillName;
    }
    public Integer getPhotoId() {
        return photoId;
    }
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getStatus() {
        return status;
    }
    public String getReminder() {
        return reminder;
    }
    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
