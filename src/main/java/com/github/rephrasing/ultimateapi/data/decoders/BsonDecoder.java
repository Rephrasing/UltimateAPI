package com.github.rephrasing.ultimateapi.data.decoders;

import com.github.rephrasing.ultimateapi.data.AbstractDataClass;
import lombok.var;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class BsonDecoder {

    public static Document toDocument(AbstractDataClass data) {
        var hash = data.encode();
        var document = new Document();

        for (Map.Entry<String, Object> entry : hash.entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }
        return document;
    }

    public static <T extends AbstractDataClass> T fromDocument(Document document, T emptyInstance) {
        HashMap<String, Object> hash = new HashMap<>(document);
        return emptyInstance.decode(hash);
    }

}
