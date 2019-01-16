package analyzer;

import fillers.Fillers;
import fillers.FillersClass;
import output.ResultTable;
import sorters.SortType;
import sorters.SorterClass;
import output.ResultRecord;
import sorters.ArraySorter;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.lang.System.nanoTime;

/**
 * Class for analysing {@link fillers.ArrayFillers} and child of {@link ArraySorter}
 * @author Dima Korenko
 */
public class Analyzers {
    public static final int[] DEFAULT_ELEMENT_COUNT_FOR_TESTS = {10, 100, 500, 1000, 2000, 5000, 10000};

    /**
     * Field storing {@link Set} of methods marked as {@link Fillers}
     */
    private Set<Method> fillers = new HashSet<>();

    /**
     * Field storing {@link Set} of {@link SortsClassHolder}
     */
    private Set<SortsClassHolder> sorters = new HashSet<>();

    /**
     * Constructor searching for classes marked as {@link FillersClass} or {@link SorterClass} and storing them into
     * {@link #fillers} and {@link #sorters}
     *
     * @param pathToClasses path where searching classes marked as {@link FillersClass} or {@link SorterClass}
     */
    public Analyzers(String pathToClasses) {
        Set<Class<?>> sortReflection = new Reflections(pathToClasses).getTypesAnnotatedWith(SorterClass.class);
        for (Class<?> c : sortReflection)
        {
            if (c.getAnnotation(SorterClass.class).type() == SortType.WITH_PARAM) {
                for (Class<?> cParam : sortReflection) {
                    if (cParam.getAnnotation(SorterClass.class).type() == SortType.DEFAULT) {
                        sorters.add(new SortsClassHolder(c, cParam));
                    }
                }
                continue;
            }
            sorters.add(new SortsClassHolder(c));
        }
        for (Class<?> c : new Reflections(pathToClasses).getTypesAnnotatedWith(FillersClass.class)) {                for (Method method : c.getMethods()) {
                    if (method.isAnnotationPresent(Fillers.class)) {
                        fillers.add(method);
                    }
                }
            }
    }

    /**
     * Method use {@param sorter} and {@param filler} to test them using {@param arr}
     */
    private void test(SortsClassHolder sorter, Method filler, int[] arr) {
        try {
            filler.invoke(null, arr, 0, arr.length);
            sorter.invoke(arr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method makes creating {@return List<ResultTable>}
     */
    public List<ResultTable> analyze(int[] elementCounts, boolean withColdStart, int numberOfTests) {
        ArrayList result = new ArrayList<ResultTable>();
        for (Method filler : fillers) {
            ResultTable sheet = new ResultTable();
            for (int elementCount : elementCounts) {
                int[] arr = new int[elementCount];
                for (SortsClassHolder sorter : sorters) {
                    if (!withColdStart) {
                        prepareSorter(sorter, filler);
                    }
                    long testStart = nanoTime();
                    for (int repeat = 0; repeat < numberOfTests; repeat++) {
                        test(sorter, filler, arr);
                    }
                    sheet.add(new ResultRecord((int) ((nanoTime() - testStart) / numberOfTests), elementCount, sorter.getName(), filler.getAnnotation(Fillers.class).name()));
                }
            }
            result.add(sheet);
        }
        return result;
    }

    /**
     * Method prepares the sorter to work
     */
    private void prepareSorter(SortsClassHolder sorter, Method filler) {        int[] arr = new int[100];
        for (int i = 0; i < 5; i++) {
            test(sorter, filler, arr);
        }
    }
}