package fillers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseSortedFillerWithNull {
    private int[] array;

    @Before
    public void init() {
        array = new int[100];
    }

    @Test(expected = NullPointerException.class)
    public void reverseSortedFillerWithNull() {
        ArrayFillers.reverseSortedFiller(null, 1, 20);
    }

}
