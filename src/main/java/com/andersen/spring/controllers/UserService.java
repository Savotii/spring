package com.andersen.spring.controllers;

import com.andersen.spring.entity.User;

import java.util.List;

public interface UserService {

    User update(User product);

    User create(User product);

    User getById(long id);

    boolean delete(long id);

   // List<User> getAll();

}
