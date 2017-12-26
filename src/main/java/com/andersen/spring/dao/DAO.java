package com.andersen.spring.dao;

import java.util.List;

public interface DAO<T> {

    T created(T item);

    T getById(long id);

    T update(T item);

    T createdToMock(T item);

    boolean delete(long id);

    List<T> getAll();

}
