package com.kelebb.geetest4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jay.Bao
 * description
 * date 2024/7/29
 */
@Data
@SuppressWarnings("ConfigurationProperties")
@ConfigurationProperties(prefix = "geetest")
public class GeetestProperties {
    String id;
    String key;
    Integer check_status_interval;
}
