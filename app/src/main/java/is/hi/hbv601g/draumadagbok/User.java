package is.hi.hbv601g.draumadagbok;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Halli on 18.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String password;
    private String passwordConfirm;
    private int id;
    private List<Dream> dreamList;

    //Constructors
    public User(){
        dreamList = new ArrayList<Dream>();
    }


    public User(int userId,
                 String userName,
                String userPassword,
                 List<Dream> dreams) {
        this.name = userName;
        this.id = userId;
        this.password = userPassword;
        this.dreamList = dreams;

    }
    public User(int userId, String userName, String userPassword) {
        this.name = userName;
        this.id = userId;
        this.password = userPassword;
        this.dreamList = new ArrayList<Dream>();
    }

    // Notkun:
    // Fyrir:
    // Eftir:
    protected void deleteDream(List<Dream> my_dreams) {

    }

    // possibly redundant
    protected void updateUserInfo(String name, int id, String password){

    }


    // Only getters and setters bellow here:
    public String getName() {
        return name;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int new_id) {
        this.id = new_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String new_password) {
        this.password = new_password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String new_password) {
        this.passwordConfirm = new_password;
    }

    public void setDreams(List<Dream> dreams){
        this.dreamList = dreams;
    }
    public void resetDreams(){
        this.dreamList = new ArrayList<Dream>();
    }

    public List<Dream> getDreams(){
        return this.dreamList;
    }

    public void addDream(Dream dream){
        this.dreamList.add(dream);

    }
    public String toString(){
        return "" + id + " " + name + " " + password;
    }

}
