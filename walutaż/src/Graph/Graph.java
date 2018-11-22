package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private final List<Vertex> vertexList;
    private int rateCounter = 0;

    public Graph() {
        vertexList = new ArrayList<>();
    }

    public void addVertex(String code) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).name.equals(code)) {
                System.out.println("Waluta o kodzie " + code + " już istnieje, nie zostala dodana powtornie");
                System.out.println("Waluta ta znajduje się w wierszu " + vertexList.size());
                return;
            }
        }
        vertexList.add(new Vertex(code));
    }

    public void addRate(String currencyName1, String currencyName2, double value, String provisionType, double provision) {

        Vertex vertexFrom;
        Vertex vertexTo;
        boolean added = false;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(currencyName1)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(currencyName2)) {
                        vertexFrom.neighbourList.add(new Rate(vertexTo, value, provisionType, provision));
                        added = true;
                        break;
                    }
                }
            }
        }
        if (!added) {
            System.out.println("Nie dodano kursu pomiedzy " + currencyName1 + " " + currencyName2 + " jedna z walut nie istnieje");
            System.out.println("Kurs ten znajduje sie w w wierszu " + rateCounter);
        }
        rateCounter++;
    }

    public List<String> getBestExchenge(String inCurrency, String outCurrency, double value) {
        checkGraphForBestExchenge(inCurrency, value);
        List result = readBestRoad(inCurrency, outCurrency);
        return result;
    }

    public List<String> getArbitrag(double value) {
        String vertexName = checkGraphForArbitrage(value);
        List result = readBestRoad(vertexName, vertexName);
        return result;
    }

    private void checkGraphForBestExchenge(String inCurrency, double value) {
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
            vertexFrom = queue.remove();
            if (!vertexFrom.check) {
                vertexFrom.checkNeighbourWithCycleBreak(queue, inCurrency);
                vertexFrom.check = true;
            }
        }
    }

    private String checkGraphForArbitrage(double value) {
        String result = null;
        Queue<Vertex> queue = new ArrayDeque<>();
        Vertex checkThis;
        Vertex vertexFrom;

        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {
                vertexFrom = vertexList.get(j);
                vertexFrom.value = 0;
                vertexFrom.check = false;
                vertexFrom.parrent = null;
            }
            checkThis = vertexList.get(i);
            checkThis.value = value;
            queue.add(checkThis);
            while (!queue.isEmpty()) {
                vertexFrom = queue.remove();
                if (!vertexFrom.check) {
                    result = vertexFrom.checkNeighbourForArbitrag(queue);
                    vertexFrom.check = true;
                }
                if (result != null && checkThis.name.equals(result)) {
                    break;
                }
            }
            if (result != null && checkThis.name.equals(result)) {
                break;
            }
        }
        return result;
    }

    private List<String> readBestRoad(String inCurrency, String outCurrency) {
        List<String> result = new ArrayList<>();
        Vertex vertexFrom = null;

        if (inCurrency == null) {
            result.add("Nie istnieje arbitraz");
            return result;
        }

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                break;
            }
        }

        if (vertexFrom.parrent == null) {
            result.add("Nie istnieje");
            return result;
        }

        result.add(outCurrency);
        vertexFrom = vertexFrom.parrent;

        while (!vertexFrom.name.equals(inCurrency)) {
            result.add(vertexFrom.name);
            vertexFrom = vertexFrom.parrent;
        }

        System.out.println(vertexList.toString());

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
