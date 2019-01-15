package sorters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for marking {@link sorters.ArraySorter}
 * @author Dima Korenko
 */
@Target(value=ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface SorterClass {
    String name();
    SortType type() default SortType.DEFAULT;
}
