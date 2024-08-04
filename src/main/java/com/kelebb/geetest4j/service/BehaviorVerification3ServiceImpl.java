package com.kelebb.geetest4j.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.kelebb.geetest4j.GeetestProperties;
import com.kelebb.geetest4j.constants.GeetestConstant;
import com.kelebb.geetest4j.params.RegisterParam;
import com.kelebb.geetest4j.params.ValidateParam;
import com.kelebb.geetest4j.result.BypassStatusResult;
import com.kelebb.geetest4j.result.RegisterResult;
import com.kelebb.geetest4j.result.ValidateResult;
import cn.hutool.core.util.StrUtil;
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

    public Boolean ServiceStatusDetection() {
        String url = GeetestConstant.BYPASS_STATUS_URL+"?gt="+geetestProperties.getId();
        HttpRequest request = HttpRequest.get(url);
        String result = request.execute().body();
        JSONObject resultJson = new JSONObject(result);
        BypassStatusResult bypassStatusResult = resultJson.toBean(BypassStatusResult.class);
        if (bypassStatusResult.getStatus().equals("success")) {
            return Boolean.TRUE;
        } else {
            return  Boolean.FALSE;
        }
    }

    public RegisterResult register(RegisterParam registerParam) {
        String url = GeetestConstant.REGISTER_URL + "?gt=" + geetestProperties.getId() +
                "&user_id=" + registerParam.getUserId() +
                "&client_type=" + (StrUtil.isEmpty(registerParam.getClientType()) ? "web" : registerParam.getClientType()) +
                "&ip_address=" + (StrUtil.isEmpty(registerParam.getIpAddress()) ? "127.0.0.1" : registerParam.getIpAddress()) +
                "&digestmod=" + "md5" +
                "&json_format=" + "1" +
                "&sdk=" + "jave-servlet:3.1.1";
        HttpRequest request = HttpRequest.get(url);
        String result = request.execute().body();
        JSONObject resultJson = new JSONObject(result);
        return resultJson.toBean(RegisterResult.class);
    }

    public ValidateResult validate(ValidateParam validateParam) {
        // 关键参数非空校验
        if (StrUtil.isEmpty(validateParam.getSecCode()) || StrUtil.isEmpty(validateParam.getChallenge()) || StrUtil.isEmpty(validateParam.getValidate())) {
            String url = GeetestConstant.VALIDATE_URL +
                    "?user_id=" + validateParam.getUserId() +
                    "&client_type=" + (StrUtil.isEmpty(validateParam.getClientType()) ? "web" : validateParam.getClientType()) +
                    "&ip_address=" + (StrUtil.isEmpty(validateParam.getIpAddress()) ? "127.0.0.1" : validateParam.getIpAddress()) +
                    "&seccode=" + validateParam.getSecCode() +
                    "&challenge=" + validateParam.getChallenge() +
                    "&captchaid=" + geetestProperties.getId() +
                    "&json_format=" + "1" +
                    "&sdk=" + "jave-servlet:3.1.1";
            HttpRequest request = HttpRequest.post(url);
            String result = request.execute().body();
            JSONObject resultJson = new JSONObject(result);
            return resultJson.toBean(ValidateResult.class);
        }else {
            throw new RuntimeException("参数不能为空");
        }
    }
}
