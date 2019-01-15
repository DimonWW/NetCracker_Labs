package sorters;

/**
 * Super class for all classes which use {@link #swap(int[], int, int)}
 * @author Dima Korenko
 */
public abstract class BubbleSort {
    protected void swap(int[] array, int i, int j){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
