package com.lx.wxpush.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 14:50
 * @Description: TODO
 */
public interface ProverbService {
    /**
     * 这个接口很奇怪，可能会返回奇奇怪怪的句子
     * @return
     */
    String getOneProverbRandom();

    String translateToEnglish(String sentence);

    /**
     * 得到正常的句子
     * @return
     */
    String getOneNormalProverb();
}
