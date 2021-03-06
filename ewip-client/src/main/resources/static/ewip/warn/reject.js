
layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    disaster: 'disaster'
});

layui.use(["table","form","laytpl","layer","disaster"], () => {
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,disaster = layui.disaster
        ,employee = layui.sessionData("ewip").employee
        ,$ = layui.$;   			// 引用layui的jquery


    /**
     * 预警流程格式化
     * @param d
     * @returns {string}
     */
    let flowFormat = d => {
        if(d.currentFlow == 0) return "<span class='layui-btn layui-btn-danger layui-btn-xs ewip-cursor-default'>录入</span>";
        if(d.currentFlow == 1) return "<span class='layui-btn layui-btn-warm layui-btn-xs ewip-cursor-default'>待审核</span>";
        if(d.currentFlow == 2) return "<span class='layui-btn layui-btn-normal layui-btn-xs ewip-cursor-default'>待签发</span>";
        if(d.currentFlow == 3) return "<span class='layui-btn layui-btn-xs ewip-cursor-default'>待应急办签发</span>";
        if(d.currentFlow == 4) return "<span class='layui-btn layui-btn-disabled layui-btn-xs ewip-cursor-default'>待发布</span>";
        if(d.currentFlow == 6) return "<span class='layui-btn layui-btn-warm layui-btn-xs ewip-cursor-default'>被驳回</span>";
    };

    /**
     * 预警类型格式化
     * @param d
     * @returns {string}
     */
    let warnTypeFormat = d => {
        if(d.warnType == "Actual") return "<span class='layui-btn layui-btn-danger layui-btn-xs ewip-cursor-default'>实际</span>";
        if(d.warnType == "Exercise") return "<span class='layui-btn layui-btn-warm layui-btn-xs ewip-cursor-default'>演练</span>";
        if(d.warnType == "Test") return "<span class='layui-btn layui-btn-normal layui-btn-xs ewip-cursor-default'>测试</span>";
        if(d.warnType == "Draft") return "<span class='layui-btn layui-btn-xs ewip-cursor-default'>草稿</span>";
    };

    /**
     * 预警图片格式化
     * @param d
     * @returns {string}
     */
    let iconFormat = d => {
        if(d.disasterIcon !="" && d.disasterIcon != null) {
            return "<img src='/client/"+d.disasterIcon+"'  style='width:50px;height:50px;' >";
        }else{
            return "暂无图片";
        }
    };

    /**
     * 颜色转换
     * @param d
     * @returns {string}
     */
    let colorFormat = d => {
        return disaster.color(d.disasterColor,1);
    };

    /**
     * 级别转换
     * @param d
     * @returns {string}
     */
    let levelFormat = d => {
        return disaster.level(d.disasterLevel);
    };

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/warn/option/select/flow'
        ,where:{currentFlow: 6, isOption: 0, areaId:employee.areaId, organizationId: employee.organizationId} // 查询流程中预警编辑提交信息
        ,page:true
        ,even: true
        ,height: 'full-165'
        ,limits:[10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers',          title: '编号'}
            ,{field: 'disasterIcon',    title: '预警图片',      width:90, templet: iconFormat}
            ,{field: 'title',           title: '预警标题',      width:400,}
            ,{field: 'disasterName',    title: '预警名称',      width:120}
            ,{field: 'disasterColor',   title: '预警颜色',      width:90, templet:colorFormat}
            ,{field: 'disasterLevel',   title: '预警级别',      width:140,templet: levelFormat}
            ,{field: 'currentFlow',     title: '当前流程',       width:90, templet: flowFormat}
            ,{field: 'employeeName',    title: '提交人员',      width:120, sort: true}
            ,{field: 'warnType',        title: '预警类型',      sort: true, templet: warnTypeFormat}
            ,{field: 'editTime',        title: '编辑时间',      width:160, sort: true}
            ,{title: '操&nbsp;&nbsp;作', align:'center',        width:160,toolbar: '#btnGroupOption'}
        ]]
        ,done:function () {
            if(employee.organizationType==1){
                $(".handle").show();
            }else{
                $(".handle").hide();
            }
        }
    });

    /**
     * 修改后重新刷新列表，curr: 1重新从第 1 页开始
     */
    let reloadTable = param => {
        table.reload('table', {
            page: { curr: 1 },
            where: { //设定异步数据接口的额外参数，任意设
                disasterColor: param == undefined ? '' : param.disasterColor
                ,disasterLevel: param == undefined ? '' : param.disasterLevel
                ,warnType: param == undefined ? '' : param.warnType
            }
        });
    };

    /**
     * 统一按钮操作对象
     * @type {{updateOption: updateOption}}
     */
    let active = {
        resendOption: obj => {
            let index = layer.open({
                title: "<i class='layui-icon layui-icon-form'></i>预警编辑"
                ,type: 2
                ,content: "/client/page/warn/resend/" + obj.data.id +"/1"
                ,success: (layero, index) => {
                    setTimeout(() => {
                        layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500);
                },end: () => {
                    reloadTable(); // 刷新列表
                }
            });
            layer.full(index);
        }
        /**
         * 终止预警
         * @param obj
         */
        ,stopOption: obj => {
            $.ajax({
                async: true
                ,type: 'POST'
                ,data: {warnEditId : obj.data.id}
                ,url: '/client/warn/edit/stopWarn'
                ,dataType: 'json'
                ,success: function(json){
                if(json.code == 200){
                    // 刷新列表
                    reloadTable();
                }
                // 弹出提示信息，2s后自动关闭
                layer.msg(json.msg, {time: 2000});
                }
            });
        }
    };
    /**
     * 监听头部搜索
     */
    form.on('submit(search)', data => {
        reloadTable(data.field);
    });

    /**
     * 监听列表中按钮事件
     */
    table.on('tool(table)', obj => {
        active[obj.event] ? active[obj.event].call(this, obj) : '';
    });


});