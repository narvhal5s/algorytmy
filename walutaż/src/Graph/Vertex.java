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
    boolean base = false;
    Vertex parrent = null;

    Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    void checkNeighbour(Queue<Vertex> queue) {
        Vertex visiting;

        for (int i = 0; i < neighbourList.size(); i++) {
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value) {
                visiting.value = newValue;
                visiting.parrent = this;
                queue.add(visiting);
                //RETURN ARBITRAZOWY
            }
            if (!visiting.visit) {
                queue.add(visiting);
            }

            visiting.visit = true;
        }
    }
}
