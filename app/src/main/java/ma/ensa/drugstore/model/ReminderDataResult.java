package ma.ensa.drugstore.model;

public class ReminderDataResult {
    private Integer hour;
    private Integer minutes;
    private String quantity;
    private Integer id;
    private Integer pillId;

    public ReminderDataResult(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPillId() {
        return pillId;
    }
    public void setPillId(Integer pillId) {
        this.pillId = pillId;
    }
    public Integer getHour() {
        return hour;
    }
    public void setHour(Integer hour) {
        this.hour = hour;
    }
    public Integer getMinutes() {
        return minutes;
    }
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
