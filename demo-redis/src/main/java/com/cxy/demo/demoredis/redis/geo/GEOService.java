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
 */
@Service
public class GEOService {
    private static final Logger logger  = LoggerFactory.getLogger(GEOService.class);
    //key  RedisKeyUtil.getGeoTest()

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public void add(City city){
        if( redisTemplate.opsForGeo().add("nanjing",city.getPoint(),city.getCityName())>0){
            logger.info("添加单个节点成功");
        }
     }

    public void add(Location location){
        if( redisTemplate.opsForGeo().add("nanjing",location.getPoint(),location.getLocationName())>0){
            logger.info("添加单个节点成功");
        }

    }

    public void addGeos(List<Location> locations){
        Map<Object, Point> geos  = locations.stream().collect(Collectors.toMap(x->x.getLocationName(), x->x.getPoint()));
        //Map<String,Point> geos = new HashMap<>();
        if( redisTemplate.opsForGeo().add("nanjing",geos)>0){
            logger.info("批量添加节点成功");
        }

    }

    public void removeGeo(String ...locationName){
        redisTemplate.opsForGeo().remove("nanjing",locationName);
    }

    //返回一个标准的地理空间的Geohash字符串,类似wtmvp5bpyd0
    public List<String> geoHash(String ...locationName){
      return  redisTemplate.opsForGeo().hash("nanjing",locationName);
    }

    public List<Point> getPos(String ...locationNames){
       return  redisTemplate.opsForGeo().position("nanjing",locationNames);
    }


    public Distance GEODIST(String lName1, String lName2){
        GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
        Distance distance = geoOps.distance("nanjing", lName1, lName2, DistanceUnit.KILOMETERS);
        return distance;
    }

    /**
     *  WITHDIST: 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致。
         WITHCOORD: 将位置元素的经度和纬度也一并返回。
        WITHHASH: 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。
        这个选项主要用于底层应用或者调试， 实际中的作用并不大。

     *  asc 从近到远  desc 从远到近
     * @return
     */
    public GeoResults<GeoLocation<Object>> GEORADIUS(Point target, Distance standard, Long limit) {
        GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();

        //设置geo查询参数
        GeoRadiusCommandArgs geoRadiusArgs = GeoRadiusCommandArgs.newGeoRadiusArgs();
        geoRadiusArgs = geoRadiusArgs.includeCoordinates().includeDistance();
        geoRadiusArgs.sortAscending();
        geoRadiusArgs.limit(limit);//限制查询数量

        GeoResults<GeoLocation<Object>> radiusGeo = geoOps.radius(
               "nanjing",
                new Circle(target, standard),
                geoRadiusArgs);

        return  radiusGeo;
    }

    //查询指定半径内匹配到的最大距离的一个地理空间元素
    public void GEORADIUSBYMEMBER(String member, Distance standard, Long limit) {
     GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
        //设置geo查询参数
        GeoRadiusCommandArgs geoRadiusArgs = GeoRadiusCommandArgs.newGeoRadiusArgs();
        geoRadiusArgs = geoRadiusArgs.includeCoordinates().includeDistance();//查询返回结果包括距离和坐标
        geoRadiusArgs.sortAscending();//按查询出的坐标距离中心坐标的距离进行排序
        geoRadiusArgs.limit(limit);//限制查询数量
        //new Distance(10, DistanceUnit.KILOMETERS)
        GeoResults<GeoLocation<Object>> radiusGeo = geoOps.radius(
                "nanjing", member, standard, geoRadiusArgs);
    }


}
