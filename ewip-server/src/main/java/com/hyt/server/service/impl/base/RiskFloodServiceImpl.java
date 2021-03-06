package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskFlood;
import com.hyt.server.mapper.base.IRiskFloodMapper;
import com.hyt.server.service.base.IRiskFloodService;
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
@Service("riskFloodService")
public  class RiskFloodServiceImpl extends AbstractService<RiskFlood> implements IRiskFloodService {

    @Autowired
    private IRiskFloodMapper riskFloodMapper;

    @Override
    public PageInfo<RiskFlood> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskFlood> areaList = this.riskFloodMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<RiskFlood> selectList(Map<String, Object> map){
        return this.riskFloodMapper.findAll(map);
    }
}
