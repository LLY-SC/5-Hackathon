package com.wx.inclusive.charity.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.inclusive.charity.server.beans.AidApplyRequest;
import com.wx.inclusive.charity.server.beans.DonateRequest;
import com.wx.inclusive.charity.server.beans.NormalResponse;
import com.wx.inclusive.charity.server.beans.ResponseFormat;
import com.wx.inclusive.charity.server.constants.Magic;
import com.wx.inclusive.charity.server.service.InclusiveCharityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
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
    public NormalResponse donate(@RequestBody DonateRequest donateRequest){
        return NormalResponse.success(inclusiveCharityService.donate(donateRequest));

    }
    @PostMapping(path = "/aidApply",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public NormalResponse aidApply(@RequestBody AidApplyRequest aidApplyRequest){
        return NormalResponse.success(inclusiveCharityService.aidApply(aidApplyRequest));

    }


}
