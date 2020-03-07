package com.wx.inclusive.charity.server.service;

import com.wx.inclusive.charity.server.beans.DonateRequest;
import com.wx.inclusive.charity.server.beans.DonateResponse;
import com.wx.inclusive.charity.server.beans.data.ChainInfo;
import com.wx.inclusive.charity.server.beans.data.CharityAccount;
import com.wx.inclusive.charity.server.beans.data.DonaterAccount;
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

    public DonateResponse donate(DonateRequest denoteRequest){
        //verify use address

        DonateResponse donateResponse = new DonateResponse();
        if(denoteRequest.getCharity().equals(CharityAccount.Address))
        {
            DonaterAccount.balance = DonaterAccount.balance.subtract(denoteRequest.getBalance());
            CharityAccount.balance = CharityAccount.balance.add(denoteRequest.getBalance());
            donateResponse.setBlockNo(ChainInfo.blockNo+1);
            donateResponse.setTxHash(ChainInfo.getTxHash());
            donateResponse.setTimestamp(System.currentTimeMillis());
            donateResponse.setBalance(DonaterAccount.balance);
        }
        return donateResponse;
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
