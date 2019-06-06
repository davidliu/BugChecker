package com.evie.bugchecker.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        /** Class, interface (including annotation type), or enum declaration */
        TYPE,

        /** Field declaration (includes enum constants) */
        FIELD,

        /** Formal parameter declaration */
        PARAMETER,

        /**
         * Type parameter declaration
         *
         * @since 1.8
         */
        TYPE_PARAMETER,

        /**
         * Use of a type
         *
         * @since 1.8
         */
        TYPE_USE})
public @interface PropKey {
    Class<?> tye() default Void.class;
}
