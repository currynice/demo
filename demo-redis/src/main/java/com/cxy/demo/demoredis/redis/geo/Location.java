package com.cxy.demo.demoredis.redis.geo;

import org.springframework.data.geo.Point;

/**
 *   redis 经度longitude在前，纬度latitude在后。
 */
public class Location {
    private Integer id;
    private String locationName;
    private Point point;

    public Location(){
        super();
    }

    public Location(Integer id, String locationName, Point point) {
        this.id = id;
        this.locationName = locationName;
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(double longitude,double latitude ) {
        this.point = new Point(longitude,latitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                ", point=" + point +
                '}';
    }
}
