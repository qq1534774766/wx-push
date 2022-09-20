package com.aguo.wxpush;

import com.aguo.wxpush.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/9/1 21:36
 * @Description: TODO
 */
//@Component
public class Task {
    private static Timer TASK = new Timer();
    @Autowired
    private SendService sendService;

    {
        //每天自动推送的时间
//        runTask(8, 30);
    }

    public static void main(String[] args) {
    }

    public void runTask(int hour, int minute) {
        //年月日
        LocalDate now = LocalDate.now();
        //时间点
        LocalTime localTime = LocalTime.of(hour, minute);
        //年月日 时间点
        LocalDateTime localDateTime = LocalDateTime.of(now, localTime);
        TASK.schedule(getTimerTask(), Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()),
                TimeUnit.DAYS.toMillis(1));
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    sendService.sendWeChatMsg();
                } catch (NullPointerException e) {
                }
            }
        };
    }

    public synchronized void changeTask(int hour, int minute) {
        try {
            TASK.cancel();
            TASK = new Timer();
        } catch (Exception e) {

        }
        runTask(hour, minute);
    }
}
