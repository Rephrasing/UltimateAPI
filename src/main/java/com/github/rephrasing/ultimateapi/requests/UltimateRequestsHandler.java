package com.github.rephrasing.ultimateapi.requests;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.requests.exceptions.UltimateRequestException;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Optional;

public abstract class UltimateRequestsHandler<Key extends UltimateRequestKeyType<?>, Value> {

    private final HashMap<Key, Value> ultimateRequestList = new HashMap<>();

    public void request(Key key, Value value) throws UltimateRequestException {
        if (ultimateRequestList.containsKey(key)) throw new UltimateRequestException("Tried to create an Ultimate Request but Request already exists.");
        ultimateRequestList.put(key, value);
    }

    public void requestLimited(Key key, Value value, int removeAfterInSeconds) throws UltimateRequestException {
        request(key, value);
        new BukkitRunnable() {

            @Override
            public void run() {
                removeRequest(key);
            }
        }.runTaskLater(UltimateAPI.instance.getPlugin(), 20L * removeAfterInSeconds);
    }

    public void removeRequest(Key key) {
        ultimateRequestList.remove(key);
    }

    public Optional<Value> getRequestByKey(Key key) {
        return ultimateRequestList.containsKey(key) ? Optional.of(ultimateRequestList.get(key)) : Optional.empty();
    }

}
