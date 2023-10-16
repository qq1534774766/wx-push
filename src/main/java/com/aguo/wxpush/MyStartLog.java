package com.aguo.wxpush;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author baldwin
 */
@Component
@Order(1)
public class MyStartLog implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        StringBuilder commandLog = new StringBuilder();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        commandLog.append("+=========================================================================================+\n");
        commandLog.append("+                         WeChat push started successfully!!                              +\n");
        commandLog.append("+    You can open url(http://" + ip + ":" + port + "/send) to send WeChat message!                   +\n");
        commandLog.append("+    Of course,you also can open url(http://" + ip + ":" + port + "/) to change weather of city      +\n");
        commandLog.append("+                                                                                         +\n");
        commandLog.append("+    Author's blog address:https://blog.csdn.net/qq15347747?type=blog                     +\n");
        commandLog.append("+    Current project address:https://gitee.com/aguozi/wx-push                             +\n");
        commandLog.append("+    Tutorial article address:https://gitee.com/aguozi/wx-push                            +\n");
        commandLog.append("+=========================================================================================+\n");
        System.out.println(commandLog.toString());
    }
}

