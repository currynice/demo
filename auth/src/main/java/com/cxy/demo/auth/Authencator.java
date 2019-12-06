package com.cxy.demo.auth;

import com.cxy.demo.auth.domain.ApiRequest;

/**
 * 鉴权接口
 */
public interface Authencator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}
