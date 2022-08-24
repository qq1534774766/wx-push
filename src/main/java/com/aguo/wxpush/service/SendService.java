package com.aguo.wxpush.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 17:35
 * @Description: TODO
 */
public interface SendService {
    String sendWeChatMsg();
    String messageHandle(HttpServletRequest request, HttpServletResponse response);
}
