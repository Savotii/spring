package com.andersen.spring.dao;

import com.andersen.spring.storage.MarketStorage;

public abstract class AbstractDAO {

    private static long id;
    private MarketStorage storage;

    {
        id = 0;
    }

    public MarketStorage getStorage() {
        return this.storage;
    }

    public void setStorage(MarketStorage storage) {
        this.storage = storage;
    }

    public long generatedId() {
        id += 1;
        return id;
    }

}
