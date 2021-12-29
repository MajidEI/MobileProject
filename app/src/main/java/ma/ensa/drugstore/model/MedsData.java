package ma.ensa.drugstore.model;

public class MedsData {
    private Integer photoId;
    private String pillName;

    public Integer getPhotoId() {
        return photoId;
    }
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
    public String getPillName() {
        return pillName;
    }
    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public MedsData(){}
}
