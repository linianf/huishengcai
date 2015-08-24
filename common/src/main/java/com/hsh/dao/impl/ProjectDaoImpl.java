package com.hsh.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.ProjectDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Project;

@Repository(value = "projectDao")
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long addProject(Project project) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().save(project);
            return project.getId();
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void updateProject(Project project) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().update(project);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List <Project> queryProject(Map <String, Object> params) throws DataValidateException {

        try {
            StringBuffer hql = new StringBuffer("from Project where 1=1");
            if (params.get("state") != null) {
                hql.append("and state =:state ");
            }
            if (params.get("minId") != null) {
                hql.append("and id <:minId");
            }
            hql.append("order by id desc");

            Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
            if (params.get("pageSize") != null) {
                query.setMaxResults(Integer.parseInt(params.get("pageSize").toString()));
            }

            if (params.get("state") != null) {
                query.setInteger("state", Integer.parseInt(params.get("state").toString()));
            }
            if (params.get("minId") != null) {
                query.setLong("minId", Long.parseLong(params.get("minId").toString()));
            }
            return query.list();
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Project getProjectById(long id) throws DataValidateException {

        List <Project> list;
        try {
            StringBuffer hql = new StringBuffer("from Project where id=:id");
            list = sessionFactory.getCurrentSession().createQuery(hql.toString()).setLong("id", id).list();
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
        if (list == null || list.size() == 0) {
            throw new DataValidateException("项目不存在，请重新选择");
        }
        return list.get(0);
    }
}
