package com.github.rephrasing.ultimateapi.data;

import org.bson.Document;

public abstract class AbstractBsonData {

    public abstract Document toBson();
    public abstract AbstractBsonData fromBson(Document document);
}
