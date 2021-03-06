package analyzer;

import sorters.ArraySorter;
import sorters.Sorter;
import sorters.SorterClass;
import sorters.SortType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class provides easy access to class marked {@link ArraySorter}
 * @author Dima Korenko
 */
public class SortsClassHolder {

    /**
     * Class which implements {@link ArraySorter} and holder are created for it
     */
    private Class<?> c;

    /**
     * Another holder for class marked as {@link SortType#DEFAULT} becomes available when {@link SortsClassHolder}
     * holds class marked as {@link SortType#WITH_PARAM}
     */
    private SortsClassHolder cParam;

    /**
     * Method which use as {@link ArraySorter#sort(int[])}
     */
    private Method method;

    /**
     * @param c is a class which implements {@link ArraySorter} and marked as {@link SortType#DEFAULT}
     */
    SortsClassHolder(Class<?> c){
        this.c = c;
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }


    /**
     * @param c      is a class which implements {@link ArraySorter} and marked as {@link SortType#WITH_PARAM}
     * @param cParam is another holder for class which marked as {@link SortType#DEFAULT}
     */
    SortsClassHolder(Class<?> c, Class<?> cParam){
        this.c = c;
        this.cParam = new SortsClassHolder(cParam);
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    /**
     * Method which invokes {@link ArraySorter#sort(int[])}
     */
    public void invoke(int[] arr){
        try {
            method.invoke(newInstance(c), (Object) arr);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method created for simplify reflection constructor
     *
     * @param c class which instance will be created
     * @return new {@link ArraySorter}
     */
    private ArraySorter newInstance(Class<?> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cParam != null ? (ArraySorter) c.getConstructor(ArraySorter.class).newInstance(cParam.newInstance(cParam.c)) : (ArraySorter) c.getDeclaredConstructor().newInstance();
    }

    /**
     * @return pretty name of {@link ArraySorter} depends from {@link SortType}
     */
    public String getName(){
        String name = c.getAnnotation(SorterClass.class).name();
        return cParam == null ? name : name + "(" + cParam.getName() + ")";
    }
}