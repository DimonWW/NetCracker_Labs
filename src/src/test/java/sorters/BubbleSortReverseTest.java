package sorters;

public class BubbleSortReverseTest extends SortTest{

    @Override
    public void sort(int[] array) {
        selectSorter(array, new BubbleSortReverse());
    }
}
