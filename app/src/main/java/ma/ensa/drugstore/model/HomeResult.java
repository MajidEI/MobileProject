package ma.ensa.drugstore.model;

import java.util.Date;

public class HomeResult {

    private Integer id;
    private Date start;
    private String duration;
    private String frequency;
    private Integer photoId;
    private String medName;
    private String time;
    public HomeResult(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getPhotoId() {
        return photoId;
    }
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
    public String getMedName() {
        return medName;
    }
    public void setMedName(String medName) {
        this.medName = medName;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
