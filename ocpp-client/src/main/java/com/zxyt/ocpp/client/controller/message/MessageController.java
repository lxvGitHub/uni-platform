package com.zxyt.ocpp.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.service.message.IMessageService;
import com.zxyt.ocpp.client.service.publish.IPublishService;
import com.zxyt.ocpp.client.utils.UploadFileUtil;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: JiangXincan
 * @Description: 一键发布控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"一键发布"}, description = "MessageController")
@RestController
@RequestMapping("/message")
public class MessageController {

    /**
     * 注入消息处理接口
     */
    @Autowired
    private IMessageService messageService;

    /**
     * 注入分发平台接口
     */
    @Autowired
    private IPublishService publishService;

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;


    /**
     * 传真和显示屏文件上传文件夹
     */
    @Value("${web.messageFile-path}")
    private String messageFile;

    @ApiOperation(value = "获取文件信息", httpMethod = "GET", notes = "根据FTP类型下载FTP上最新文件下载到本地制定路径，然后读取文件内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name="channelCode",value="渠道类型", dataType = "String",paramType = "query")
    })
    @GetMapping("/select/file")
    public ResultObject<Object> selectFTPFileInfo(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        Subject subject = SecurityUtils.getSubject();
        JSONObject json = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("areaId", json.getString("areaId"));
        map.put("organizationId", json.getString("organizationId"));
        JSONObject result = this.messageService.selectFTPFileInfo(map);
        if(result==null || result.getInteger("code") == 200){
            String msg = result.getString("msg");
            result.remove("msg");
            result.remove("code");
            return ResultResponse.make(200, msg ,result);
        }
        return ResultResponse.make(500,result.getString("msg"),null);
    }

    @ApiOperation(value = "添加一键发布信息", httpMethod = "POST", notes = "根据参数添加一键发布信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="发布标题", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="type",value="发布类型", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="record",value="国突对接（0：否，1：是）", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="content",value="发布内容", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areas",value="影响地区集合", dataType = "List<Map>",paramType = "query"),
            @ApiImplicitParam(name="channels",value="发布渠道集合", dataType = "List<Map>",paramType = "query"),
            @ApiImplicitParam(name="groups",value="发布群组集合", dataType = "List<Map>",paramType = "query")
    })
    @PostMapping(value = "/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String, Object> map,@RequestParam("warnFile") MultipartFile[] files) throws IOException {
        // 文件开始上传
        JSONArray file = UploadFileUtil.upload(files, uploadPath, messageFile);
        map.put("files", file != null ? file.toJSONString() : "");

        JSONObject result = this.messageService.insert(map);

        if(result.getInteger("code") == 200){
            // 调用分发接口
            this.publishService.publish(result);

            return ResultResponse.make(200,result.getString("msg"), result);
        }
        return ResultResponse.make(500,result.getString("msg"),null);
    }



}
