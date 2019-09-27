package com.cxy.demo.demoredis.redis.geo;

import org.springframework.data.geo.Point;

/**
 *   redis 经度longitude在前，纬度latitude在后。
 */
public class City {
    private Integer id;
    private String cityName;
    private Point point;

    public City(){
        super();
    }

    public City(Integer id, String cityName, Point point) {
        this.id = id;
        this.cityName = cityName;
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(double longitude,double latitude ) {
        this.point = new Point(longitude,latitude);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", point=" + point +
                '}';
    }
}
