package com.cxy.demo.auth.ticket;

import com.cxy.demo.auth.util.HashFunctionUtil;


/**
 * 1 把 URL、AppID、激活码、时间戳 拼接为一个字符串；
 * 2 字符串通过sha256不可逆加密算法加密生成 ticket；
 * 6 根据时间戳判断 ticket 是否过期失效；
 * 7 验证两个 ticket 是否匹配。
 */
public class AuthTicket {
    //默认有效间隔时间1min
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000L;

    //ticket有效间隔
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    //ticket
    private String ticket;

    //生成时间
    private long createTime;


    public AuthTicket(String ticket, long createTime) {
        this.ticket = ticket;
        this.createTime = createTime;
    }


    /**
     * 服务器发布根据用户授权请求，发布一个ticket
     * 把 baseUrl、AppID、激活码、时间戳 ，拼接为一个字符串
     * 字符串通过sha256不可逆加密算法加密生成 ticket(http://www.cxy.com/search?wd=123&appid=123&act=123abc&ts=1261523435)
     * @param baseUrl
     * @param appId
     * @param activedCode
     * @param timestamp
     * @return
     */
    public static AuthTicket createTicket(String baseUrl,String appId,String activedCode,long timestamp){
        String info = baseUrl+appId+activedCode+timestamp;
        String ticket = HashFunctionUtil.getSHA256(info);
        return new AuthTicket(ticket,timestamp);
    }


    /**
     * 根据激活请求，包装一个ticket对象
     */
    public static AuthTicket wrapTicket(String ticket ,long timestamp){

        return new AuthTicket(ticket,timestamp);
    }

    /**
     *  获得ticket
     * @return
     */
    public String getTicket(){
        return ticket==null?"":ticket;
    }

    public long getExpiredTimeInterval() {
        return expiredTimeInterval;
    }

    public long getCreateTime() {
        return createTime;
    }

    /**
     * 判断ticket是否过期
     * @return
     */
    public boolean isExpired(){
        //当前时间跟创建时间的差超过时间间隔
        return System.currentTimeMillis()-this.createTime>expiredTimeInterval;
    }

    /**
     * 与另一个ticket是否一样
     * @param anotherTicket
     * @return
     */
    public boolean match(AuthTicket anotherTicket){
        return this.ticket.equals(anotherTicket);
    }




}
