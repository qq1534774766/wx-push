package com.aguo.wxpush.controller;


import com.aguo.wxpush.Task;
import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.SendService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@RestController
public class wxController {

    @Autowired
    private ConfigConstant configConstant;

    @Autowired
    private SendService sendService;


    @Autowired
    private Task task;

    /**
     * 获取Token
     * 每天早上7：30执行推送
     *
     * @return
     */
//    @Scheduled(cron = "0 30 7 ? * *")
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
    public void changeConfig(String city,
                             String hour, String minute,
                             HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        returnCity(city);
        writer.write("<h1>更新城市成功!<h1/>");
        if (StringUtils.hasText(hour) && StringUtils.hasText(minute)) {
            task.changeTask(Integer.parseInt(hour), Integer.parseInt(minute));
            writer.write("<h1>更新时间成功!<h1/>");
        }
        response.setContentType("text/html");
    }
    @RequestMapping("/receiveMsg")
    public String receiveMsg(@RequestParam(required = false) String echostr,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if(!StringUtils.hasText(echostr)) {
            //正常处理
            String s = sendService.messageHandle(request, response);
            if (s != null) {
                if (Pattern.matches("^\\d+[:|：|.|。]\\d+$", s)) {
                    //输入的是时间
                    String[] time = s.split("[:|：|.|。]");
                    task.changeTask(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                } else {
                    //输入的是城市
                    returnCity(s);
                    sendService.sendWeChatMsg();
                }
            }
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
