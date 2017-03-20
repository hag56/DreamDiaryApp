package is.hi.hbv601g.draumadagbok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Halli on 19.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class dreamList {

    private List<Dream> dreams;

    public dreamList(){
        dreams = new ArrayList<Dream>();
    }
    public dreamList(List<Dream> dreams){
        this.dreams = dreams;

    }
    public List<Dream> getDreams(){
        return this.dreams;
    }

    public void setDreams(List<Dream> dreams){
        this.dreams = dreams;
    }

    public void addDream(Dream dream){
        this.dreams.add(dream);
    }

}
