## <center>Spring-boot-starter-geetest4j</center>



<p align="center">
	<a target="_blank" href="https://central.sonatype.com/artifact/io.github.baozhuo92/spring-boot-starter-geetest4j">
		<img src="https://img.shields.io/maven-central/v/io.github.baozhuo92/spring-boot-starter-geetest4j" />
	</a>
	<a target="_blank" href="https://opensource.org/licenses/MIT">
		<img src="https://img.shields.io/github/license/baozhuo92/spring-boot-starter-geetest4j" />
	</a>
	<a target="_blank" href='https://github.com/baozhuo92/spring-boot-starter-geetest4j'>
		<img src="https://img.shields.io/github/stars/baozhuo92/spring-boot-starter-geetest4j" alt="github star"/>
	</a>
</p>

## 简介
因曾在项目中用使用极验sdk时，发现极验sdk需要先下载代码放进项目中，使用起来没那么方便，故封装了极验的java sdk，并提供了spring-boot-starter依赖，方便使用。

## 功能
- 官方SDK相同功能
1. 服务器状态检测
2. 验证初始化
3. 二次验证

## 使用
### 1. 引入依赖
```xml
<dependency>
    <groupId>io.github.baozhuo92</groupId>
    <artifactId>spring-boot-starter-geetest4j</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```
### 2. 配置极验参数
```yaml
geetest:
  # 填写您申请的id和key
  id: 4f8d7418d52d11ec941e525400c04324
  key: 6c0d9a7b7a8d11ec941e525400c04324
  # 可选，服务监控任务执行间隔，默认30s
  check_status_interval: 30
``` 
    
### 3. 使用

```java
import com.kelebb.geetest4j.constants.GeetestConstant;
import com.kelebb.geetest4j.result.RegisterResult;
import com.kelebb.geetest4j.result.ValidateResult;

@Autowired
private IBehaviorVerification3Service behaviorVerification3Service;

//验证初始化
public void init() {
    RegisterParam registerParam = new RegisterParam();
    if (GeetestConstant.BYPASS_STATUS) {
        // 1. 验证初始化
        RegisterResult registerResult = behaviorVerification3Service.register(registerParam);
        System.out.println("initResult: " + initResult);
        // 业务逻辑代码
        …………
    }else {
        // 服务器为不可用状态时建议不使用验证码，直接走后续业务逻辑，或者使用其他验证码
    }

}

//二次验证
public void validate() {
    ValidateParam validateParam = new ValidateParam();
    if (GeetestConstant.BYPASS_STATUS) {
        // 2. 二次验证
        ValidateResult verify = behaviorVerification3Service.validate(validateParam);
        System.out.println("verify: " + verify);
        // 业务逻辑代码
        …………
    }else {
        // 服务器为不可用状态时建议不使用验证码，直接走后续业务逻辑，或者使用其他验证码
    }
}
```

## 入参registerParam 与 validateParam 参数说明

`入参与官方文档 `
[官方文档](https://docs.geetest.com/sensebot/apirefer/api/server#register-php-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
`相同，不过精简了几个固定参数`

### registerParam

| 参数名 | 类型 | 说明                   |
| ---- | ---- |----------------------|
| userId | String | 用户id                 |
| clientType | String | 客户端类型，web、h5、native、unknown |
| ipAddress | String | 客户端请求SDK服务器的ip地址               |

------

### validateParam

| 参数名 | 类型 | 说明 |
| ---- | ---- | ---- |
| userId | String | 用户id |
| clientType | String | 客户端类型，web、h5、native、unknown |
| ipAddress | String | 客户端请求SDK服务器的ip地址 |
| challenge | String | 验证初始化返回的challenge |
| validate | String | 验证初始化返回的validate |
| seccode | String | 验证初始化返回的seccode |

## 注意事项
1. 需要使用极验的账号，并开通行为验证服务
2. 


## License

[MIT](https://opensource.org/licenses/MIT)