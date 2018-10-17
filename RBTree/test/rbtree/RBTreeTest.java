/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GGWP
 */
public class RBTreeTest {

    public RBTreeTest() {
    }

    @Test
    public void testSetValue() {
        RBTree<Integer, String> test = new RBTree<>();
        test.setValue(0, "Dupa");
    }

    @Test
    public void testGetValue() {
        RBTree<Integer, String> test = new RBTree<>();
        test.setValue(1, "Jeden");
        test.setValue(0, "Zero");
        test.setValue(2, "Dwa");
        test.setValue(-1, "ujemna");
        test.setValue(3, "Trzy");
        test.setValue(4, "Cztery");
        test.setValue(5, "Pięćaea");
        test.setValue(6, "Sześć");
        assertEquals("Pięćaea", test.getValue(5));
    }
    
    @Test
    public void testSameKey(){
        RBTree<Integer, String> test = new RBTree<>();
        test.setValue(1, "Jeden");
        test.setValue(1, "Jeden");
        test.setValue(1, "Jeden");
        test.setValue(1, "Jeden");
        assertEquals( 1 , test.getSize());
    }
    
    @Test
    public void testKeyNotExist(){
        RBTree<Integer, String> test = new RBTree<>();
        test.setValue(1, "Jeden");
        test.setValue(0, "Zero");
        test.setValue(2, "Dwa");
        test.setValue(-1, "ujemna");
        test.setValue(3, "Trzy");
        test.setValue(4, "Cztery");
        test.setValue(5, "Pięćaea");
        test.setValue(6, "Sześć");
        assertNull(test.getValue(7));
    
        
    }

    @Test
    public void testMain() {
        RBTree<Integer, Integer> test = new RBTree<>();
        for (Integer i = 0; i <= 2500000; i++) {
            test.setValue(i, i + 2);
        }
        assertEquals((Integer) 2502, test.getValue(2500));
    }
}


