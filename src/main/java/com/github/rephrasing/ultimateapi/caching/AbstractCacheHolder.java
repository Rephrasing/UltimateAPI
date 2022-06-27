package com.github.rephrasing.ultimateapi.caching;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.data.AbstractDataClass;
import com.github.rephrasing.ultimateapi.data.decoders.BsonDecoder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import lombok.var;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCacheHolder<Data extends AbstractDataClass> {

    private final List<Data> cachedData;
    private final MongoCollection<Document> mongoCollection;
    private final Data emptyNewInstance;

    public AbstractCacheHolder(MongoCollection<Document> mongoCollection, Data emptyNewInstance) {
        this.mongoCollection = mongoCollection;
        this.emptyNewInstance = emptyNewInstance;
        if (fetch() == null) {
            UltimateAPI.getInstance().getPlugin().getLogger().severe("Fetching from database returned null!");
            this.cachedData = new ArrayList<>();
            return;
        }
        this.cachedData = fetch();
    }

    public List<Data> getCachedData() {
        return cachedData;
    }

    public Data getData(UUID uuid) {
        return cachedData.stream().filter(loopData -> loopData.getUniqueId().equals(uuid)).findFirst().orElse(null);
    }

    public void cacheData(Data data) {
        Data found = cachedData.stream().filter(loopData -> loopData.getUniqueId().equals(data.getUniqueId())).findFirst().orElse(null);
        if (found != null) throw new IllegalArgumentException("Attempted to add data to cache but an object with the same UUID already exists");
        cachedData.add(data);
    }
    public void removeFromCache(UUID uuid) {
        Data found = getData(uuid);
        if (found == null) throw new IllegalArgumentException("Attempted to remove data from cache but no object with given uuid was found");
        cachedData.remove(found);
    }

    public List<Data> fetch() {
        List<Data> fetched = new ArrayList<>();

        for (Document doc : mongoCollection.find()) {
            fetched.add(BsonDecoder.fromDocument(doc, emptyNewInstance));
        }
        return fetched;
    }

    public void push() {
        for (Data data : getCachedData()) {
            Document dataDoc = BsonDecoder.toDocument(data);
            var replacedDoc = mongoCollection.findOneAndReplace(Filters.eq("uuid", data.getUniqueId()), dataDoc);
            if (replacedDoc == null) {
                mongoCollection.insertOne(dataDoc);
            }
        }
    }

}
