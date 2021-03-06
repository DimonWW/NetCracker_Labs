package sorters;

/**
 * Class class which contains quick sort algorithm
 * @author Dima Korenko
 */
@SorterClass(name = "Quick sort")
public class QuickSort
        extends BubbleSort
        implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Quick sort algorithm
     */
    private void quickSort(int[] array, int start, int end) {
        if (array == null || array.length == 0)
            return;
        if (start >= end)
            return;
        int i = start;
        int j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(array, start, cur);
        quickSort(array,cur + 1, end);
    }
}
