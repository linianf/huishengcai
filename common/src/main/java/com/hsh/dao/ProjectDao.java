package com.hsh.dao;

import java.util.List;
import java.util.Map;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Project;

public interface ProjectDao {

    long addProject(Project project) throws DataValidateException;

    void updateProject(Project project) throws DataValidateException;

    List <Project> queryProject(Map <String, Object> params) throws DataValidateException;

    Project getProjectById(long id) throws DataValidateException;

}
