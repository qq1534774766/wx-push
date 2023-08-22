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


    /**
     * 这个方法是用于浏览器打开，http://localhost:8081/，修改城市时候调用的接口
     *
     * @param city     城市，不允许包含“省”，“市”，”区“等字。广州就写广州，天河就天河，不要加”市“，而错写成广州市
     * @param time     保留字段，暂时无用
     * @param response
     * @throws IOException
     */
    @RequestMapping("/changeConfig")
    public void changeConfig(String city,String time, HttpServletResponse response) throws IOException {
        returnCity(city);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/text");
        response.getWriter().write("<h1>更新成功!<h1/>");
        sendService.sendWeChatMsg();
    }

    /**
     * 这是配置微信公众号能回复。
     * 即给公众号发送城市，即能修改天气所对应的城市，这个需要去公众号后台配置，并且需要域名和服务器，比较麻烦。
     * 教程没给，想折腾的先搞到域名和服务器再说
     * @param echostr
     * @param request
     * @param response
     * @return
     */
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

    /**
     * 城市字段安全处理，因为有些人可能会忘记配置的时候是不能加“市”这个字的，故写下该方法来剔除后面的字。
     * @param city
     * @return
     */
    private String returnCity(String city){
        if (city.contains("省")||city.contains("市")||city.contains("区")||city.contains("县")) {
            configConstant.setCity(city.substring(0,city.length()-1));
        }else {
            configConstant.setCity(city);
        }
        return city;
    }


}
