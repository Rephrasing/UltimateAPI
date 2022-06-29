package com.github.rephrasing.ultimateapi.mongodb.caching;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.mongodb.data.UltimateBsonClass;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public abstract class UltimateBsonCachingHolder<Data extends UltimateBsonClass> {

    private final List<Data> cachedData;

    public UltimateBsonCachingHolder() {
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

    public Data getData(ObjectId id) {
        return cachedData.stream().filter(loopData -> loopData.getObjectId().equals(id)).findFirst().orElse(null);
    }

    public void cacheData(Data data) {
        Data found = cachedData.stream().filter(loopData -> loopData.getObjectId().equals(data.getObjectId())).findFirst().orElse(null);
        if (found == null) throw new IllegalArgumentException("Attempted to cache data but found same object id");
        cachedData.add(data);
    }

    public void cacheOrReplace(Data data) {
        cachedData.stream().filter(loopData -> loopData.getObjectId().equals(data.getObjectId())).findFirst().ifPresent(cachedData::remove);
        cachedData.add(data);
    }

    public void removeFromCache(ObjectId uuid) {
        Data found = getData(uuid);
        if (found == null) throw new IllegalArgumentException("Attempted to remove data from cache but no object with given uuid was found");
        cachedData.remove(found);
    }

    public abstract List<Data> fetch();
    public abstract void push();

}
