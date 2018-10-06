package kopiec_v1;

import java.util.ArrayList;

public class Kopiec {

    private ArrayList<Double> list;
    int elem_n;

    public Kopiec() {
        list = new ArrayList();
        elem_n = 0;
    }

    public void add(double x) {
        list.add(elem_n, x);
        elem_n++;
        if (elem_n >= 2) {
            balance();
        }
        // System.out.print(elem_n);
    }

    private void balance() {
        int child = elem_n - 1;
        int parent = 1;
        while (parent > 0) {
            parent = (child) / 2;
            if (list.get(child) > list.get((parent))) {
                swap(child, parent);
            }
            child = parent;

        }
    }

    public void balancefromtop(int size) {
        int current = 0;
        int lchild = 1;
        int rchild = 2;
        while (lchild <= size) {
            if (list.get(lchild) > list.get(current) && ( list.get(lchild) >= list.get(rchild) || rchild > size)) {
                swap(lchild, current);
                current = lchild;
            } else if (list.get(rchild) > list.get(current) && rchild <= size) {
                swap(rchild, current);
                current = rchild;
            } else {
                return;
            }
            lchild = 2 * current + 1;
            rchild = 2 * current + 2;
        }
    }

    private void swap(int c, int p) {
        double tmp = list.get(c);
        list.set(c, list.get(p));
        list.set(p, tmp);
    }

    public void sort() {
        int current = elem_n - 1;
        while (current >= 0) {
            swap(0, current);
            current--;
            balancefromtop(current);
        }
    }

    public void fill() {
        int n = 0;
        for (int i = 0; i < 52; i++) {
            list.add(i, (double) ( i));
            elem_n++;
            balance();
        }
    }

    @Override
    public String toString() {
        return "Kopiec{" + "list=" + list + ", elem_n=" + elem_n + '}';
    }

    public static void main(String[] args) {
        Kopiec test = new Kopiec();
        test.fill();
//        test.add(14);
//        test.add(15);
//        test.add(45);
//        test.add(0);
        System.out.println(test);

        test.sort();
        System.out.print(test);

    }

}
