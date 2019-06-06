package com.evie.bugchecker;

import com.evie.bugchecker.annotations.PropKey;

public class CheckHasAnnotationNegativeCases {

    private static final String INVALID_KEY = "invalid";
    @PropKey
    private static final String VALID_KEY = "valid";

    public void useValidKey() {
        put(VALID_KEY);
    }

    public void useInvalidKey() {
        uncheckedPut(INVALID_KEY);
    }

    public void put(@PropKey String key) {
        // do nothing
    }

    public void uncheckedPut(String key) {
        // do nothing
    }
}