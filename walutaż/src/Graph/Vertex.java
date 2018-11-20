package Graph;

import java.util.ArrayList;
import java.util.List;


public class Vertex {
    String name ;
    List<Rate> neighbourList ;
    double value = 0 ;
    boolean visit = false ;
    boolean check = false ;
    boolean base = false ;
    Vertex parrent = null ;

    Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>() ;
    }

    
    
    
    void checkNeighbour(){
        
    }
    
    double calculateRateValue(){
        return 0D;
    }
    
}
