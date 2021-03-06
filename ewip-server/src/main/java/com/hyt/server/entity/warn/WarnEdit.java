package com.hyt.server.entity.warn;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 预警编辑实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "WarnEdit",description = "预警编辑信息")
@Table(name = "warn_edit")
public class WarnEdit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "disaster_id",length = 64)
    private String disasterId;

    @Column(name = "disaster_name",length = 100)
    private String disasterName;

    @Column(name = "disaster_color",length = 1)
    private Integer disasterColor;

    @Column(name = "disaster_level",length = 1)
    private Integer disasterLevel;

    @Column(name = "warn_type",length = 20)
    private String warnType;

    @Column(name = "scope",length = 20)
    private String scope;

    @Column(name = "msg_type",length = 20)
    private String msgType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "edit_time")
    private Date editTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "forecast_time")
    private Date forecastTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "invalid_time")
    private Date invalidTime;

    @Column(name = "record",length = 1)
    private Integer record;

    @Column(name = "measure",length = 2000)
    private String measure;

    @Column(name = "instruction",length = 2000)
    private String instruction;

    @Column(name = "flow",length = 50)
    private String flow;

    @Column(name = "status",length = 1)
    private Integer status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;

    private String disasterCode;

    private String icon;

    private String areaCode;

    private String areaName;

    private String organizationName;

    private String employeeId;

    private String employeeName;

    private Double longitude;

    private Double latitude;

    private String content;

    public WarnEdit() {}

    public WarnEdit(String title, String areaId, String organizationId, String disasterId, String disasterName, Integer disasterColor, Integer disasterLevel, String warnType, String scope, String msgType, Date editTime, Date forecastTime, Date invalidTime, Integer record, String measure, String instruction, String flow, Integer status, Date createTime, Date sendTime, String disasterCode, String icon,String areaCode, String areaName, String organizationName, String employeeId, String employeeName, Double longitude, Double latitude, String content) {
        this.title = title;
        this.areaId = areaId;
        this.organizationId = organizationId;
        this.disasterId = disasterId;
        this.disasterName = disasterName;
        this.disasterColor = disasterColor;
        this.disasterLevel = disasterLevel;
        this.warnType = warnType;
        this.scope = scope;
        this.msgType = msgType;
        this.editTime = editTime;
        this.forecastTime = forecastTime;
        this.invalidTime = invalidTime;
        this.record = record;
        this.measure = measure;
        this.instruction = instruction;
        this.flow = flow;
        this.status = status;
        this.createTime = createTime;
        this.sendTime = sendTime;
        this.disasterCode = disasterCode;
        this.icon = icon;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.organizationName = organizationName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    public String getDisasterName() {
        return disasterName;
    }

    public void setDisasterName(String disasterName) {
        this.disasterName = disasterName;
    }

    public Integer getDisasterColor() {
        return disasterColor;
    }

    public void setDisasterColor(Integer disasterColor) {
        this.disasterColor = disasterColor;
    }

    public Integer getDisasterLevel() {
        return disasterLevel;
    }

    public void setDisasterLevel(Integer disasterLevel) {
        this.disasterLevel = disasterLevel;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Date forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getDisasterCode() {
        return disasterCode;
    }

    public void setDisasterCode(String disasterCode) {
        this.disasterCode = disasterCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
