package org.vasileva.crud.rest;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vasileva.crud.mapper.UsersMapper;
import org.vasileva.crud.service.UsersService;

@Api(value = "CRUD Application", tags = {"Users RestController"})
@RestController
@RequestMapping("/users/")
public class UsersRestController {

    UsersService usersService;
    UsersMapper usersMapper;

    @Autowired
    public UsersRestController(UsersService usersService, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }
}
