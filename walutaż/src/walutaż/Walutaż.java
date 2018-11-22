package walutaż;

import Graph.Graph;
import Load.Load;
import java.io.FileNotFoundException;
import java.util.List;

public class Walutaż {

    public static void main(String[] args) throws FileNotFoundException {
        Load load = new Load(args[0]);
        Graph graph = load.load();
        List<String> result = graph.getBestExchenge("RUB", "AAA", 1000);
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}
