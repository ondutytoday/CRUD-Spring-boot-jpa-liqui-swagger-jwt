package org.vasileva.crud.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vasileva.crud.dto.UsersDto;
import org.vasileva.crud.entity.Users;
import org.vasileva.crud.mapper.UsersMapper;
import org.vasileva.crud.repository.UsersRepository;
import org.vasileva.crud.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(value = "CRUD Application", tags = {"Authorization RestController"})
@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UsersRepository usersRepository, UsersMapper usersMapper, JwtProvider jwtProvider) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    @ApiOperation(value = "Authorization")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UsersDto request) {
        Users user = usersRepository.findByUsername(request.getUsername());
        String token = jwtProvider.generateToken(request.getUsername());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", request.getUsername());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "Logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

}
