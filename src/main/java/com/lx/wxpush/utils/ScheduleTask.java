package com.lx.wxpush.utils;


/**
 * 定时任务线程类
 *
 * @author zhongxiaojian
 * @date 2020/4/17
 **/
public class ScheduleTask implements Runnable {

    private static final int TIMEOUT = 30000;

    private String id;
    private String keyword;

    public String getId() {
        return id;
    }

    /**
     * @param id      任务ID
     * @param service 业务类
     * @param keyword 关键字参数
     */
    public ScheduleTask(String id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    @Override
    public void run() {

    }
}
