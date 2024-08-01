package com.kelebb.geetest4j.result;

import lombok.Data;

/**
 * @author Jay.Bao
 * description
 * date 2024/7/29
 */
@Data
public class BypassStatusResult {
    // success: 极验云服务正常  fail: 极验云服务异常
    String status;
}
