package sorters;

import fillers.Fillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Sorts {

    /*
     *Метод сортировки пузырьком от начала к концу
     * */
    public int[] BubbleSortDirect(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /*
     *Метод сортировки пузырьком от начала к концу
     * */
    public int[] BubbleSortReverse(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = array.length - 2; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /*
     *Метод сортировки QuickSort
     * */
    private static int[] QuickSortReal(int[] array, int low, int high) {
        int i = low;
        int j = high;
        int x = array[low + (high - low) / 2];
        do {
            while (array[i] < x) ++i;
            while (array[j] > x) --j;
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        //рекурсивные вызовы функции qSort
        if (low < j) QuickSortReal(array, low, j);
        if (i < high) QuickSortReal(array, i, high);
        return array;
    }

    /*
     *Метод сортировки с помощью Arrays.sort(int[] a).
     * */
    public static int[] QuickSort(int[] array) {
        QuickSortReal(array, 0, array.length - 1);
        return array;
    }

    public int[] arraysSort(int[] array) {
        Arrays.sort(array);
        return array;
    }

    private int[] merge(int[] arr_1, int[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }


    /*
    Сортировок с использованием метода половинного деления и предыдущих сортировок
    */
    public int[] BubbleSortDirectMod(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(BubbleSortDirect(Arrays.copyOfRange(arr, 0, middle)),
                BubbleSortDirect(Arrays.copyOfRange(arr, middle, len)));
    }

    public int[] BubbleSortReverseMod(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(BubbleSortReverse(Arrays.copyOfRange(arr, 0, middle)),
                BubbleSortReverse(Arrays.copyOfRange(arr, middle, len)));
    }

    public int[] QuickSortMod(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(QuickSort(Arrays.copyOfRange(arr, 0, middle)), //, 0, middle - 1
                QuickSort(Arrays.copyOfRange(arr, middle, len))); //, 0, len - middle - 1
    }

    public int[] arraysSortMod(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(arraysSort(Arrays.copyOfRange(arr, 0, middle)),
                arraysSort(Arrays.copyOfRange(arr, middle, len)));
    }


}
