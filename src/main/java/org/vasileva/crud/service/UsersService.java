package org.vasileva.crud.service;


import org.vasileva.crud.entity.Users;


public interface UsersService {

    Users saveUser (Users user);

    Users saveAdmin (Users user);

    Users findByUsername(String username);

    Users findByUsernameAndPassword (String username, String password);
}
