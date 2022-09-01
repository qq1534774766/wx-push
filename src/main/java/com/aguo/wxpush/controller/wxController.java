package com.aguo.wxpush.controller;


import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.SendService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
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
        try {
            return sendService.sendWeChatMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("msg", "信息推送失败");
        return json.toJSONString();
    }



    @RequestMapping("/changeConfig")
    public void changeConfig(String city,String time, HttpServletResponse response) throws IOException {
        returnCity(city);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/text");
        response.getWriter().write("<h1>更新成功!<h1/>");
        sendService.sendWeChatMsg();
    }
    @RequestMapping("/receiveMsg")
    public String receiveMsg(@RequestParam(required = false) String echostr,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if(!StringUtils.hasText(echostr)){
            //正常处理
            String s = sendService.messageHandle(request, response);
            returnCity(s);
            sendService.sendWeChatMsg();
        }
        //只是验证接口
        return echostr;
    }
    private String returnCity(String city){
        if (city.contains("省")||city.contains("市")||city.contains("区")||city.contains("县")) {
            configConstant.setCity(city.substring(0,city.length()-1));
        }else {
            configConstant.setCity(city);
        }
        return city;
    }


}
