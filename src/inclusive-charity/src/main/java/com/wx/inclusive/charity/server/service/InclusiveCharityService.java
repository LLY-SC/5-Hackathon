package com.wx.inclusive.charity.server.service;

import com.wx.inclusive.charity.server.beans.*;
import com.wx.inclusive.charity.server.beans.data.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by ly on 10/24/18
 */
@Service
public class InclusiveCharityService {

    public DonateResponse donate(TransferRequest transferRequest)
    {
        Boolean result = transfer(transferRequest);
        DonateResponse donateResponse = new DonateResponse();

        donateResponse.setBlockNo(ChainInfo.blockNo+1);
        donateResponse.setTxHash(ChainInfo.getTxHash());
        donateResponse.setTimestamp(System.currentTimeMillis());
        donateResponse.setBalance(transferRequest.getBalance());

        return donateResponse;
    }

    public Boolean transfer(TransferRequest transferRequest){
        //todo verify to get addressFrom
        String addressFrom = "";
        String addressTo = transferRequest.getToAddress();
        BigDecimal balance = transferRequest.getBalance();
        for (AccountData accountData:AccountList.accountDataList)
        {
            if (accountData.getAddress().equals(addressFrom))
            {
                accountData.setBalance(accountData.getBalance().subtract(balance));
            }
            if (accountData.getAddress().equals(addressTo))
            {
                accountData.setBalance(accountData.getBalance().add(balance));
            }
        }
        return true;
    }
    public String aidApply(ApplyRequest applyRequest){
        //todo  get address from signature
        String address = "";
        ApplyData applyData = new ApplyData();
        applyData.setBalance(applyRequest.getBalance());
        applyData.setContext(applyRequest.getContext());
        applyData.setTimestamp(System.currentTimeMillis());
        applyData.setAddress(address);
        AidApplyList.applyDataList.add(applyData);
        return "success";
    }
    public BigDecimal balanceQuery(QueryRequest queryRequest){
        //todo  get address from signature
        String address = "";
        BigDecimal balance = BigDecimal.ZERO;
        for (AccountData accountData:AccountList.accountDataList)
        {
            if (accountData.getAddress().equals(address))
            {
                balance = accountData.getBalance();
            }
        }
        return balance;
    }

    public String withdrawApply(ApplyRequest applyRequest){
        //todo verify use address

        //todo  get address from signature
        String address = "";
        ApplyData applyData = new ApplyData();
        applyData.setBalance(applyRequest.getBalance());
        applyData.setContext(applyRequest.getContext());
        applyData.setTimestamp(System.currentTimeMillis());
        applyData.setAddress(address);
        WithdrawApplyList.applyDataList.add(applyData);
        return "success";
    }
    public String aidCheck(TransferRequest transferRequest){
        // todo 如何删除
        // //AidApplyList.applyDataList.remove(applyData);
        Boolean result = transfer(transferRequest);
        return "success";
    }
}
