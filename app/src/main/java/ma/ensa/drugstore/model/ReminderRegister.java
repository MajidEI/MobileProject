package ma.ensa.drugstore.model;

public class ReminderRegister {

    private Integer hour;
    private Integer minutes;
    private String quantity;
    private Integer pillId;

    public ReminderRegister(){}

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
    public Integer getPillId() {
        return pillId;
    }
    public void setPillId(Integer pillId) {
        this.pillId = pillId;
    }
}
