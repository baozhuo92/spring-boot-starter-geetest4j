package com.kelebb.geetest4j.result;

import lombok.Data;

/**
 * @author Jay.Bao
 * description
 * date 2024/7/29
 */
@Data
public class RegisterResult {
    // 流水号，一次完整验证流程的唯一标识
    String challenge;
}
