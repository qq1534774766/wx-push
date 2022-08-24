package com.aguo.wxpush;

import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.ProverbService;
import com.aguo.wxpush.service.TianqiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
class WxPushApplicationTests {
    @Autowired
    private ProverbService proverbService;
    @Autowired
    private ConfigConstant configConstant;

    @Autowired
    private TianqiService tianqiService;

    @Test
    void contextLoads() {
//        String result = proverbService.getOneNormalProverb();
//        System.out.println(result);
//        configConstant.getOpenidList();
        Map<String, String> map = tianqiService.getTheNextThreeDaysWeather();
        LocalDate now = LocalDate.now();
        System.out.println(now.plusDays(1).getDayOfMonth());
        System.out.println(now.plusDays(1).getDayOfMonth());
        System.out.println(now.plusDays(1).getDayOfMonth());
//        Map<String, String> map2 = new HashMap<>();
//        for (int i = 0; i < 3; i++) {
//        }
        System.out.println();

    }


}
