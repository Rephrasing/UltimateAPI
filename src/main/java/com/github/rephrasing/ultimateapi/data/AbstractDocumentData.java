package com.github.rephrasing.ultimateapi.data;

import org.bson.Document;

public abstract class AbstractDocumentData {

    public abstract Document toBson();
    public abstract AbstractDocumentData fromBson(Document document);
}
