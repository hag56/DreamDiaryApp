package is.hi.hbv601g.draumadagbok.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * Created by Halli on 18.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dream {
    private String name; //vantar getset
    private int userId;
    private String content;
    private int id;

    private Date date;
    private String interpretation;

    public Dream(){

    }


    public Dream(String name, int userId, String content, int id) {
        this.name = name;
        this.userId = userId;
        this.content = content;
        this.id = id;
        this.date = new Date();
        // this.interpretation = new Interpretation();
    }



    public Dream(int id,
                 int userId,
                 Date date,
                 String name,
                 String content,
                 String interpretation){
        this.name = name;
        this.userId = userId;
        this.content = content;
        this.id = id;
        this.date = date;
        this.interpretation = interpretation;
    }


    // Notkun: inputDream.interpret()
    // Fyrir: inputDream er hlutur af tagi Dream.
    // Eftir: inputDream hefur fengid Interpretation attribute
    /*
    protected void interpret() {
      this.interpretation = (new StringBuilder(this.content).reverse().toString());
    }
    */

    // Notkun: editDream(old_dream)
    // Fyrir: old_dream er hlutur af tagi Dream
    // Eftir:
    // Liklega redundant. Notum bara setContent
    //protected void editDream(Dream old_dream) {}


    // Only getters and setters bellow this point
    public String getName(){
        return name;
    }

    public void setName(String new_name){
        this.name = new_name;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int new_userId){
        this.userId = new_userId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String dreamText){
        this.content=dreamText;
    }

    public int getId() {
        return id;
    }

    public void setId(int new_dreamId){
        this.id = new_dreamId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date new_date){
        this.date = new_date;
    }

    public String getInterpretation(){
        return interpretation;
    }

    public void setInterpretation(String new_interpretation){
        this.interpretation = new_interpretation;
    }

    public String toString(){
        return ("" + name + " " + userId + " " + content + " " + id + " " + date + " " + interpretation);
    }
}
