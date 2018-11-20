package fillers;


import java.util.Arrays;
import java.util.Random;


public class Fillers {
    private static int N = 20;
    public static int[] genArr = new int[N];
    private static int[] array = new int[N];
    private static int[] arrayReserve = new int[N];
    private static int[] arrayBig = new int[N + 1];

    private static Random random = new Random();

    //Массив из рандомных элементов в диапазоне 0-100 в рандомном порядке
    public static int[] GeneretedArray(){
        for (int i = 0; i < N; i++) {
            genArr[i] =  (random.nextInt(100));
        }
        return genArr;
    }

    public static int getN() {
        return N;
    }

    //отсортированный массив
    public static int[] genArraySort() {

        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = genArr[i];
        }
        Arrays.sort(array);
        return array;
    }


    //отсортированный массив +1 рандомный элемент в конце
    public static int[] genArraySortRand() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arrayBig[i] = array[i];
        }
        arrayBig[N] = random.nextInt(N + 1);
        return arrayBig;
    }


    public static int[] genArraySortReverse() {
        int temp;
        for (int i = 0; i < N / 2; i++) {

            temp = array[i];
            array[i] = array[N - i - 1];
            array[N - i - 1] = temp;
        }
        return array;
    }

    public static int[] getReverse(){
        for (int i = 0; i < N; i++) {
            arrayReserve[i] = array[i];
        }

        return arrayReserve;
    }


    public static int[] genArrayRandom(){
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int j = random.nextInt(i+1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }



}
