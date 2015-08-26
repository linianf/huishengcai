package com.hsh.service;

import java.util.List;

import com.hsh.model.UserRole;


public interface UserRoleService {

    List <UserRole> ListUserRoleByUserId(long id);

}
