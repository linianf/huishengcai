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
public class User extends ABaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * email
     */
    private String email;

    /**
     * 是否可用 注册成功即为可用
     */
    private boolean enable;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public boolean isEnable() {

        return enable;
    }

    public void setEnable(boolean enable) {

        this.enable = enable;
    }

}
