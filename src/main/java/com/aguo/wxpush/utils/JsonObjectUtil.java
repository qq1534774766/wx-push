package com.aguo.wxpush.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 16:40
 * @Description: TODO
 */
public class JsonObjectUtil {
    public static JSONObject packJsonObject(String value,String color){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value",value);
        jsonObject.put("color",color);
        return jsonObject;
    }
}
