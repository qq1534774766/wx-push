package com.lx.wxpush;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.wxpush.service.ProverbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxPushApplicationTests {
    @Autowired
    private ProverbService proverbService;
    @Test
    void contextLoads() {
        String result = proverbService.getOneNormalProverb();
        System.out.println(result);
    }

}
