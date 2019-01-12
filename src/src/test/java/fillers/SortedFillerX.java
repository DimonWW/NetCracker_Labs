package fillers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedFillerX {
    private int[] array;

    @Before
    public void init() {
        array = new int[100];
    }

    @Test
    public void sortedFillerX() {
        ArrayFillers.sortedFillerX(array, 1, 20);
        for (int i = 1; i < array.length; i++) {
            assertNotEquals(array[i], 0);
        }
    }

}
