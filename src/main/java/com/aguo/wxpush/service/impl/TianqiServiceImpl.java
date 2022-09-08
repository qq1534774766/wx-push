package com.aguo.wxpush.service.impl;

import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.TianqiService;
import com.aguo.wxpush.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:55
 * @Description: TODO
 */
@Service
public class TianqiServiceImpl implements TianqiService {
    @Autowired
    private ConfigConstant configConstant;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public JSONObject getWeatherByCity() {
        String result = null;
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("www.yiketianqi.com")
                    .addPathSegments("free/day")
                    .addQueryParameter("appid", configConstant.getWeatherAppId())
                    .addQueryParameter("appsecret", configConstant.getWeatherAppSecret())
                    .addQueryParameter("city", configConstant.getCity())
                    .addQueryParameter("unescape", "1")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response re = client.newCall(request).execute();
            result = re.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }
    @Override
    public Map<String, String> getTheNextThreeDaysWeather() {
        Map<String, String> map =  null;
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("yiketianqi.com")
                    .addPathSegments("free/week")
                    .addQueryParameter("appid", configConstant.getWeatherAppId())
                    .addQueryParameter("appsecret", configConstant.getWeatherAppSecret())
                    .addQueryParameter("city", configConstant.getCity())
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String responseResult = response.body().string();
            if (!StringUtils.hasText(responseResult)) {
                logger.error("获取三天天气失败,检查配置文件");
                throw new RuntimeException("获取三天天气失败,检查配置文件");
            }
            logger.info(responseResult);
            ZoneId zoneId = ZoneId.of("Asia/Shanghai");
            LocalDate now = LocalDate.now(zoneId);
            //封装今天，明天，后天的时间
            /**
             * 原因分析：从天气api获取到未来的天气的日期是 01  02  03 的两位数。
             * 而Java中的LocalDate的日期，是一位数的 1  2  3 。
             * 因为一开始用是String类型比较，所以01≠1，最后导致异常。
             */
            Map<Integer, String> daySet = new HashMap<>();
            daySet.put(now.getDayOfMonth(), "今");
            daySet.put(now.plusDays(1L).getDayOfMonth(), "明");
            daySet.put(now.plusDays(2L).getDayOfMonth(), "后");
            //过滤，提取结果
            JSONObject jsonObject = JSONObject.parseObject(responseResult);
            if (jsonObject.containsKey("errmsg")) {
                logger.error(jsonObject.getString("errmsg"));
                throw new IllegalArgumentException(jsonObject.getString("errmsg"));
            }
            map = jsonObject.getJSONArray("data").stream()
                    .peek(o -> {
                        String date = getStringFromJson(o, "date").substring(8);
                        ((JSONObject) o).put("date", date);
                    })
                    .filter(o -> daySet.containsKey(getIntegerFromJson(o, "date")))
                    .collect(Collectors.toMap(
                            key -> daySet.get(getIntegerFromJson(key, "date")),
                            value -> getStringFromJson(value, "wea")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取失败");
        }
        return map;
    }

    @Override
    public JSONObject getWeatherByIP() {
        String TemperatureUrl = "https://www.yiketianqi.com/free/day?appid=" + configConstant.getWeatherAppId() + "&appsecret=" + configConstant.getWeatherAppSecret() + "&unescape=1";
        String sendGet = HttpUtil.sendGet(TemperatureUrl, null);
        return JSONObject.parseObject(sendGet);
    }

    private String hasTextOrDefault(String s) {
        return StringUtils.hasText(s) ? s : "无法识别";
    }

    private String getStringFromJson(Object obj, String key) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).getString(key);
        }
        return null;
    }

    private Integer getIntegerFromJson(Object obj, String key) {
        if (obj instanceof JSONObject) {
            return Integer.valueOf(((JSONObject) obj).getString(key));
        }
        return null;
    }
}
