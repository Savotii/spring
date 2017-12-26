package com.andersen.spring.dao;

import com.andersen.spring.jdbc.MySqlHelper;
import com.andersen.spring.storage.MarketStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

public abstract class AbstractDAO {

    private static long id;

    @Autowired
    protected MarketStorage marketStorage;

    {
        id = 0;
    }

    public AbstractDAO() {
    }

    public MarketStorage getStorage() {
        return this.marketStorage;
    }

    public void setStorage(MarketStorage storage) {
        this.marketStorage = storage;
    }

    public long generatedId() {
        id += 1;
        return id;
    }

}
