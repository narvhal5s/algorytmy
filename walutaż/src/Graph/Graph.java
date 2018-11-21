package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
                        vertexFrom.neighbourList.add(new Rate(vertexTo, value, provisionType, provision));
                    }
                }
            }
        }
    }

    public List<String> getBestExchenge(String inCurrency, String outCurrency) {
        checkGraph(inCurrency);

        List result = readBestRoad(inCurrency, outCurrency);

        return result;
    }

//    public List<Vertex> getArbitrag(String inCurrency) {
//        checkGraph(inCurrency);
//        List result = readBestRoad(inCurrency);
//        return result;
//    }
    private void checkGraph(String inCurrency) {
        Vertex vertexFrom = null;
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(inCurrency)) {
                queue.add(vertexFrom);
            }
        }

        while (!queue.isEmpty()) {
            vertexFrom = queue.remove();
            for (int i = 0; i < vertexFrom.neighbourList.size(); i++) {
                vertexFrom.neighbourList.get(i).vertexOut.checkNeighbour(queue);
                vertexFrom.check = true;
            }
        }

    }

    private List<String> readBestRoad(String inCurrency, String outCurrency) {
        List<String> result = new ArrayList<>();
        Vertex vertexFrom = null;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                break;
            }
        }

        result.add(outCurrency);

        while (!vertexFrom.name.equals(inCurrency)) {
            result.add(vertexFrom.parrent.name);
            vertexFrom = vertexFrom.parrent;
        }

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                System.out.println(vertexFrom.value);
                break;
            }
        }
        return result;
    }

    public void setInCurrencyValue(String inCurrency, double value) {
        Vertex vertexFrom = null;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(inCurrency)) {
                vertexFrom.value = value;
            }
        }
    }
}
