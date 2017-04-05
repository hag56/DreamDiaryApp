package is.hi.hbv601g.draumadagbok.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;

import is.hi.hbv601g.draumadagbok.model.Dream;

/**
 * Created by Halli on 19.3.2017.
 */
//bean class, intended to help with POST operations
@JsonIgnoreProperties(ignoreUnknown = true)
public class dreamList implements Serializable{

    private List<Dream> dreams;

    public dreamList(){
        dreams = new ArrayList<Dream>();
    }

    public List<Dream> getDreams(){
        return this.dreams;
    }

    public void setDreams(List<Dream> dreams){
        this.dreams = dreams;
    }

    public void addDream(Dream dream){
        this.dreams.add(0,dream);
    }

}
