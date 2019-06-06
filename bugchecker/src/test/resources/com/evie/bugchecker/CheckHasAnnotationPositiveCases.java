package com.evie.bugchecker;

import com.evie.example.EventProp;

public class CheckHasAnnotationPositiveCases {

    private static final String INVALID_KEY = "invalid";
//
//    public void doBadThing() {
//        // BUG: Diagnostic contains: Requires EventKey annotation.
//        put(INVALID_KEY, null);
//    }
//
//    public void put(@PropKey String key, Object value) {
//        // do nothing
//    }
//
//    public void testMap() {
//        Map<@PropKey String, Object> map = new HashMap<>();
//        // BUG: Diagnostic contains: Requires EventKey annotation.
//        map.put(INVALID_KEY, new Object());
//    }

    public void testEventProp() {
        EventProp eventProp = new EventProp();
        // BUG: Diagnostic contains: Requires EventKey annotation.
        eventProp.put(INVALID_KEY, new Object());
    }
}