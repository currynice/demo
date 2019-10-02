package com.cxy.demo.demoredis.redis.geo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.redis.connection.RedisGeoCommands.*;


/**
 * 地理位置
 * GeoHash将二维经纬度映射成一魏整数，将其作为zset的score(52位，取出时还原)
 * 避免集群部署，造成的迁移卡顿
 */
@Service
public class GEOService {
    private static final Logger logger  = LoggerFactory.getLogger(GEOService.class);
    //key  RedisKeyUtil.getGeoTest()

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public void add(City city,String keyName){
        if( redisTemplate.opsForGeo().add(keyName,city.getPoint(),city.getCityName())>0){
            logger.info("添加单个节点成功");
        }
     }

    public void add(Location location,String keyName){
        if( redisTemplate.opsForGeo().add(keyName,location.getPoint(),location.getLocationName())>0){
            logger.info("添加单个节点成功");
        }

    }

    public void addGeos(List<Location> locations,String keyName){
        Map<Object, Point> geos  = locations.stream().collect(Collectors.toMap(x->x.getLocationName(), x->x.getPoint()));
        //Map<String,Point> geos = new HashMap<>();
        if( redisTemplate.opsForGeo().add(keyName,geos)>0){
            logger.info("批量添加节点成功");
        }

    }

    public void removeGeo(String keyName,String ...locationName){
        redisTemplate.opsForGeo().remove(keyName,locationName);
    }

    //返回一个标准的地理空间的Geohash字符串,类似wtmvp5bpyd0
    // http://geohash.org/${hash} 校验
    public List<String> geoHash(String keyName,String ...locationName){
      return  redisTemplate.opsForGeo().hash(keyName,locationName);
    }

    /**
     * 获得集合中任意元素的经纬度
     * @param keyName
     * @param locationNames
     * @return  细微误差
     */
    public List<Point> getPos(String keyName,String ...locationNames){
       return  redisTemplate.opsForGeo().position(keyName,locationNames);
    }


    public Distance getDist(String keyName,String lName1,String lName2,DistanceUnit distanceUnit){
        return redisTemplate.opsForGeo().distance(keyName, lName1, lName2,distanceUnit);

    }

    /**
     * 两个元素之间的距离
     * @param keyName
     * @param lName1
     * @param lName2
     * @param distanceUnit
     * @return
     */
    public double getDistValue(String keyName,String lName1, String lName2,DistanceUnit distanceUnit){
        return getDist(keyName, lName1, lName2,distanceUnit).getValue();
    }

    /**
     *  WITHDIST: 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致。
         WITHCOORD: 将位置元素的经度和纬度也一并返回。
        WITHHASH: 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。
        这个选项主要用于底层应用或者调试， 实际中的作用并不大。

     *  asc 从近到远  desc 从远到近
     * @return
     */
    public GeoResults<GeoLocation<Object>> geoRadius(Point target, Distance standard, Long limit) {
        GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();

        //设置geo查询参数
        GeoRadiusCommandArgs geoRadiusArgs = GeoRadiusCommandArgs.newGeoRadiusArgs();
        geoRadiusArgs = geoRadiusArgs.includeCoordinates()//WITHCOORD
                .includeDistance();//WITHDIST
        geoRadiusArgs.sortAscending();//sortDescending
        geoRadiusArgs.limit(limit);//限制查询数量

        GeoResults<GeoLocation<Object>> radiusGeo = geoOps.radius(
               "nanjing",
                new Circle(target, standard),
                geoRadiusArgs);

        return  radiusGeo;
    }




}
