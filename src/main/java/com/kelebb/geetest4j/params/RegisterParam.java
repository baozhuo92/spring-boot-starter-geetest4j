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
    private String user_id;
    // 客户端类型，web（pc浏览器），h5（手机浏览器，包括webview），native（原生app），unknown（未知）
    private String client_type;
    // 客户端请求SDK服务器的ip地址
    private String ip_address;

    // 以下为必填参数

    // 生成唯一标识字符串的签名算法，默认暂支持md5
    private String digestmod;
    // 向极验申请的账号id
    private String gt;
    // json格式化标识
    private String json_format;
    // sdk代码版本号
    private String sdk;
}
