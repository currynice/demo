package com.cxy.demo.retry.muretryutils;




import java.io.Serializable;

/**
 * Description: code2Session响应包体  <br>
 * Date: 2020/8/3 10:23  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class Code2SessionResponse implements Serializable , ResultCheck {

    /**
     * openid	string	用户唯一标识
     * session_key	string	会话密钥
     * unionid	string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     * errcode	number	错误码
     * errmsg	string	错误信息
     *
     *
     * errcode 的合法值
     *
     * 值	说明	最低版本
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     */


    private static final long serialVersionUID = 6119065210730530670L;

    //以下为body中内容
    private String openid;                // 用户唯一标识

    private String session_key;           //会话密钥

    private String unionid;               //开放平台标识符，忽略

    private Long errcode;                 // 响应描述码(错误码)

    private String errmsg;               //响应错误信息


    /**
     * code == 1000 且 result != null
     *
     * @return 是否有效/可用
     */
    public boolean isSuccess() {
        return (openid != null && session_key !=null);
    }

    @Override
    public boolean matching() {
        return isSuccess();
    }

    @Override
    public String toString() {
        return "Code2SessionResponse{" +
                "openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
