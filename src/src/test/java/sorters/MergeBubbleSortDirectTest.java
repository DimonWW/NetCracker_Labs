package sorters;

public class MergeBubbleSortDirectTest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new BubbleSortDirect()));
    }
}