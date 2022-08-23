package com.lx.wxpush;

import com.lx.wxpush.constant.ConfigConstant;
import com.lx.wxpush.service.ProverbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxPushApplicationTests {
    @Autowired
    private ProverbService proverbService;
    @Autowired
    private ConfigConstant configConstant;

    @Test
    void contextLoads() {
//        String result = proverbService.getOneNormalProverb();
//        System.out.println(result);
        configConstant.getOpenidList();
    }

}
