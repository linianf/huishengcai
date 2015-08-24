package com.hsh.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

/**
 * 项目详情
 * @author lengxiangwu
 *
 */
@Entity
public class ProjectIntroduction extends ABaseEntity {

    private static final long serialVersionUID = -5708882220243005738L;

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 项目id
    private long projectId;

    // 项目详情
    private String introduction;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getProjectId() {

        return projectId;
    }

    public void setProjectId(long projectId) {

        this.projectId = projectId;
    }

    public String getIntroduction() {

        return introduction;
    }

    public void setIntroduction(String introduction) {

        this.introduction = introduction;
    }

}
