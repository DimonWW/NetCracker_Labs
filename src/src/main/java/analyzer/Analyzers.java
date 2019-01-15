package analyzer;

import fillers.Fillers;
import fillers.FillersClass;
import output.ResultHolder;
import sorters.SortType;
import sorters.SorterClass;
import org.reflections.Reflections;
import sorters.ArraySorter;
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
    private static final String PATH = "lab1";

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
     * Method makes creating {@return List<ResultHolder>}
     */
    public List<ResultHolder> analyze(int elementCount, boolean withColdStart, int numberOfTests) {
        ArrayList result = new ArrayList<ResultHolder>();
        int[] arr = new int[elementCount];
        for (SortsClassHolder sorter : sorters) {
            for (Method filler : fillers) {
                if (!withColdStart) {
                    prepareSorter(sorter, filler);
                }
                long testStart = nanoTime();
                for (int repeat = 0; repeat < numberOfTests; repeat++) {
                    test(sorter, filler, arr);
                }
                result.add(new ResultHolder((int) ((nanoTime() - testStart) / numberOfTests), sorter.getName(), filler.getAnnotation(Fillers.class).name()));
            }
        }
        return result;
    }

    public List<ResultHolder> analyze(int elementCount) {
        return analyze(elementCount, false, 10);
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