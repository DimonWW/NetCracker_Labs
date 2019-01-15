package sorters;

/**
 * Class class which holds a child of {@link ArraySorter} marked as {@link SortType#DEFAULT}
 * @author Dima Korenko
 */
@SorterClass(name = "Merge sort", type = SortType.WITH_PARAM)
public class MergeSort implements ArraySorter {

    /**
     * {@link ArraySorter} marked as {@link SortType#DEFAULT}
     */
    private final ArraySorter sorter;

    /**
     * @param sorter marked as {@link SortType#DEFAULT}
     */
    public MergeSort(ArraySorter sorter) {
            this.sorter = sorter;
    }

    @Sorter
    @Override
    public void sort(int[] array) {
        divide(array);
    }

    /**
     * Divides array and sort both part using {@link #sorter#sort(int[])} and then merge it
     * @param array to sort
     */
    public void divide(int[] array) {
        int m = array.length / 2;
        int[] arr1 = new int[m];
        int[] arr2 = new int[array.length - m];
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, m, arr2, 0, arr2.length);
        sorter.sort(arr1);
        sorter.sort(arr2);
        merge(array, arr1, arr2);
    }

    /**
     * Method merge first and second part of array
     * @param array result
     * @param arr1 first part
     * @param arr2 second part
     */
    public void merge(int[] array, int[] arr1, int[] arr2) {
        int j = 0;
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (j == arr1.length) {
                array[i] = arr2[k++];
            } else if (k == arr2.length) {
                array[i] = arr1[j++];
            } else if (arr1[j] > arr2[k]) {
                array[i] = arr2[k++];
            } else {
                array[i] = arr1[j++];
            }
        }
    }
}