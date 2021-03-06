package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.FacilitySupply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("facilitySupplyMapper")
public interface IFacilitySupplyMapper extends IBaseMapper<FacilitySupply> {
    /**
     * 分页查询应急物资信息
     * @param map
     * @return
     */
    List<FacilitySupply> findAll(Map<String, Object> map);
}