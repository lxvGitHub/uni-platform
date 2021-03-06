package com.hyt.monitor.entity.monitor;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 地区实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class Area {

    private String id;

    private String areaName;

    private String code;

    private String pId;

    private Integer level;

    private Double longitude;

    private Double latitude;

    private Double altitude;

    private String vicinity;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String parentName;

    private Integer child;


    public Area() {
    }

    public Area(String areaName, String code, String pId, Integer level, Double longitude, Double latitude, Double altitude, Date createTime, String parentName, Integer child) {
        this.areaName = areaName;
        this.code = code;
        this.pId = pId;
        this.level = level;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.createTime = createTime;
        this.parentName = parentName;
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
