package com.aguo.wxpush.service.impl;

import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.ProverbService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 14:50
 * @Description: 处理谚语的逻辑
 */
@Service
public class ProverbServiceImpl implements ProverbService {
    @Autowired
    private ConfigConstant configConstant;

    /**
     * 获取一个随机的谚语
     *
     * @return
     */
    @Override
    public String getOneProverbRandom() {
        String proverb;
        do {
            proverb = null;
            try {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Request request = new Request.Builder()
                        .url("https://api.xygeng.cn/one")
                        .get()
                        .addHeader("Content-Type", "")
                        .build();
                Response response = client.newCall(request).execute();
                //解析
                JSONObject jsonObject = JSONObject.parseObject(response.body().string());
                JSONObject content = jsonObject.getJSONObject("data");
                proverb = content.getString("content");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (proverb.length() > 25);
        return proverb;
    }

    /**
     * 翻译中文为英文
     *
     * @param sentence
     * @return
     */
    @Override
    public String translateToEnglish(String sentence) {
        String result = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url("https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + sentence)
                    .get()
                    .addHeader("Content-Type", "")
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
            //解析
            JSONObject jsonObject = JSONObject.parseObject(result);
            result = jsonObject.getJSONArray("translateResult").getJSONArray(0).getJSONObject(0).getString("tgt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * APISpace的获取谚语的接口
     *
     * @return
     */
    @Override
    public String getOneNormalProverb() {
        String proverb = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "titleID=" + new Random().nextInt(9));
            Request request = new Request.Builder()
                    .url("https://eolink.o.apispace.com/myjj/common/aphorism/getAphorismList")
                    .method("POST", body)
                    .addHeader("X-APISpace-Token", configConstant.getToken())
                    .addHeader("Authorization-Type", "apikey")
                    .addHeader("Content-Type", "")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            //取出全部句子
            JSONArray allProverb = JSONObject.parseArray((String) jsonObject.getJSONArray("result").getJSONObject(0).get("words"));
            // 新增 微信限制只能20个字符，因此新增限制，2023-08-22更新
            List<Object> collect = allProverb.stream().filter(o ->
                    ((String) o).length() <= 20
            ).collect(Collectors.toList());
            //随机取出一条句子
            String s = (String) collect.get(new Random().nextInt(collect.size()));
            //去除无关元素
            proverb = s.replaceAll("^.*、", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return proverb;
    }


}
