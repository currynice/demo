package com.cxy.demo.auth;

import cn.hutool.core.util.StrUtil;
import com.cxy.demo.auth.activedcode.ActivedCodeStorage;
import com.cxy.demo.auth.activedcode.impl.MySqlActivedCodeStorageImpl;
import com.cxy.demo.auth.exceptions.AuthException;
import com.cxy.demo.auth.request.ApiRequest;
import com.cxy.demo.auth.ticket.AuthTicket;


/**
 * 鉴权接口
 */
public class DefaultAuthencatorImpl implements Authencator {

    private ActivedCodeStorage activedCodeStorage;

    public DefaultAuthencatorImpl() {
         this.activedCodeStorage = new MySqlActivedCodeStorageImpl();
    }

    public DefaultAuthencatorImpl(ActivedCodeStorage activedCodeStorage) {
        this.activedCodeStorage = activedCodeStorage;
    }



    @Override
    public void auth(ApiRequest apiRequest) {
        //用户appId
        String appId = apiRequest.getAppId();
        //授予的t票
        String ticket = apiRequest.getTicket();
        //激活认证请求的时间戳
        long timestamp = apiRequest.getTimestamp();
        String baseUrl = apiRequest.getBaseUrl();
        AuthTicket clientAuthTicket = AuthTicket.wrapTicket(ticket,timestamp);
        if (clientAuthTicket.isExpired()) {
            throw new AuthException("激活码过期，激活失败");
        }
        //存储层获取对应的激活码(null就是激活码过期或者无效)
        String acticedCode = activedCodeStorage.getActiveDCodeByAppId(appId);
        if(StrUtil.isBlank(acticedCode)){
            throw new AuthException("激活码无效,激活失败");
        }
        //根据参数生成
        AuthTicket serverAuthTicket = AuthTicket.createTicket(baseUrl,appId, acticedCode,timestamp);
        if (!serverAuthTicket.match(clientAuthTicket)) {
            throw new AuthException("ticket验证失败，激活失败");
        }
    }
}
