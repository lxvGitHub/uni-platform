package com.hyt.monitor.service.sys;

import com.github.pagehelper.PageInfo;
import com.hyt.monitor.config.common.universal.IBaseService;
import com.hyt.monitor.entity.sys.Area;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 地区管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IAreaService extends IBaseService<Area> {

    PageInfo<Area> selectAll(Map<String, Object> map);

    Area selectById(String id);

}
