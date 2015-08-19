package com.hsh.dao;

import java.util.List;

import com.hsh.model.Postion;

public interface PostionDao {

    Postion getPostionById(long postionId);

    List <Postion> ListAllPostion(int pageSize, int lastMinId);
}
