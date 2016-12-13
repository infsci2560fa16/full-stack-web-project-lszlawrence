import java.util.UUID;

/**
 * Created by lszlawrence on 2016/12/10.
 */
public class image {
    private UUID id;
    private String address;
    private int uid;
    private String category;
    private String introMessage;
    private String recentComment;
    private String devices;
    private String filters;
    private String lens;
    private String date;


    public image(String address){
        this.id = UUID.randomUUID();
        this.address = address;
        this.uid = 1;
        this.category = "view";
        this.date = "09 08 2016";
        this.devices = "canon";
        this.filters = "fashion";
        this.lens = "35mm";
        this.introMessage = "favour";
        this.recentComment = "nice";
    }

    public UUID getId(){
        return id;
    }

    public int getUid(){
        return uid;
    }

    public String getAddress(){
        return address;
    }

    public String getCategory(){
        return category;
    }

    public String getIntroMessage(){
        return introMessage;
    }

    public String getRecentComment(){
        return recentComment;
    }

    public String getDevices(){
        return devices;
    }

    public String getFilters(){
        return filters;
    }

    public String getLens(){
        return lens;
    }

    public String getDate(){
        return date;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public  void  setCategory(String category){
        this.category = category;
    }

    public  void setIntroMessage(String introMessage){
        this.introMessage = introMessage;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public void setRecentComment(String recentComment) {
        this.recentComment = recentComment;
    }

}
