package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.FacilityShelter;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-应急避难所接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IFacilityShelterService extends IBaseService<FacilityShelter>{

        PageInfo<FacilityShelter> selectAll(Map<String, Object> map);

}