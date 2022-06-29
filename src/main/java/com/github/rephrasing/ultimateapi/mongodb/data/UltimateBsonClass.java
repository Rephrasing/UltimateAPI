package com.github.rephrasing.ultimateapi.mongodb.data;


import org.bson.Document;

public abstract class UltimateBsonClass {

    private final Object immutableKeyObject;

    public UltimateBsonClass(Object id) {
        this.immutableKeyObject = id;
    }

    /**
     * This should return the key object that should be immutable in the database, Therefore if an object is pushed to the database with the same KeyObject It either will be replaced or denied to be pushed depends on the method call in {@link com.github.rephrasing.ultimateapi.mongodb.caching.UltimateBsonCachingHolder}
     * @return
     */
    public Object getImmutableKeyObject() {
        return immutableKeyObject;
    }

    public abstract Document toDocument();
}
