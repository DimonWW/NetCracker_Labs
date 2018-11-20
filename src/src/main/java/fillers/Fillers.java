package fillers;


import java.util.Arrays;
import java.util.Random;


public class Fillers {
    private static int N = 20; // Ввод количества элементов в массиве. Можно переделать под консольный ввод
    public static int[] genArr = new int[N];
    private static int[] array = new int[N];
    private static int[] arrayReserve = new int[N];
    private static int[] arrayBig = new int[N + 1];
    private static Random random = new Random();

    /*
     *Метод для создания массива с отсортироваными элементами
     * */
    public static int[] genArraySort() {

        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = genArr[i];
        }
        Arrays.sort(array);
        return array;
    }

    /*
     *Метод для создания массива с отсортироваными элементами и одним рандомным элементом в конце
     * */
    public static int[] genArraySortRand() {
        for (int i = 0; i < N; i++) {
            arrayBig[i] = array[i];
        }
        arrayBig[N] = random.nextInt(N + 1);
        return arrayBig;
    }

    /*
     *Метод для создания обратного массива
     * */
    public static int[] genArraySortReverse() {
        int temp;
        for (int i = 0; i < N / 2; i++) {

            temp = array[i];
            array[i] = array[N - i - 1];
            array[N - i - 1] = temp;
        }
        return array;
    }

    /*
     *Метод для создания полностю рандомного массива
     * */
    public static int[] genArrayRandom(){
        for (int i = 0; i < N; i++) {
            int j = random.nextInt(i+1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }



}
