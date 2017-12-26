package com.andersen.spring.dao;

public interface DAO<T> {

    T created(T item);

    T getById(long id);

    T update(T item);

    T createdToMock(T item);

    boolean delete(long id);

}
