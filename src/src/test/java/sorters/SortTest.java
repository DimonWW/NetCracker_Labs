package sorters;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public abstract class SortTest {

    @Test
    public void baseSort() {
        int[] array = {231, 43, 457, 7, 76, 45, 7658, 67, 5, 87, 43, 7433, 457353, 34635,};
        int[] result = array.clone();

        sort(array);
        Arrays.sort(result);
        assertArrayEquals(array, result);
    }

    @Test(expected = NullPointerException.class)
    public void sortNull() {
        sort(null);
    }

    protected void selectSorter(int[] array, ArraySorter sorter) {
        sorter.sort(array);
    }

    public abstract void sort(int[] array);

}