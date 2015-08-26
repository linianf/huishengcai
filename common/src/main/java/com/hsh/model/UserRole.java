package com.hsh.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

/**
 * 
 * @author lengxiangwu
 * 
 */
@Entity
public class UserRole extends ABaseEntity {

    private static final long serialVersionUID = 2214918358746333668L;

    /**
     * 后台权限
     */
    public static final String ROLE_BA = "ROLE_BA";

    /**
     * 普通用户
     */
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;

    public UserRole() {

    }

    public UserRole(String roleName, String userId) {

        super();
        this.roleName = roleName;
        this.userId = userId;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    private String userId;

    public String getRoleName() {

        return roleName;
    }

    public void setRoleName(String roleName) {

        this.roleName = roleName;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

}
