package com.oceanica.datastructure;

import java.util.HashMap;
import java.util.function.Function;

public class HydratingHashMap<Key, Value> {
    protected final HashMap<Key, Value> internalMap = new HashMap<Key, Value>();
    protected final Function<Key, Value> defaultBuilder;

    public HydratingHashMap(Function<Key, Value> defaultBuilder) {
        this.defaultBuilder = defaultBuilder;
    }

    public Value get(Key key) {
        return internalMap.computeIfAbsent(key, (mapKey) -> this.defaultBuilder.apply(mapKey));
    }
}