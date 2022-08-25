package com.aguo.wxpush;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WxPushApplicationTests {
//    @Autowired
//    private ProverbService proverbService;
//    @Autowired
//    private ConfigConstant configConstant;
//
//    @Autowired
//    private TianqiService tianqiService;

    @Test
    void contextLoads() {
//        String result = proverbService.getOneNormalProverb();
//        System.out.println(result);
//        configConstant.getOpenidList();
//        Map<String, String> map = tianqiService.getTheNextThreeDaysWeather();
//        LocalDate now = LocalDate.now();
//        System.out.println(now.plusDays(1).getDayOfMonth());
//        System.out.println(now.plusDays(1).getDayOfMonth());
//        System.out.println(now.plusDays(1).getDayOfMonth());
//        Map<String, String> map2 = new HashMap<>();
//        for (int i = 0; i < 3; i++) {
//        }
//        System.out.println();

    }

    public static void main(String[] args) {
        test("src/main/resources/a.txt");
    }

    //            try (
//    FileWriter rf = new FileWriter(fileName)) {
//        Random random = new Random();
//        while (true){
//            rf.write(","+random.nextInt(111));
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
    private static void test(String fileName) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get(fileName)))) {
            int readLength = 0;
            byte[] buffer = new byte[1024 * 1024];
            Map<String, Integer> map = new HashMap<>();
            while ((readLength = bis.read(buffer)) != -1) {
                String[] split = ("" + new String(buffer, 0, readLength)).split(",");
                for (String s : split) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                }
                split = null;
            }
            for (String s : map.keySet()) {
                System.out.println(s + "," + map.get(s));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
