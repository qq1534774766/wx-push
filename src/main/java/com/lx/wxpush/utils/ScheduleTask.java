package com.lx.wxpush.utils;


import com.lx.wxpush.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务线程类
 *
 * @author zhongxiaojian
 * @date 2020/4/17
 **/
@Component
public class ScheduleTask implements Runnable {

    private static final int TIMEOUT = 30000;
    @Autowired
    private SendService sendService;
    private String id;
    private String keyword;

    public String getId() {
        return id;
    }

    public ScheduleTask() {
    }

    /**
     * @param id      任务ID
     * @param keyword 关键字参数
     */
    public ScheduleTask(String id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        sendService.sendWeChatMsg();
    }
}
