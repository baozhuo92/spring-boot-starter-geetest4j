package com.kelebb.geetest4j.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.kelebb.geetest4j.GeetestProperties;
import com.kelebb.geetest4j.constants.GeetestConstant;
import com.kelebb.geetest4j.params.RegisterParam;
import com.kelebb.geetest4j.params.ValidateParam;
import com.kelebb.geetest4j.result.BypassStatusResult;
import com.kelebb.geetest4j.result.RegisterResult;
import com.kelebb.geetest4j.result.ValidateResult;

/**
 * @author Jay.Bao
 * description
 * date 2024/8/1
 */

public class BehaviorVerification3ServiceImpl implements IBehaviorVerification3Service {

    private GeetestProperties geetestProperties;

    public void setGeetestProperties(GeetestProperties geetestProperties){
        this.geetestProperties = geetestProperties;
    }

    public BypassStatusResult ServiceStatusDetection() {
        HttpRequest request = HttpRequest.get(GeetestConstant.BYPASS_STATUS_URL+"?gt="+geetestProperties.getId());
        String result = request.execute().body();
        JSONObject resultJson = new JSONObject(result);
        BypassStatusResult bypassStatusResult = resultJson.toBean(BypassStatusResult.class);
        return bypassStatusResult;
    }

    public RegisterResult register(RegisterParam registerParam) {
        StringBuilder url = new StringBuilder(GeetestConstant.REGISTER_URL);
        url.append("?gt=").append(geetestProperties.getId())
                .append("&user_id=").append(registerParam.getUser_id())
                .append("&client_type=").append(StrUtil.isEmpty(registerParam.getClient_type()) ? "web" : registerParam.getClient_type())
                .append("&ip_address=").append(StrUtil.isEmpty(registerParam.getIp_address())? "127.0.0.1" : registerParam.getIp_address())
                .append("&digestmod=").append("md5")
                .append("&json_format=").append("1")
                .append("&sdk=").append("jave-servlet:3.1.1");
        HttpRequest request = HttpRequest.get(url.toString());
        String result = request.execute().body();
        JSONObject resultJson = new JSONObject(result);
        return resultJson.toBean(RegisterResult.class);
    }

    public ValidateResult validate(ValidateParam validateParam) {

        StringBuilder url = new StringBuilder(GeetestConstant.VALIDATE_URL);
        url
                .append("?user_id=").append(validateParam.getUser_id())
                .append("&client_type=").append(StrUtil.isEmpty(validateParam.getClient_type())? "web" : validateParam.getClient_type())
                .append("&ip_address=").append(StrUtil.isEmpty(validateParam.getIp_address())? "127.0.0.1" : validateParam.getIp_address())
                .append("&seccode=").append(validateParam.getSeccode())
                .append("&challenge=").append(validateParam.getChallenge())
                .append("&captchaid=").append(geetestProperties.getId())
                .append("&json_format=").append("1")
                .append("&sdk=").append("jave-servlet:3.1.1");
        HttpRequest request = HttpRequest.post(url.toString());
        String result = request.execute().body();
        JSONObject resultJson = new JSONObject(result);
        return resultJson.toBean(ValidateResult.class);
    }
}
