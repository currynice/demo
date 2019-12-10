package com.cxy.demo.auth;

import com.cxy.demo.auth.request.ApiRequest;

/**
 * 鉴权接口
 */
public interface Authencator {



    void auth(ApiRequest apiRequest);
}
