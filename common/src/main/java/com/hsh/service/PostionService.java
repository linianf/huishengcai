package com.hsh.service;

import java.util.List;

import com.hsh.model.Postion;

public interface PostionService {

    /**
     * 根据位置id查询位置信息
     * 
     * @param postionId
     * @return
     */
    Postion getPostionById(long postionId);

    /**
     * 分页查询所有位置信息
     * 
     * @return
     */
    List <Postion> ListAllPostion(int pageSize, int lastMinId);
}
