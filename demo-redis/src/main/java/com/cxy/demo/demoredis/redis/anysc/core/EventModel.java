package com.cxy.demo.demoredis.redis.anysc.core;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cxy
 * @Date: 2019/5/11 15:26
 * @Description: 事件处理线程
 */
public class EventModel {
    @NotNull
    private String taskId;
    private EventType eventType;  //运行事件
    private Integer actorId;      //触发者
    /*组成一个运行载体*/
    private Integer entityType;
    private Integer entityId;

    private Integer entityOwnerId;//

    //重连次数
    private Integer times;

    //key-value形式包含更多信息
    private Map<String,String> exds = new HashMap<>();


    public EventModel(){
        super();
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public Integer getActorId() {
        return actorId;
    }

    public EventModel setActorId(Integer actorId) {
        this.actorId = actorId;
        return this;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(Integer entityType) {
        this.entityType = entityType;
        return this;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(Integer entityId) {
        this.entityId = entityId;
        return this;
    }

    public Integer getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(Integer entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExds() {
        return exds;
    }

    public EventModel setExds(Map<String, String> exds) {
        this.exds = exds;
        return this;
    }

    public EventModel setExd(String key,String value){
        this.exds.put(key,value);
        return this;
    }

    public String getValue(String key){
        return this.exds.get(key);
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
