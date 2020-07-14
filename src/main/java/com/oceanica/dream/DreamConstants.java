package com.oceanica.dream;

import java.util.HashMap;

public class DreamConstants {
    public static final DreamConstants FirstDream = new DreamConstants("first-dream", true);
    public static final DreamConstants FoundBookDream = new DreamConstants("found-book");

    private static final HashMap<String, DreamConstants> dreamConstantsById = new HashMap<String, DreamConstants>();
    static {
        dreamConstantsById.put(FirstDream.id, FirstDream);
        dreamConstantsById.put(FoundBookDream.id, FoundBookDream);
    }

    public static DreamConstants getById(String id) {
        return dreamConstantsById.get(id);
    }

    public final String id;
    public final boolean eligibleByDefault;

    public DreamConstants(String id) {
        this(id, false);
    }

    public DreamConstants(String id, boolean eligibleByDefault) {
        this.id = id;
        this.eligibleByDefault = eligibleByDefault;
    }
}