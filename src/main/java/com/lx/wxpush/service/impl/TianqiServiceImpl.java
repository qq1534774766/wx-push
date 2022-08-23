package com.lx.wxpush.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.wxpush.constant.ConfigConstant;
import com.lx.wxpush.service.TianqiService;
import com.lx.wxpush.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:55
 * @Description: TODO
 */
@Service
public class TianqiServiceImpl implements TianqiService {
    @Autowired
    private ConfigConstant configConstant;
    @Override
    public JSONObject getWeatherByCity() {
        String TemperatureUrl = "https://www.yiketianqi.com/free/day" +
                "?appid=" + configConstant.getWeatherAppId() +
                "&appsecret=" + configConstant.getWeatherAppSecret()+
                "&city=" + configConstant.getCity()+
                "&unescape=1";
        String sendGet = HttpUtil.sendGet(TemperatureUrl, null);
        return JSONObject.parseObject(sendGet);
    }

    @Override
    public JSONObject getWeatherByIP() {
        String TemperatureUrl = "https://www.yiketianqi.com/free/day?appid=" + configConstant.getWeatherAppId() + "&appsecret=" + configConstant.getWeatherAppSecret() + "&unescape=1";
        String sendGet = HttpUtil.sendGet(TemperatureUrl, null);
        return JSONObject.parseObject(sendGet);
    }
    private String hasTextOrDefault(String s){
        return StringUtils.hasText(s)?s:"无法识别";
    }
}
