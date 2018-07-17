package com.hyt.server.service.sys;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.Employee;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 员工接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IEmployeeService extends IBaseService<Employee> {

    PageInfo<Employee> selectAll(Map<String,Object> map);

    Employee login(Map<String, Object> map);

    Employee selectById(Map<String, Object> map);

}