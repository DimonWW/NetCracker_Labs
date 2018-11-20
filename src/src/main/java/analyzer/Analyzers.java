package analyzer;

import fillers.Fillers;
import output.Outputs;
import sorters.Sorts;

public class Analyzers {
    public static long end;
    public static long start;

    public static void main(String[] args) {

        Outputs out = new Outputs();
        Sorts sort = new Sorts();
        Fillers fillers = new Fillers();


        out.showGenArr();

        System.out.println("\n\t\t\tСортировка пузырьком");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.BubbleSortDirect(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.BubbleSortDirect(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();


        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.BubbleSortDirect(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.BubbleSortDirect(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        ///////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tСортировка пузырьком обратно");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.BubbleSortReverse(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.BubbleSortReverse(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.BubbleSortReverse(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.BubbleSortReverse(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        /////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tQuickSort");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.QuickSort(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.QuickSort(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.QuickSort(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.QuickSort(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        //////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tArrays.sort");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.arraysSort(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.arraysSort(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.arraysSort(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.arraysSort(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        /////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tСортировка пузырьком + слияние");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.BubbleSortDirectMod(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.BubbleSortDirectMod(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.BubbleSortDirectMod(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.BubbleSortDirectMod(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        ///////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tСортировка пузырьком обратно + слияние");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.BubbleSortReverseMod(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.BubbleSortReverseMod(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.BubbleSortReverseMod(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.BubbleSortReverseMod(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        //////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tQuickSort + слияние");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.QuickSortMod(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.QuickSortMod(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.QuickSortMod(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.QuickSortMod(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();

        //////////////////////////////////////////////////////////

        System.out.println("\n\t\t\tArrays.sort + слияние");

        out.showGenArraySort();
        start = System.nanoTime();
        sort.arraysSortMod(fillers.genArraySort());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortRand();
        start = System.nanoTime();
        sort.arraysSortMod(fillers.genArraySortRand());
        end = System.nanoTime();
        out.showResults();

        out.showGenArraySortReverse(fillers.genArraySortReverse());
        start = System.nanoTime();
        sort.arraysSortMod(fillers.genArraySortReverse());
        end = System.nanoTime();
        out.showResults();

        out.showGenArrayRandom();
        start = System.nanoTime();
        sort.arraysSortMod(fillers.genArrayRandom());
        end = System.nanoTime();
        out.showResults();


    }

}
