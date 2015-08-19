package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsh.dao.PostionDao;
import com.hsh.model.Postion;
import com.hsh.service.PostionService;

@Service(value = "postionService")
public class PostionServiceImpl implements PostionService {

    @Autowired
    private PostionDao postionDao;

    @Override
    public Postion getPostionById(long postionId) {

        return postionDao.getPostionById(postionId);
    }

    @Override
    public List <Postion> ListAllPostion(int pageSize, int lastMinId) {

        return postionDao.ListAllPostion(pageSize, lastMinId);
    }

}
