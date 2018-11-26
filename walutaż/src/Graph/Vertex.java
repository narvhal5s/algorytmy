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

    void checkNeighbourWithCycleBreak(Queue<Vertex> queue, String inCurrency) {
        Vertex visiting;
        boolean cyclebreak;

        for (int i = 0; i < neighbourList.size(); i++) {
            cyclebreak = true;
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value) {
                Vertex grandparrent = this.parrent;
                while (grandparrent != null ) {
                    if (grandparrent == visiting) {
                        cyclebreak = false;
                        break;
                    }
                    grandparrent = grandparrent.parrent;
                }
                if (cyclebreak) {
                    visiting.value = newValue;
                    visiting.parrent = this;
                    visiting.check = false;
                    if (!queue.contains(visiting)) {
                        queue.add(visiting);
                    }
                }
            }
        }
    }

    String checkNeighbourForArbitrag(Queue<Vertex> queue) {
        String result = null;
        Vertex visiting;
        boolean cycleconfirm = false;

        for (int i = 0; i < neighbourList.size(); i++) {
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value) {
                Vertex grandparrent = this.parrent;
                while (grandparrent != null) {
                    if (grandparrent == visiting) {
                        cycleconfirm = true;
                        break;
                    }
                    grandparrent = grandparrent.parrent;
                }
                if (cycleconfirm) {
                    visiting.parrent = this;
                    visiting.value = newValue;
                    return visiting.name;
                }
                visiting.value = newValue;
                visiting.parrent = this;
                if (!queue.contains(visiting)) {
                    queue.add(visiting);
                    visiting.check = false;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Vertex{" + "name=" + name + ", value=" + value + '}';
    }

}
