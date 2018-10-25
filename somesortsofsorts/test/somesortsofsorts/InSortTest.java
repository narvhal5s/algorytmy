package somesortsofsorts;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class InSortTest {
    
     //Zasada do sprawdzania czy program zgłasza wyjątek przy nieprawidłowych danych
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    
    public InSortTest() {
        
    }

    @Test
    public void testNull() {

        SortingAlgorithm test = new InSort();
        double[] tab = null;

        exception.expect(IllegalArgumentException.class);
        test.sort(tab);

    }

    @Test
    public void testOne() {

        //given
        SortingAlgorithm test = new InSort();
        double[] tab = {1D};

        //when
        tab = test.sort(tab);
        double actuals = tab[0];
        double expected = 1.0;

        //then
        assertEquals(expected, actuals, 0);

    }

    @Test
    public void testSort() {

        //given
        SortingAlgorithm test = new InSort();
        int x = 1000 ;
        double[] expecteds = new double[x];
        double[] actuals = new double[x];
        double rand;
        for (int i = 0; i < expecteds.length; i++) {
            rand = Math.random();
            expecteds[i] = rand;
            actuals[i] = rand;
        }

        //when
        actuals = test.sort(actuals);
        Arrays.sort(expecteds);
        //then
        assertArrayEquals(expecteds, actuals, 0);
    }

    
}
