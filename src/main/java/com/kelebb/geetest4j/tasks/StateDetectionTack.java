package com.kelebb.geetest4j.tasks;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.kelebb.geetest4j.GeetestProperties;
import com.kelebb.geetest4j.constants.GeetestConstant;
import com.kelebb.geetest4j.result.BypassStatusResult;
import com.kelebb.geetest4j.service.BehaviorVerification3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.Resources;

/**
 * @author Jay.Bao
 * description
 * date 2024/7/29
 */
public class StateDetectionTack {

    private GeetestProperties geetestProperties;

    public StateDetectionTack(GeetestProperties geetestProperties) {
        this.geetestProperties = geetestProperties;
    }

    public void run() {

        if (geetestProperties.getCheck_status_interval() != null) {
            int check_status_interval = geetestProperties.getCheck_status_interval();
            if(check_status_interval >= 0) {
                String cronStr = "*/" + check_status_interval + " * * * * *"; // 每隔check_status_interval秒执行一次
                CronUtil.schedule(cronStr, new Task() {
                    @Override
                    public void execute() {
                        BehaviorVerification3ServiceImpl behaviorVerification3Service = new BehaviorVerification3ServiceImpl();
                        behaviorVerification3Service.setGeetestProperties(geetestProperties);
                        GeetestConstant.BYPASS_STATUS = behaviorVerification3Service.ServiceStatusDetection();
                    }
                });
                CronUtil.setMatchSecond(true);
                CronUtil.start();
            }
        }
    }

}
