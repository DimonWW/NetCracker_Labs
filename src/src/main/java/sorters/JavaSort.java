package sorters;

import java.util.Arrays;

/**
 * Class class which contains {@link Arrays#sort(int[])} algorithm
 * @author Dima Korenko
 */
@SorterClass(name = "Java sort")
public class JavaSort implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}