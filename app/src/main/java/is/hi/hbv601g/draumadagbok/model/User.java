package is.hi.hbv601g.draumadagbok.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import is.hi.hbv601g.draumadagbok.dreamList;
import is.hi.hbv601g.draumadagbok.model.Dream;

/**
 * Created by Halli on 18.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String password;
    private String passwordConfirm;
    private int id;
    private dreamList dreams;

    //Constructors
    public User(){
        dreams = new dreamList();
    }


    public User(int userId,
                 String userName,
                String userPassword,
                 dreamList dreams) {
        this.name = userName;
        this.id = userId;
        this.password = userPassword;
        this.dreams = dreams;

    }
    public User(int userId, String userName, String userPassword) {
        this.name = userName;
        this.id = userId;
        this.password = userPassword;
        this.dreams = new dreamList();
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
        this.dreams.setDreams(dreams);
    }
    public void resetDreams(){
        this.dreams = new dreamList();
    }

    public List<Dream> getDreams(){
        return this.dreams.getDreams();
    }

    public void addDream(Dream dream){
        this.dreams.addDream(dream);

    }
    public String toString(){
        return "" + id + " " + name + " " + password;
    }

}
