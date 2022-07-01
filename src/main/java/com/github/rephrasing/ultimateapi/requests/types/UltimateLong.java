package com.github.rephrasing.ultimateapi.requests.types;

import com.github.rephrasing.ultimateapi.requests.UltimateRequestKeyType;

public class UltimateLong implements UltimateRequestKeyType<Long> {

    private final long longKey;

    public UltimateLong(long longKey) {
        this.longKey = longKey;
    }

    @Override
    public Long getJavaType() {
        return longKey;
    }
}
