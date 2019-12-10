package com.cxy.demo.auth.activedcode;

/**
 * 从存储层根据appid获得密码(加密后的)
 */
public interface ActivedCodeStorage {

    String getActiveDCodeByAppId(String appId);

    //todo 生成一个会过期的唯一激活码，需要存起来
    String generateActiveDCode();
}
