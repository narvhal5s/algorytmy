package walutaż;

import Graph.Graph;
import Load.Load;
import java.io.FileNotFoundException;

public class Walutaż {

    public static void main(String[] args) throws FileNotFoundException {

        Load load = new Load(args[0]);
        Graph graph = load.load();
        System.out.println( graph );

    }

}
