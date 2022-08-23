package com.lx.wxpush.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:49
 * @Description: TODO
 */
public interface TianqiService {
    JSONObject getWeatherByCity();

    JSONObject getWeatherByIP();
}
