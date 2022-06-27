package com.github.rephrasing.ultimateapi.data;


import org.bson.Document;
import org.bson.types.ObjectId;


public abstract class AbstractDataClass {

    private final ObjectId id = new ObjectId();

    public ObjectId getObjectId() {
        return id;
    }

    public abstract Document toDocument();;
}
