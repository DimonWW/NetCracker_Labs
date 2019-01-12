package analyzer;

import sorters.ArraySorter;
import sorters.Sorter;
import sorters.SorterClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SortsClassHolder {
    private Class<?> c;
    private SortsClassHolder cParam;
    private Method method;

    SortsClassHolder(Class<?> c){
        this.c = c;
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    SortsClassHolder(Class<?> c, Class<?> cParam){
        this.c = c;
        this.cParam = new SortsClassHolder(cParam);
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    public void invoke(int[] arr){
        try {
            method.invoke(newInstance(c), (Object) arr);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private ArraySorter newInstance(Class<?> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cParam != null) {
            Constructor<?> constructor = c.getConstructor(ArraySorter.class);
            return  (ArraySorter) constructor.newInstance(cParam.newInstance(cParam.c));
        }
        Constructor<?> constructor = c.getDeclaredConstructor();
        return  (ArraySorter) constructor.newInstance();
    }

    public String getName(){
        String name = c.getAnnotation(SorterClass.class).name();
        return cParam == null ? name : name + "(" + cParam.getName() + ")";
    }
}