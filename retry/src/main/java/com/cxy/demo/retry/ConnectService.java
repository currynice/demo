package com.cxy.demo.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

    /**
     * 重试两次，每次延时5000ms
     * @param port
     */
    @Retryable(value = {AuthException.class},maxAttempts = 2,backoff = @Backoff(delay = 5000))
    public void connect(int port){

    }
}
