package com.github.rephrasing.ultimateapi.requests.types;

import com.github.rephrasing.ultimateapi.requests.UltimateRequestKeyType;

public class UltimateInteger implements UltimateRequestKeyType<Integer> {

    private final int integer;

    public UltimateInteger(int integer) {
        this.integer = integer;
    }

    @Override
    public Integer getJavaType() {
        return integer;
    }
}
