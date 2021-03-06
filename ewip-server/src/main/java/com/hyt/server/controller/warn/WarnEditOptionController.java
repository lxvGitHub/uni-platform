package com.hyt.server.controller.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.warn.WarnEditFlow;
import com.hyt.server.entity.warn.WarnEditOption;
import com.hyt.server.service.publish.FormatUtil;
import com.hyt.server.service.publish.HttpUtils;
import com.hyt.server.service.publish.IPublishService;
import com.hyt.server.service.warn.IWarnEditOptionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警编辑控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"预警编辑操作"}, description = "WarnEditOptionController")
@RestController
@RequestMapping("/warn/option")
public class WarnEditOptionController {

    /**
     * 注入预警信息操作层
     */
    @Autowired
    private IWarnEditOptionService warnEditOptionService;

    /**
     * 注入对接分发平台接口
     */
    @Autowired
    private IPublishService publishService;

    @ApiOperation(value="分页查询预警编辑流程信息",httpMethod="POST",notes="根据参数列表分页查询预警编辑流程信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name="areaId", value="地区ID",required = true, dataType = "String", paramType = "query"),
                @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
                @ApiImplicitParam(name="flow", value="流程标识", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/select/flow")
    public ResultObject<Object> selectFlowByParam(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<WarnEditOption> pageInfo = this.warnEditOptionService.selectFlowByParam(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="添加预警编辑流程信息",httpMethod="POST",notes="根据参数列表添加预警编辑流程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="warnEditId", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="flow", value="流程标识",required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="currentFlow", value="当前预警流程", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationName", value="机构名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeId", value="操作用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeName", value="操作用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="advice", value="操作意见", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/insert/flow")
    public ResultObject<Object> insertFlow(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject result = this.warnEditOptionService.insert(map);
        // 获取流程状态值
        int status = result.getInteger("status");
        if(status > 0){
            // 发布后调用分发接口
            if(status == 4){
                Map<String, Object> param = new HashMap<>(result);
//                this.publishService.publish(param);
//                newPublishService.publish(result);

                System.out.println(param);
                Map newMap = new HashMap();
                String filesArray=result.get("files").toString();
                String files="";
                if(!filesArray.equals("")){
                    JSONArray myJsonArray = JSONArray.parseArray(filesArray);
                    if(myJsonArray.size()>0){
                        for(int i=0;i<myJsonArray.size();i++){
                            JSONObject json=myJsonArray.getJSONObject(i);
                            files +=","+json.get("name").toString();
                        }
                    }else{
                        files=",";
                    }
                }else{
                    files=",";
                }
                newMap.put("tacticsName", "刘松2019测试");
                newMap.put("extEmailAddress", "");
                newMap.put("extSmsAddress", "");
                newMap.put("extFtpAddress", "");
                newMap.put("extFaxAddress", "");
                newMap.put("extNotesAddress", "");
                newMap.put("title", param.get("title"));
                newMap.put("text", param.get("content"));
                newMap.put("fileName", files.substring(1));
                newMap.put("areaCode", "230000");
                newMap.put("userName", "test");

                HttpUtils httpUtils = new HttpUtils();
                String rstData = httpUtils.sendPost("http://172.19.112.36:8080/PublishService/services/PostService/sendByTactics",newMap);
                FormatUtil formatUtil = new FormatUtil() ;
                formatUtil.outputRstHtml( rstData ) ;
            }
            return ResultResponse.make(200,result.getString("msg"),map);
        }
        return ResultResponse.make(500,"操作失败",null);
    }


    @ApiOperation(value="预警驳回",httpMethod="POST",notes="根据流程ID驳回预警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="warnEditId", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="id", value="当前流程信息ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="flow", value="流程标识",required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="currentFlow", value="当前预警流程", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationName", value="机构名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeId", value="操作用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeName", value="操作用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="advice", value="操作意见", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/reject")
    public ResultObject<Object> reject(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        int num = this.warnEditOptionService.reject(map);
        if(num > 0){
            return ResultResponse.make(200,"预警驳回成功",map);
        }
        return ResultResponse.make(500,"预警驳回失败",null);
    }

    @ApiOperation(value="根据预警ID查询发布后的预警详细信息",httpMethod="GET",notes="根据预警ID查询发布后的预警详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/detail")
    public ResultObject<Object> selectWarnEditDetail(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = this.warnEditOptionService.selectWarnEditDetail(map);
        if (json != null){
            return ResultResponse.make(200,"查询警信息成功",json);
        }
        return ResultResponse.make(500,"查询预警信息失败",null);
    }

    @ApiOperation(value="根据预警ID查询发布后的预警流程信息",httpMethod="GET",notes="根据预警ID查询发布后的预警流程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/select/flow/id")
    public ResultObject<Object> selectFlowByWarnEditId(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        List<WarnEditFlow> list = this.warnEditOptionService.selectFlowByWarnEditId(map);
        if (list.size() > 0){
            return ResultResponse.make(200,"查询警流程成功",list);
        }
        return ResultResponse.make(500,"查询预警流程失败",null);
    }

    @ApiOperation(value="微信获取当天所有预警信息",httpMethod="GET",notes="微信获取当天所有预警信息")
    @GetMapping("/wechat")
    public ResultObject<Object> getWechatWarnInfo(){
        List<Map<String, Object>> list = this.warnEditOptionService.getWechatWarnInfo();
        if (list.size() > 0){
            return ResultResponse.make(200,"微信获取当天所有预警信息成功",list);
        }
        return ResultResponse.make(500,"微信获取当天所有预警信息失败",null);
    }

    /**
     * 首页信息提醒查询预警个数
     * @param map
     * @return
     */
    @GetMapping("/selectWarn")
    public JSONArray selectWarn(@RequestParam Map<String,Object> map){
        return this.warnEditOptionService.selectWarn(map);
    }


}
