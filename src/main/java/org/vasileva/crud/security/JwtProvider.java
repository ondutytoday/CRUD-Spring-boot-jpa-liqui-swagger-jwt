package org.vasileva.crud.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;



}
