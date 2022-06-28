package com.github.rephrasing.ultimateapi.mongodb.data;


import org.bson.Document;
import org.bson.types.ObjectId;


public abstract class UltimateBsonClass {

    private final ObjectId id = new ObjectId();

    public ObjectId getObjectId() {
        return id;
    }

    public abstract Document toDocument();;
}
