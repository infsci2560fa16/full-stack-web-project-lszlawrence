/**
 * Created by lszlawrence on 2016/12/10.
 */
public class Users {
    private int id;
    private String email;
    private String name;
    private String password;

    public Users(String email, String password, String name){
        this.email = email;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
