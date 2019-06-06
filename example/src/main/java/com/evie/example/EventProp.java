package com.evie.example;

import com.evie.bugchecker.annotations.PropKey;

import java.util.HashMap;
import java.util.Map;

@PropKey
public class EventProp {

    @PropKey private static final String VALID_KEY = "asdf";

    Map<@PropKey String, Object> map = new HashMap<>();

    public void addSomeValues() {
        map.put("invalid_key", new Object()); // should error
        map.put(VALID_KEY, new Object()); // should pas
    }

    public void put(@PropKey String key, Object value) {
        map.put(key, value);
    }

    public void usingCustomPut() {
        put("invalid_key", new Object()); // should error
        put(VALID_KEY, new Object()); // should pass
    }
}
