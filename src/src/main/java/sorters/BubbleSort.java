package sorters;

public abstract class BubbleSort {
    protected void swap(int[] array, int i, int j){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
