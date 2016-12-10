/**
 * Created by lszlawrence on 2016/12/10.
 */
public class image {
    private int id;
    private String address;
    private int uid;

    public image(int id, String address, int uid){
        this.id = id;
        this.address = address;
        this.uid = uid;
    }

    public int getId(){
        return id;
    }

    public int getUid(){
        return uid;
    }

    public String getAddress(){
        return address;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setUid(int uid){
        this.uid = uid;
    }
}
