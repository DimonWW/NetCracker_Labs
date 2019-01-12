package analyzer;

import fillers.Fillers;
import fillers.FillersClass;
import output.ResultHolder;
import sorters.SortType;
import sorters.SorterClass;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.lang.System.nanoTime;

public class Analyzers {
    private static final String PATH = "lab1";

    private Set<Method> fillers = new HashSet<>();
    private Set<SortsClassHolder> sorters = new HashSet<>();

    public Analyzers() {
        Set<Class<?>> sortReflection = new Reflections(PATH).getTypesAnnotatedWith(SorterClass.class);
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
        for (Class<?> c : new Reflections(PATH).getTypesAnnotatedWith(FillersClass.class)) {                for (Method method : c.getMethods()) {
                    if (method.isAnnotationPresent(Fillers.class)) {
                        fillers.add(method);
                    }
                }
            }
    }

    private void test(SortsClassHolder sorter, Method filler, int[] arr) {
        try {
            filler.invoke(null, arr, 0, arr.length);
            sorter.invoke(arr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


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

    private void prepareSorter(SortsClassHolder sorter, Method filler) {        int[] arr = new int[100];
        for (int i = 0; i < 5; i++) {
            test(sorter, filler, arr);
        }
    }
}