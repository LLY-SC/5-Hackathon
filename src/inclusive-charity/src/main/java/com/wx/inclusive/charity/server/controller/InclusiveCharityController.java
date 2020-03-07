package com.wx.inclusive.charity.server.controller;

import com.alibaba.fastjson.JSONObject;
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

    @PostConstruct
    public void init(){
        List<String> all = inclusiveCharityService.queryAll();
        all.stream()
                .filter(String -> !StringUtils.isEmpty(String))
                .forEach(a ->jsonMapCache.put(a,a));
    }

    @Autowired
    private InclusiveCharityService inclusiveCharityService;

    @PostMapping(path = "/setApi",produces = "application/json; charset=UTF-8")
    public ResponseFormat postApi(HttpServletRequest request, @PathVariable(value = "appId") String appId){
        ResponseFormat responseFormat = ResponseFormat.basic();
        byte[] dataOrigin = new byte[request.getContentLength()];

        return responseFormat;
    }

    @GetMapping(path = "/getApi/{appId}",produces = "application/json; charset=UTF-8" )
    public String getApi(@PathVariable("appId") String appId, String bb,@RequestHeader  String b
    , String aa){
        String String = inclusiveCharityService.getByAppId(appId);
        if(String !=null){
            return inclusiveCharityService.getByAppId(appId);
        }else {
            return null;
        }
    }


}
