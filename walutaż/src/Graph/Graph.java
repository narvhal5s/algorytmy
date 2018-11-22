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

        Vertex vertexFrom;
        Vertex vertexTo;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(currencyName1)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(currencyName2)) {
                        vertexFrom.neighbourList.add(new Rate(vertexTo, value, provisionType, provision));
                        break;
                    }
                }
            }
        }
    }

    public List<String> getBestExchenge(String inCurrency, String outCurrency, double value) {
        checkGraph(inCurrency, value);

        List result = readBestRoad(inCurrency, outCurrency);

        return result;
    }

    public List<String> getArbitrag(String inCurrency, double value) {
        checkGraph(inCurrency, value);
        List result = readBestRoad(inCurrency, inCurrency);
        return result;
    }

    private void checkGraph(String inCurrency, double value) {
        Vertex vertexFrom;
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(inCurrency)) {
                vertexFrom.value = value;
                queue.add(vertexFrom);
                break;
            }
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.toString());
            vertexFrom = queue.remove();
            if (!vertexFrom.check) {
                vertexFrom.checkNeighbour(queue, inCurrency);
                vertexFrom.check = true;
                if (vertexFrom.visit_counter > 1 && vertexFrom.name.equals(inCurrency)) {
                    //Mamy arbitraz
                    return;
                }
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
        
        if(vertexFrom.parrent == null){
            result.add("Nie istnieje") ;
            return result;
        }

        result.add(outCurrency);
        vertexFrom = vertexFrom.parrent;

        while (!vertexFrom.name.equals(inCurrency)) {
            result.add(vertexFrom.name);
            vertexFrom = vertexFrom.parrent;
        }

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                System.out.println(vertexFrom.value);
                break;
            }
        }
        result.add(inCurrency);
        return result;
    }
    
    

}
