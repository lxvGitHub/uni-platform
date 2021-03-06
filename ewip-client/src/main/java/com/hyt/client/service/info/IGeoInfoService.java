package com.hyt.client.service.info;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Service("geoInfoService")
@FeignClient("EWIP-SERVER")
public interface IGeoInfoService {

        /**
         * 根据ID删除信息
         * @param id
         * @return
         */
        @DeleteMapping("/geoInfo/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除信息
         * @param id
         * @return
         */
        @PostMapping("/geoInfo/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询信息
         * @param map
         * @return
         */
        @GetMapping("/geoInfo/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表
         * @return
         */
        @GetMapping("/geoInfo/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/geoInfo/list")
        JSONObject selectList(@RequestParam Map<String, Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/geoInfo/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/geoInfo/update")
        JSONObject update(@RequestParam Map<String, Object> map);


        /**
         * 数据导入
         * @return
         */
        @PostMapping("/geoInfo/importData")
        JSONObject importData(@RequestParam Map<String, Object> map, @RequestBody List<Map<String, Object>> list);

        /**
         * 查询灾种信息
         */
        @GetMapping("/geoInfo/selectDisasterList")
        List<Map<String,Object>> selectDisasterList(@RequestParam Map<String,Object> map);
}
