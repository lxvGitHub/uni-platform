package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 医院
 */
@Table(name = "base_unit_hospital")
@Getter
@Setter
@Entity
public class UnitHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "district",length = 64)
    private String district;

    /**
     * 占地面积（㎡）
     */
    @Column(name = "square" )
    private Double square;

    @Column(name = "doctor",length = 1)
    private Integer doctor;

    @Column(name = "nurse",length = 1)
    private Integer nurse;

    @Column(name = "ambulance",length = 1)
    private Integer ambulance;

    @Column(name = "bed",length = 1)
    private Integer bed;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "unit",length = 64)
    private String unit;

    @Column(name = "principal",length = 64)
    private String principal;

    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "description",length = 500)
    private String description;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "createUser", referencedColumnName = "id")
    private Employee createUser;

    @ManyToOne
    @JoinColumn(name = "updateUser", referencedColumnName = "id")
    private Employee updateUser;

}