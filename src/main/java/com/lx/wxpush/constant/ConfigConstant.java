package com.lx.wxpush.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:51
 * @Description: TODO
 */
@Component
public class ConfigConstant {
    @Value("${wx.config.appId}")
    public  String appId;
    @Value("${wx.config.appSecret}")
    public  String appSecret;
    @Value("${wx.config.templateId}")
    public  String templateId;
    @Value("${wx.config.openid}")
    public String openid;
    @Value("${weather.config.appid}")
    public  String weatherAppId;
    @Value("${weather.config.appSecret}")
    public  String weatherAppSecret;
    
    @Value("${weather.config.city}")
    public  String city;
    @Value("${message.config.togetherDate}")
    public  String togetherDate;
    @Value("${message.config.birthday1}")
    public  String birthday1;
    @Value("${message.config.birthday2}")
    public  String birthday2;
    @Value("${message.config.message}")
    public  String message;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getWeatherAppId() {
        return weatherAppId;
    }

    public void setWeatherAppId(String weatherAppId) {
        this.weatherAppId = weatherAppId;
    }

    public String getWeatherAppSecret() {
        return weatherAppSecret;
    }

    public void setWeatherAppSecret(String weatherAppSecret) {
        this.weatherAppSecret = weatherAppSecret;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTogetherDate() {
        return togetherDate;
    }

    public void setTogetherDate(String togetherDate) {
        this.togetherDate = togetherDate;
    }

    public String getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(String birthday1) {
        this.birthday1 = birthday1;
    }

    public String getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(String birthday2) {
        this.birthday2 = birthday2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
