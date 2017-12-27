package com.andersen.spring.dao;

import java.util.List;

public interface DAO<T> {

    T create(T item);

    T getById(long id);

    T update(T item);

    boolean delete(long id);

    List<T> getAll();

}
