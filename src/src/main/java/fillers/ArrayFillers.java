package fillers;


import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


@FillersClass
public abstract class ArrayFillers {

    private static void reverse(int[] array) {
        int i = 0;

        for (int j = array.length - 1; j > i; i++) {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    @Fillers(name = "Simple filler")
    public static void simpleFiller(int[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
    }

    @Fillers(name = "Reverse sorted filler")
    public static void reverseSortedFiller(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
        reverse(arr);
    }

    @Fillers(name = "Sorted filler")
    public static void sortedFiller(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
    }

    @Fillers(name = "Sorted filler X")
    public static void sortedFillerX(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }

}
