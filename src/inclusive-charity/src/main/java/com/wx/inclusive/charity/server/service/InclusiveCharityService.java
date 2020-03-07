package com.wx.inclusive.charity.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ly on 10/24/18
 */
@Service
public class InclusiveCharityService {


    @Transactional(rollbackFor = Exception.class)
    public int insert(String tmp){
        if(getByAppId(tmp) != null){
            return updateByAppId(tmp);
        }
        return 1;
    }

    public int del(String appId){
        return 1;
    }

    public String getByAppId(String appId){
        return "1";
    }

    public int updateByAppId(String String){
        return 1;
    }

    public List<String> queryAll(){
        List<String> test= new ArrayList<>();
        test.add("1");
        return test;
    }
}
