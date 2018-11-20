package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertexList;

    public Graph() {
        vertexList = new ArrayList<>();
    }

    public void addVertex(String code) {
        vertexList.add(new Vertex(code));
    }

    public void addRate(String currencyName1, String currencyName2, double value, String provisionType, double provision) {

        Vertex vertexFrom = null;
        Vertex vertexTo = null;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(currencyName1)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(currencyName2)) {
                        vertexFrom.neighbourList.add(new Rate (vertexTo, value, provisionType, provision) );
                    }
                }
            }
        }
    }

}
