package com.wx.inclusive.charity.server.controller;

import com.wx.inclusive.charity.server.beans.ApplyRequest;
import com.wx.inclusive.charity.server.beans.TransferRequest;
import com.wx.inclusive.charity.server.beans.NormalResponse;
import com.wx.inclusive.charity.server.service.InclusiveCharityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 10/24/18
 */

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class InclusiveCharityController {
    private static final Map<String,String> jsonMapCache = new HashMap<>();

    @Autowired
    private InclusiveCharityService inclusiveCharityService;

    @PostMapping(path = "/donate",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public NormalResponse donate(@RequestBody TransferRequest transferRequest){
        return NormalResponse.success(inclusiveCharityService.donate(transferRequest));

    }
    @PostMapping(path = "/aidApply",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public NormalResponse aidApply(@RequestBody ApplyRequest applyRequest){
        return NormalResponse.success(inclusiveCharityService.aidApply(applyRequest));

    }


}
