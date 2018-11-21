package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Vertex {

    String name;
    List<Rate> neighbourList;
    double value = 0;
    boolean visit = false;
    boolean check = false;
    int visit_counter = 0;
    Vertex parrent = null;

    Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    void checkNeighbour(Queue<Vertex> queue, String inCurrency) {
        Vertex visiting;

        for (int i = 0; i < neighbourList.size(); i++) {
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value && visiting.parrent != this) {
                if (visiting.visit_counter > 0 && visiting.name.equals(inCurrency)) {
                    //Mamy arbitraz
                    visiting.visit_counter++;
                    return;
                }
                visiting.value = newValue;
                if (!(this.parrent == visiting)) {
                    visiting.parrent = this;
                }
                queue.add(visiting);
                visiting.visit_counter++;
            }
            if (!visiting.visit) {
                queue.add(visiting);
            }
            visiting.visit = true;
        }
    }
}
