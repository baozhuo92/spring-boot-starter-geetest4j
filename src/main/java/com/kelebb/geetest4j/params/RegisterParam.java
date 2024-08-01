package com.kelebb.geetest4j.params;

import lombok.Data;

/**
 * @author Jay.Bao
 * description
 * date 2024/8/1
 */
@Data
public class RegisterParam {
    // user_id作为终端用户的唯一标识，确定用户的唯一性；作用于提供进阶数据分析服务，可在api1 或 api2 接口传入，不传入也不影响验证服务的使用；若担心用户信息风险，可作预处理(如哈希处理)再提供到极验
    private String userId;
    // 客户端类型，web（pc浏览器），h5（手机浏览器，包括webview），native（原生app），unknown（未知）
    private String clientType;
    // 客户端请求SDK服务器的ip地址
    private String ipAddress;
}
