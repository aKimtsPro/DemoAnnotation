package be.akimts.test.container;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceContainer {

    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static void loadServices() throws Exception {
        Set<Class<?>> classes = findAllClassesUsingClassLoader("be.akimts.test.container");

        for (Class<?> clazz : classes){
            if( clazz.isAnnotationPresent(ToInstanciate.class) ){
                Constructor<?> ctor = clazz.getConstructors()[0];

                Parameter[] params =  ctor.getParameters();
                if( params.length == 0 ){
                    instances.put(clazz, ctor.newInstance());
                }
                else {
                    boolean paramsOk = Arrays.stream( params )
                            .allMatch(param -> instances.containsKey( param.getType() ));

                    if( paramsOk ){

                        Object[] dependencies = Arrays.stream( params )
                                .map(param -> instances.get(param.getType()))
                                .toArray();

                        instances.put( clazz, ctor.newInstance(dependencies) );
                    }
                }
            }
        }
    }

    public static  <T> T getInstance( Class<T> clazz ){
        return (T) instances.get(clazz);
    }


    private static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

}
