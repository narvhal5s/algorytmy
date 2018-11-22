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
        boolean cyclebreak = false;

        for (int i = 0; i < neighbourList.size(); i++) {
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value) {
//                if (visiting.visit_counter > 0 && visiting.name.equals(inCurrency)) {
//                    //Mamy arbitraz
//                    visiting.parrent = this;
//                    visiting.visit_counter++;
//                    return;
//                }
                Vertex grandparrent = this.parrent;
                while (grandparrent != null) {
                    System.out.println(grandparrent.name);
                    if (grandparrent == visiting) {
                        cyclebreak = true;
                    }
                    grandparrent = grandparrent.parrent;
                }
                if (!cyclebreak) {
                    visiting.value = newValue;
                    visiting.parrent = this;
                    visiting.visit_counter++;

                }
            }
            visiting.visit = true;
            queue.add(visiting);
            cyclebreak = false;
        }
    }

    @Override
    public String toString() {
        return "Vertex{" + "name=" + name + ", value=" + value + '}';
    }

}
