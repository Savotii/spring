package com.andersen.spring.dao;

import com.andersen.spring.entity.User;

import java.util.List;

public interface UserDAO extends DAO {

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

}
