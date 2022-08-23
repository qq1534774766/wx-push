package com.lx.wxpush.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.wxpush.constant.ConfigConstant;
import com.lx.wxpush.service.ProverbService;
import com.lx.wxpush.service.SendService;
import com.lx.wxpush.service.TianqiService;
import com.lx.wxpush.utils.DateUtil;
import com.lx.wxpush.utils.HttpUtil;
import com.lx.wxpush.utils.JsonObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 17:35
 * @Description: TODO
 */
@Service
public class SendServiceImpl implements SendService {
    @Autowired
    private TianqiService tianqiService;

    @Autowired
    private ProverbService proverbService;
    @Autowired
    private ConfigConstant configConstant;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String  getAccessToken() {
        //这里直接写死就可以，不用改，用法可以去看api
        String grant_type = "client_credential";
        //封装请求数据
        String params = "grant_type=" + grant_type + "&secret=" + configConstant.getAppSecret() + "&appid=" + configConstant.getAppId();
        //发送GET请求
        String sendGet = HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
        // 解析相应内容（转换成json对象）
        JSONObject jsonObject1 = JSONObject.parseObject(sendGet);
        logger.info("微信token响应结果=" + jsonObject1);
        //拿到accesstoken
        return (String) jsonObject1.get("access_token");
    }

    /**
     * 发送微信消息
     *
     * @return
     */
    @Override
    public String sendWeChatMsg() {
        String accessToken = getAccessToken();
        String[] openIds = configConstant.getOpenid().split(",");
        List<JSONObject> errorList = new ArrayList();
        HashMap<String,Object> resultMap = new HashMap<>();
        for (String opedId : openIds) {

            //今天
            String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
            String week = DateUtil.getWeekOfDate(new Date());
            String day = date + " " + week;
            JSONObject first = JsonObjectUtil.packJsonObject(day,"#EED016");
            resultMap.put("first",first);
            //处理天气
            JSONObject weatherResult = tianqiService.getWeatherByCity();
            //城市
            JSONObject city = JsonObjectUtil.packJsonObject(weatherResult.getString("city"),"#60AEF2");
            resultMap.put("city",city);
            //天气
            JSONObject weather = JsonObjectUtil.packJsonObject(weatherResult.getString("wea"),"#b28d0a");
            resultMap.put("weather",weather);
            //最低气温
            JSONObject minTemperature = JsonObjectUtil.packJsonObject(weatherResult.getString("tem_night") + "°","#0ace3c");
            resultMap.put("minTemperature",minTemperature);
            //最高气温
            JSONObject maxTemperature = JsonObjectUtil.packJsonObject(weatherResult.getString("tem_day") + "°","#dc1010");
            resultMap.put("maxTemperature",maxTemperature);
            //风
            JSONObject wind = JsonObjectUtil.packJsonObject(weatherResult.getString("win")+","+weatherResult.getString("win_speed"),"#6e6e6e");
            resultMap.put("wind",wind);
            //湿度
            JSONObject wet = JsonObjectUtil.packJsonObject(weatherResult.getString("humidity"),"#1f95c5");
            resultMap.put("wet",wet);

            //生日
            resultMap.put("birthDate1",getBirthday(configConstant.getBirthday1(), date));
            resultMap.put("birthDate2",getBirthday(configConstant.getBirthday2(), date));

            //在一起时间
            resultMap.put("togetherDate",togetherDay(date));
            //每日一句,中文
            String noteZh = proverbService.getOneProverbRandom();
            JSONObject note_Zh = JsonObjectUtil.packJsonObject(noteZh,"#879191");
            resultMap.put("note_Zh",note_Zh);
            //每日一句，英文
            JSONObject note_En = JsonObjectUtil.packJsonObject(proverbService.translateToEnglish(noteZh),"#879191");
            resultMap.put("note_En",note_En);
            //封装数据并发送
            sendMessage(accessToken, errorList, resultMap, opedId);
        }
        JSONObject result = new JSONObject();
        result.put("result", "success");
        result.put("errorData", errorList);
        return result.toJSONString();

    }

    private void sendMessage(String accessToken, List<JSONObject> errorList, HashMap<String, Object> resultMap, String opedId) {
        JSONObject templateMsg = new JSONObject(new LinkedHashMap<>());
        templateMsg.put("touser", opedId);
        templateMsg.put("template_id", configConstant.getTemplateId());
        templateMsg.put("data", new JSONObject(resultMap));
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;

        String sendPost = HttpUtil.sendPost(url, templateMsg.toJSONString());
        JSONObject WeChatMsgResult = JSONObject.parseObject(sendPost);
        if (!"0".equals(WeChatMsgResult.getString("errcode"))) {
            JSONObject error = new JSONObject();
            error.put("openid", opedId);
            error.put("errorMessage", WeChatMsgResult.getString("errmsg"));
            errorList.add(error);
        }
    }

    private JSONObject togetherDay(String date) {
        //在一起时间
        String togetherDay = "";
        try {
            togetherDay = "第" + DateUtil.daysBetween(configConstant.getTogetherDate(), date) + "天";
        } catch (ParseException e) {
            logger.error("togetherDate获取失败" + e.getMessage());
        }
        JSONObject togetherDateObj =JsonObjectUtil.packJsonObject(togetherDay,"#FEABB5");
        return togetherDateObj;
    }

    private JSONObject getBirthday(String configConstant, String date) {
        String birthDay = "无法识别";
        try {
            Calendar calendar = Calendar.getInstance();
            String newD = calendar.get(Calendar.YEAR) + "-" + configConstant;
            birthDay = DateUtil.daysBetween(date, newD);
            if (Integer.parseInt(birthDay) < 0) {
                Integer newBirthDay = Integer.parseInt(birthDay) + 365;
                birthDay = newBirthDay + "天";
            } else {
                birthDay = birthDay + "天";
            }
        } catch (ParseException e) {
            logger.error("togetherDate获取失败" + e.getMessage());
        }
        return JsonObjectUtil.packJsonObject(birthDay,"#6EEDE2");
    }
}
