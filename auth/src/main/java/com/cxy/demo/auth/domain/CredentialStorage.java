package com.cxy.demo.auth.domain;

/**
 * 从存储层根据appid获得密码(加密后的)
 */
public interface CredentialStorage {

    String getPassWordByAppId(String appId);
}
