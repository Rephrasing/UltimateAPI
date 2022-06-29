package com.github.rephrasing.ultimateapi.mongodb.caching;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.mongodb.data.UltimateBsonClass;

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

    public Data getData(Object keyObject) {
        return cachedData.stream().filter(loopData -> loopData.getImmutableKeyObject().equals(keyObject)).findFirst().orElse(null);
    }

    public void cacheData(Data data) {
        Data found = cachedData.stream().filter(loopData -> loopData.getImmutableKeyObject().equals(data.getImmutableKeyObject())).findFirst().orElse(null);
        if (found == null) throw new IllegalArgumentException("Attempted to cache data but found same object id");
        cachedData.add(data);
    }

    public void cacheOrReplace(Data data) {
        cachedData.stream().filter(loopData -> loopData.getImmutableKeyObject().equals(data.getImmutableKeyObject())).findFirst().ifPresent(cachedData::remove);
        cachedData.add(data);
    }

    public void removeFromCache(Object keyObject) {
        Data found = getData(keyObject);
        if (found == null) throw new IllegalArgumentException("Attempted to remove data from cache but no object with given uuid was found");
        cachedData.remove(found);
    }

    public abstract List<Data> fetch();
    public abstract void push();

}
