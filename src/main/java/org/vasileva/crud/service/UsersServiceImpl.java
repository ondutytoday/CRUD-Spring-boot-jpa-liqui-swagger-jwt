package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Roles;
import org.vasileva.crud.entity.Users;
import org.vasileva.crud.repository.RolesRepository;
import org.vasileva.crud.repository.UsersRepository;


@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Users saveUser(Users user) {
        Roles role = rolesRepository.findByRoleName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("IN UsersServiceImpl save {}", user);
        return usersRepository.save(user);
    }

    @Override
    public Users saveAdmin(Users user) {
        Roles role = rolesRepository.findByRoleName("ROLE_ADMIN");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("IN UsersServiceImpl save {}", user);
        return usersRepository.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        log.info("IN UsersServiceImpl findByUsername {}", username);
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        Users user = findByUsername(username);
        if(user!=null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                log.info("IN UsersServiceImpl findByUsernameAndPassword {}. Success", username);
                return user;
            }
        }
        log.info("IN UsersServiceImpl findByUsernameAndPassword {}. User not found", username);
        return null;
    }
}
