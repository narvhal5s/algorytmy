package Load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Load {

    private final String filename;

    public Load(String filename) {
        this.filename = filename;
    }

    public void load() throws FileNotFoundException {
        int lineNumber = checkData();
        Scanner goodFile = new Scanner(new File("./dataForTest/" + filename));

    }

    public int checkData() throws FileNotFoundException {
        Scanner checker = new Scanner(new File("./dataForTest/" + filename));
        
        int vertexNumber = 0 ;
        int rateNumber = 0 ;
        
        while (checker.hasNextLine()) {
            String line = checker.nextLine();
            String[] tokens = line.split("\\s");
            if (tokens[0] == "#") {
            } else {
                if(tokens[0].equals(vertexNumber) &&
                   tokens[1].matches("\\A-Z{3}") &&
                   tokens[2].matches("\\.+")){
                    vertexNumber ++;
                } else{
                    return vertexNumber ;
                }
            }

        }
        return -1 ;
    }
}
