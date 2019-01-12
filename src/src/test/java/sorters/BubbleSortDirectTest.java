package sorters;

public class BubbleSortDirectTest extends SortTest{

    @Override
    public void sort(int[] array) {
        selectSorter(array, new BubbleSortDirect());
    }
}
