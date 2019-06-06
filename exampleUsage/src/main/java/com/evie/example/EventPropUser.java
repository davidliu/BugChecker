package com.evie.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class EventPropUser {
    public static void main(String[] args) throws Exception {

        System.out.println("running");
        EventProp mProp = new EventProp();
        mProp.put("asdf", new Object());
        Method method = EventProp.class.getMethod("put", String.class, Object.class);
        for (Annotation annotation : method.getAnnotations())
            System.out.println("Annotation: " + annotation);
    }
}
