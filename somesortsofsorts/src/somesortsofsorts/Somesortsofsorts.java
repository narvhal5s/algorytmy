package somesortsofsorts;

import java.util.Arrays;

public class Somesortsofsorts {

    public static void main(String[] args) {
        SortingAlgorithm first = new InSort();
        double[] tab = new double[10000];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Math.random() * 20.0;
        }
        long end ;
        long start = System.nanoTime();
        first.sort(tab);
        end = System.nanoTime() - start;
//        for (int i = 0; i < tab.length; i++) {
//            System.out.print(tab[i] + "  ");
//        }
        System.out.println(end);
    }
}
