package com.cxy.demo.demoredis.util;

/**
 * Redis key 生成
 */
public class RedisKeyUtil {
    private static String SPLIT = "::";
    private static String BIZ_USER = "USER";
    private static String BIZ_GEO_TEST = "GEO_TEST";
    private static String BIZ_EVENT = "BIZ_EVENT";

    private static String BIZ_FOLLOWER = "BIZ_FOLLOWER";

    private static String BIZ_FOLLOWEE = "BIZ_FOLLOWEE";

    /**
     * for jedis
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getUserKey(int entityType,int entityId){
        return BIZ_USER + SPLIT + String.valueOf(entityType)+ SPLIT + String.valueOf(entityId);
    }

    //GEO测试key
    public static String getGeoTest(int entityType,int entityId){
        return BIZ_GEO_TEST + SPLIT + String.valueOf(entityType)+ SPLIT + String.valueOf(entityId);
    }


    public static String getEventKey(){
        return BIZ_EVENT;
    }

    /**
     * 某实体（话题，新闻，人）的follower  前缀
     * 话题A的所有粉丝缓存，  用户A的所有粉丝
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollower(Integer entityType,Integer entityId){
         return BIZ_FOLLOWER + SPLIT + String.valueOf(entityType)+ SPLIT + String.valueOf(entityId);
    }

    /**
     * 某用户关注某一实体的具体内容   前缀
     * A关注的所有 用户 缓存 ；B关注的所有话题缓存
     * @param entityType
     * @return
     */
    public static String getFollowee(Integer userId,Integer entityType){
        return BIZ_FOLLOWEE+ SPLIT + String.valueOf(userId)+SPLIT+String.valueOf(entityType);
    }
}
