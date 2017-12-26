package com.andersen.spring.dao;

import com.andersen.spring.jdbc.MySqlHelper;
import com.andersen.spring.storage.MarketStorage;

import java.sql.Connection;

public abstract class AbstractDAO {

    private static long id;
    protected MarketStorage marketStorage;

    protected Connection connection;

    {
        id = 0;
    }

    public AbstractDAO(){
        this.connection = MySqlHelper.createConnection();
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
