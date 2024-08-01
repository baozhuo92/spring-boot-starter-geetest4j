package com.kelebb.geetest4j;

import com.kelebb.geetest4j.service.BehaviorVerification3ServiceImpl;
import com.kelebb.geetest4j.tasks.StateDetectionTack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(Geetest2jStarter.class)
@EnableConfigurationProperties(GeetestProperties.class)
public class Geetest2jStarter {

    @Autowired
    private GeetestProperties geetestProperties;

    @Bean
    @ConditionalOnMissingBean(StateDetectionTack.class)
    public void setStateDetectionTack(){
        new StateDetectionTack(geetestProperties).run();
    }

    @Bean
    public BehaviorVerification3ServiceImpl behaviorVerification3Service(){
        BehaviorVerification3ServiceImpl personService = new BehaviorVerification3ServiceImpl();
        personService.setGeetestProperties(geetestProperties);
        return personService;
    }

}
