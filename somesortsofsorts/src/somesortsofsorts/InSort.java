package somesortsofsorts;

public class InSort implements SortingAlgorithm {

    public InSort() {
    }

    @Override
    public double[] sort(double[] unsortedVector) {

        if (unsortedVector == null) {
            throw new IllegalArgumentException("Podana tablica jest pusta");
        }

        int ln = unsortedVector.length;
        double current;
        int in , out;

        for (out = 1; out < ln; out++) {
            
            current = unsortedVector[out];
            in = out;
            
            while (in > 0 && unsortedVector[in - 1] >= current) {
                unsortedVector[in] = unsortedVector[in - 1];
                --in;
            }
            
            unsortedVector[in] = current;
        
        }

        return unsortedVector;

    }
    
}
