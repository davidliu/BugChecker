package com.evie.bugchecker;

import com.evie.bugchecker.annotations.PropKey;
import com.evie.example.EventProp;
import com.google.errorprone.CompilationTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link CheckHasAnnotation}.
 */
@RunWith(JUnit4.class)
public class CheckHasAnnotationTest {

    private CompilationTestHelper compilationHelper;

    @Before
    public void setup() {
        compilationHelper = CompilationTestHelper.newInstance(CheckHasAnnotation.class, getClass());
    }

    @Test
    public void checkHasAnnotationPositiveCases() {
        compilationHelper.addSourceFile("CheckHasAnnotationPositiveCases.java")
                .withClasspath(EventProp.class, PropKey.class)
                .doTest();
    }

    @Test
    public void checkHasAnnotationNegativeCases() {
        //compilationHelper.addSourceFile("CheckHasAnnotationNegativeCases.java").doTest();
    }
}