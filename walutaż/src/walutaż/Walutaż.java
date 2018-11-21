package walutaż;

import Graph.Graph;
import Load.Load;
import java.io.FileNotFoundException;
import java.util.List;

public class Walutaż {

    public static void main(String[] args) throws FileNotFoundException {
        Load load = new Load(args[0]);
        Graph graph = load.load();
        graph.setInCurrencyValue("EUR", 1000);
        List<String> result = graph.getBestExchenge("EUR", "USD") ;
        System.out.println(result);
    }

}
