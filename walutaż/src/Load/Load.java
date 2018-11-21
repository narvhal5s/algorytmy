package Load;

import Graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Load {

    private final String filename;

    public Load(String filename) {
        this.filename = filename;
    }

    public Graph load() throws FileNotFoundException {
        //int lineNumber = checkData();
        Scanner goodFile = new Scanner(new FileReader("./dataForTest/" + filename));
        String buffor;
        Graph graph = new Graph();

        while (goodFile.hasNextLine()) {
            buffor = goodFile.nextLine();
            String[] tmp = buffor.split("\\s");
            if ("#".equals(tmp[0])) {
                break;
            } else {
                graph.addVertex(tmp[1]);
            }
        }
        while (goodFile.hasNextLine()) {
            buffor = goodFile.nextLine();
            String[] tmp = buffor.split("\\s");
            graph.addRate(tmp[1], tmp[2], Double.parseDouble(tmp[3]), tmp[4], Double.parseDouble(tmp[5]));
        }
        return graph;
    }

    public int checkData() throws FileNotFoundException {
        Scanner checker = new Scanner(new File("./dataForTest/" + filename));

        int vertexNumber = 0;
        int rateNumber = 0;

        while (checker.hasNextLine()) {
            String line = checker.nextLine();
            String[] tokens = line.split("\\s");
            if ("#".equals(tokens[0])) {
            } else {
                if (tokens[0].equals(vertexNumber)
                        && tokens[1].matches("\\A-Z{3}")
                        && tokens[2].matches("\\.+")) {
                    vertexNumber++;
                } else {
                    return vertexNumber;
                }
            }
        }
        return -1;
    }

}
