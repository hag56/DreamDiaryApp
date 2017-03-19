package is.hi.hbv601g.draumadagbok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Halli on 19.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class dreamList {

    private ArrayList<Dream> dreams;

    public dreamList(){
        dreams = new ArrayList<Dream>();
    }
    public dreamList(ArrayList<Dream> dreams){
        this.dreams = dreams;

    }
    public ArrayList<Dream> getDreams(){
        return this.dreams;
    }

    public void setDreams(ArrayList<Dream> dreams){
        this.dreams = dreams;
    }


}
