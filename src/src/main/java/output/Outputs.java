package output;

import analyzer.Analyzers;
import fillers.Fillers;

import java.util.Arrays;


public class Outputs {

    static Fillers fillers = new Fillers();
    static Analyzers analyzers = new Analyzers();

    public static void showGenArr(){

        System.out.println("Сгенерированый массив: "/* + Arrays.toString(fillers.GeneretedArray())*/);
    }

    public static void showGenArraySort(){
        System.out.print("Отсортированный массив:        "/* +  Arrays.toString(fillers.genArraySort()) + "\t\t"*/);
    }

    public static void showGenArraySortRand(){
        System.out.print("Массив со случайным элементом: "/* + Arrays.toString(fillers.genArraySortRand()) + "\t"*/);
    }

    public static void showGenArraySortReverse(int arr[]){
        System.out.print("Обратный массив:               "/* + Arrays.toString(fillers.getReverse()) + "\t\t"*/);//Arrays.toString(fillers.genArraySortReverse())
    }

    /*public static void showGenArraySortReverseAfter(int arr[]){
        System.out.print("Обратный массив после сорт:    " + Arrays.toString(fillers.genArraySortReverse()) + "\t\t");
    }*/

    public static void showGenArrayRandom(){
        System.out.print("Элементы в случайном порядке:  "/* + Arrays.toString(fillers.genArrayRandom()) + "\t\t"*/);
    }

    public static void showResults() {

        System.out.println((analyzers.end - analyzers.start) + " nanosec");


    }
}
