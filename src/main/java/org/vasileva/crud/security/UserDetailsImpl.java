package org.vasileva.crud.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vasileva.crud.entity.Users;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl fromUsersToCustomUserDetails (Users user) {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.username = user.getUsername();
        userDetailsImpl.password = user.getPassword();
        userDetailsImpl.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return userDetailsImpl;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
