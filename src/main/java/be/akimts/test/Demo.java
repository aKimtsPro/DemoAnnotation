package be.akimts.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Demo {

    String alias() default "valeur par defaut";

    // String, primitif, Class ou des Array des truc precedent
    Class<? extends Person>[] value() default {};

    DemoEnum enumeration() default DemoEnum.VALEUR1;


    public static enum DemoEnum {
        VALEUR1,
        VALEUR2,
        VALEUR3;
    }
}
