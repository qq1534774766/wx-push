package com.lx.wxpush.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.wxpush.constant.ConfigConstant;
import com.lx.wxpush.service.ProverbService;
import com.lx.wxpush.service.SendService;
import com.lx.wxpush.service.TianqiService;
import com.lx.wxpush.utils.DateUtil;
import com.lx.wxpush.utils.HttpUtil;
import com.lx.wxpush.utils.JsonObjectUtil;
import jdk.nashorn.internal.scripts.JO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/wx")
public class wxController {

    @Autowired
    private ConfigConstant configConstant;

    @Autowired
    private SendService sendService;



    /**
     * 获取Token
     * 每天早上7：30执行推送
     * @return
     */
    @Scheduled(cron = "0 30 7 ? * *")
    @RequestMapping("/send")
    public String send() {
        return sendService.sendWeChatMsg();
    }



    @RequestMapping("/changeConfig")
    public void changeConfig(String city,String time, HttpServletResponse response) throws IOException {
        if (city.contains("省")||city.contains("市")||city.contains("区")||city.contains("县")) {
            configConstant.setCity(city.substring(0,city.length()-1));
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/text");
        response.getWriter().write("更新成功");
    }




}
