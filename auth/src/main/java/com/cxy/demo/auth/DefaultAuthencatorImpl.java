package com.cxy.demo.auth;

import com.cxy.demo.auth.domain.ApiRequest;
import com.cxy.demo.auth.ticket.AuthTicket;
import com.cxy.demo.auth.domain.CredentialStorage;
import com.cxy.demo.auth.domain.impl.MySqlCredentialStorageImpl;

/**
 * 鉴权接口
 */
public class DefaultAuthencatorImpl implements Authencator {

    private CredentialStorage credentialStorage;

    public DefaultAuthencatorImpl() {
         this.credentialStorage = new MySqlCredentialStorageImpl();
    }

    public DefaultAuthencatorImpl(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String ticket = apiRequest.getTicket();
        long timestamp = apiRequest.getTimestamp();
        String baseUrl = apiRequest.getBaseUrl();
        AuthTicket clientAuthTicket = new AuthTicket(ticket, timestamp);
        if (clientAuthTicket.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }
        String password = credentialStorage.getPassWordByAppId(appId);
        AuthTicket serverAuthTicket = AuthTicket.createTicket(baseUrl, timestamp,appId, password);
        if (!serverAuthTicket.match(clientAuthTicket)) {
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
