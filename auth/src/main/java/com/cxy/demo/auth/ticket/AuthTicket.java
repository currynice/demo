package com.cxy.demo.auth.ticket;



/**
 * 1 把 URL、AppID、密码、时间戳拼接为一个字符串；
 * 2 对字符串通过加密算法加密生成 ticket；
 * 6 根据时间戳判断 ticket 是否过期失效；
 * 7 验证两个 ticket 是否匹配。
 */
public class AuthTicket {
    //有效间隔默认1min
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

    public AuthTicket(long expiredTimeInterval, String ticket, long createTime) {
        this.expiredTimeInterval = expiredTimeInterval;
        this.ticket = ticket;
        this.createTime = createTime;
    }

    /**
     *
     * @param baseUrl
     * @param createTime
     * @param appId
     * @param password
     * @return
     */
    public static AuthTicket createTicket(String baseUrl, long createTime,String appId,String password){
        return new AuthTicket();
    }

    /**
     *  生成ticket hash(http://www.cxy.com/search?wd=123&appid=123&pwd=123abc&ts=1261523435)
     * @return
     */
    public String getTicket(){
        return ticket==null?"":ticket;
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

    }


    private

}
