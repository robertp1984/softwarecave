package com.example.customannotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestRunner {

    public void run(Class<?>... klasses) {
        for (Class<?> testClass : klasses) {
            runTestClass(testClass);
        }
    }
    
    private void runTestClass(Class<?> klass) {
        for (Method method : klass.getMethods()) {
            MyTest annotation = method.getAnnotation(MyTest.class);
            if (annotation != null)
                runTestMethod(klass, method, annotation);
        }
    }

    private void runTestMethod(Class<?> klass, Method method, MyTest annotation) {
        if (annotation.state() != MyTestState.ACTIVE)
            return;
        try {
            System.out.println("Running test: " + getTestName(method, annotation));
            Object testInstance = klass.newInstance();
            method.invoke(testInstance);
            System.out.println("SUCCESS");
        } catch (InstantiationException e) {
            System.err.println("FAILED: Failed to instantiate class " + klass.getName());
        } catch (IllegalAccessException e) {
            System.err.println("FAILED: Failed to call test method " + method.getName());
        } catch (InvocationTargetException e) {
            checkThrowable(annotation, e.getCause());
        }
    }

    private static String getTestName(Method method, MyTest annotation) {
        return !annotation.name().isEmpty() ? annotation.name() : method.getName();
    }
    
    private void checkThrowable(MyTest annotation, Throwable th) {
        if (annotation.expected() == th.getClass())
            System.out.println("SUCCESS");
        else
            System.out.println("FAILED: " + th.getMessage());
    }
}
