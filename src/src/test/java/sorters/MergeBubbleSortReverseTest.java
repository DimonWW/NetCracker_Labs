package sorters;

public class MergeBubbleSortReverseTest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new BubbleSortReverse()));
    }
}