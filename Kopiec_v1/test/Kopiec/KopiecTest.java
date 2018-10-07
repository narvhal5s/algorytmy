/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kopiec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GGWP
 */
public class KopiecTest {

    public KopiecTest() {
    }
//
//    @Test
//    public void Test1() {
//        Object[] expecteds = {9.0, 7.0, 4.0, 5.0};
//        Kopiec test = new Kopiec();
//        test.add(5);
//        test.add(7);
//        test.add(4);
//        test.add(9);
//        Object[] actuals = test.getList();
//        assertArrayEquals(expecteds, actuals);
//    }

    @Test
    public void fillingTest() {
        Kopiec test = new Kopiec();
        int expected = 800;
        test.fill(expected);
        int actuals = test.getElem_n();
        assertEquals(expected, actuals);
    }

//    @Test
//    public void bigTest() {
//        Kopiec test = new Kopiec();
//        int x = 52;
//        test.fill(x);
//        test.sort();
//        Object[] actuals = test.getList();
//        Object[] expected = new Object[x];
//        for (int i = 0; i < x; i++) {
//            expected[i] = (double) i;
//        }
//        assertArrayEquals(expected, actuals);
//    }

    @Test
    public void rSortTest() {
        int size = 5000000;
        Kopiec test = new Kopiec();
        ArrayList<Double> testlist = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            testlist.add( (double)r.nextInt()) ;
        }
        test.loadList(testlist);
        test.sort();
        Collections.sort(testlist );
        ArrayList<Double> actuals  = test.getList() ;
        
        for(int i = 0 ; i < testlist.size() && i < actuals.size() ; i++){
            assertEquals( "In line" + i , testlist.get(i) , actuals.get(i));
        }
    }

}
