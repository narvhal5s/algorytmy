package somesortsofsorts;

public class MergeSort implements SortingAlgorithm {

    public MergeSort() {
    }

    @Override
    public double[] sort(double[] unsortedVector) {

        if (unsortedVector == null) {
            throw new IllegalArgumentException("Podana tablica jest pusta");
        }

        int right;

        for (int left = 1; left <= unsortedVector.length ; left *= 2) {

            for (int middle = left; middle < unsortedVector.length; middle += 2 * left) {
                right = Math.min(middle + left, unsortedVector.length);
                merge(unsortedVector, middle - left, middle, right);
            }

        }

        return unsortedVector;

    }

    private void merge(double[] tab, int start, int middle, int end) {

        double[] merge = new double[end - start];
        //Liczniki ile lewych posortowano , ile prawych posortowana
        //oraz miejsce w tablicy merge na które obcnie wstawiamy
        int leftsorted = 0, rightsorted = 0, mergeindex = 0;
        int lefttosort = middle - start, righttosort = end - middle;
        while (leftsorted < lefttosort && rightsorted < righttosort) {

            if (tab[start + leftsorted] < tab[middle + rightsorted]) {
                merge[mergeindex] = tab[start + leftsorted];
                mergeindex++;
                leftsorted++;
            } else {
                merge[mergeindex] = tab[middle + rightsorted];
                mergeindex++;
                rightsorted++;
            }

        }

        while (rightsorted < righttosort) {
            merge[mergeindex] = tab[middle + rightsorted];
            mergeindex++;
            rightsorted++;
        }

        while (leftsorted < lefttosort) {
            merge[mergeindex] = tab[start + leftsorted];
            mergeindex++;
            leftsorted++;
        }

        //Kopiowanie wyniku działań do podanej tablicy
        System.arraycopy(merge, 0, tab, start, merge.length);

    }

}
