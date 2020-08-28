package co.edu.escuelaing.sparkd.microspring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MicroSpring {
    private Map<String,Method> beans= new HashMap<>();

    public void starts(String[] args) throws Exception {
        int mapped=0;
        for (Method m : Class.forName(args[0]).getMethods()) {
            System.out.println("revisando: " + m.getName());
            if (m.isAnnotationPresent(RequestMapping.class)) {
                System.out.println("si tiene @RequestMapping");
                try {
                    //m.invoke(null);
                    //beans.put(HelloController.class.getAnnotation(RequestMapping.class).value(),m);
                    //m.getAnnotation(RequestMapping.class).value();
                    beans.put(m.getAnnotation(RequestMapping.class).value(),m);
                    mapped++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                }}}

    }

    public  String invoke(String path) throws InvocationTargetException, IllegalAccessException {
        return beans.get(path).invoke(null).toString();
    }
}
