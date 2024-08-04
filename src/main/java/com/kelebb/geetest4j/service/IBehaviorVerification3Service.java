package com.kelebb.geetest4j.service;

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
public interface IBehaviorVerification3Service {

    Boolean ServiceStatusDetection();

    RegisterResult register(RegisterParam registerParam);

    ValidateResult validate(ValidateParam validateParam);
}
