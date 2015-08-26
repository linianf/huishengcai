package com.hsh.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hsh.model.User;
import com.hsh.model.UserRole;
import com.hsh.service.UserRoleService;
import com.hsh.service.UserService;

public class MyUserDetailsService implements UserDetailsService, Serializable {

    private static final long serialVersionUID = -2157776263837215188L;

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    public static final String NO_USERNAME = "用户名不存在！";

    public static final String NO_ROLE = "没有权限！";

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        final List <GrantedAuthority> authorities = new ArrayList <GrantedAuthority>();
        User user = null;
        List <UserRole> roleList = null;
        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            logger.error("数据库异常", e);
            return null;
        }

        if (user == null) {
            throw new UsernameNotFoundException(NO_USERNAME);
        }
        try {
            roleList = userRoleService.ListUserRoleByUserId(user.getId());
        } catch (Exception e) {
            logger.error("数据库异常", e);
            return null;
        }
        if (roleList == null || roleList.size() == 0) {
            throw new UsernameNotFoundException(NO_ROLE);
        }

        for (UserRole role : roleList) {
            authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
        }
        return new UserDetail(user.getId() + "", user.getUsername(), user.isEnable(), user.getPassword(), authorities);

    }
}
