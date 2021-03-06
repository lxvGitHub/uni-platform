package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.FacilityShelter;
import com.hyt.server.mapper.base.IFacilityShelterMapper;
import com.hyt.server.service.base.IFacilityShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:29 2018-11-2
 * @Modified By:
 */
@Service("facilityShelterService")
public  class FacilityShelterServiceImpl extends AbstractService<FacilityShelter> implements IFacilityShelterService {

    @Autowired
    private IFacilityShelterMapper facilityShelterMapper;

    @Override
    public PageInfo<FacilityShelter> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<FacilityShelter> areaList = this.facilityShelterMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<FacilityShelter> selectList(Map<String, Object> map) {
        return this.facilityShelterMapper.findAll(map);
    }
}