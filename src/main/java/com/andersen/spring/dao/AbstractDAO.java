package com.andersen.spring.dao;


import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

public abstract class AbstractDAO {

    private static long id;

    {
        id = 0;
    }

    public AbstractDAO() {
    }

    public long generatedId() {
        id += 1;
        return id;
    }

}
