package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskGeologicService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskGeologic")
public class RiskGeologicController {

        @Autowired
        private IRiskGeologicService riskGeologicService;

        /**
         * 根据地质灾害隐患点ID删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskGeologicService.deleteById(id);
        }

        /**
         * 批量删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskGeologicService.deleteBatch(id);
        }

        /**
         * 分页查询地质灾害隐患点信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
            return this.riskGeologicService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
                return this.riskGeologicService.selectList(map);
        }

}
