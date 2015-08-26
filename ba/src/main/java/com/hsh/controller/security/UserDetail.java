package com.hsh.controller.security;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {

    private static final long serialVersionUID = 775161384030684636L;

    private String id;

    private String username;

    private boolean enabled;

    private String password;

    private Collection <GrantedAuthority> authorities;

    public UserDetail() {

    }

    public UserDetail(String id, String username, boolean enabled, String password, Collection <GrantedAuthority> authorities) {

        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getId() {

        return id;
    }

    @Override
    public Collection <GrantedAuthority> getAuthorities() {

        return authorities;
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

        return enabled;
    }

}
