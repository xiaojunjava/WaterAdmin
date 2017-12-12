<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>水政监察</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css" />

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>
<script type="text/javascript">
    $(function(){
        loadTable();
    });
    function loadTable(queryData){
        var columns = [[
            {field:'ck',checkbox:true},
            {field:'rpType',title:'类型',width:264,align:'center',formatter : function(value, row, index) {
                if ("1"==value) {
                    return "航道";
                } else if("2"==value){
                    return "区域边界";
                }
            }  },
            {field:'rpBelong',title:'归属',width:264,align:'center',formatter : function(value, row, index) {
                if ("1"==value) {
                    return "采沙航道";
                } else if("2"==value){
                    return "采沙区域";
                }else if("3"==value){
                    return "执法航道";
                }else if("4"==value){
                    return "执法区域";
                }
            }  },
            {field:'rpLineName',title:'路线名称',width:264,align:'center'},
            {field:'rpPoints',title:'路线点集合',width:264,align:'center'},
            {field:'rpStatus',title:'线路有效状态',width:264,align:'center',formatter : function(value, row, index) {
                if ("1"==value) {
                    return "有效";
                } else {
                    return "无效";
                }
            }  },
            {field:'rpMakePerson',title:'制定人',width:264,align:'center'},
            {field:'rpMakeTime',title:'线路制定时间',width:264,align:'center'},
            {field:'remark',title:'备注',width:264,align:'center'}
        ]];
        var jsonData = {
            url : '<%=request.getContextPath()%>/rp/getRPDatas',
            title:'路线规划列表',
            halign:'center',
            align:'center',
            method:'get',
            columns : columns,
            rownumbers : true,
            remoteSort : true,
            pagination : true,
            autoRowHeight:false,
            fitColumns: true,//允许表格自动缩放，以适应父容器
            pageSize : '10',
            width:'90%',
            height : '400',
            singleSelect:false,//为true时只能选择单行
            queryParams : queryData,
            // onDblClickRow : otherMethod.onDblClickRow,
            onLoadSuccess : function(data) {
                // alert(JSON.stringify(data));
            },
            singleSelect : true,//为false时可以选择多行
            collapsible:true,
            toolbar:[{
                text: '新增',
                iconCls: 'icon-save',
                handler: add						//handler类似事件
            },'-',{
                text: '编辑',
                iconCls: 'icon-add',
                handler: edit
            },'-',{
                text: '删除',
                iconCls: 'icon-remove',
                handler: del
            } ]

        };
        $('#list_sa').datagrid(jsonData);//加载数据
    }
    function add() {
        alert("与GIS结合实现中... ...");
    }
    function edit() {
        var rows = $('#list_sa').datagrid('getSelections');

        if (rows&&rows.length == 1) {
           alert("与GIS结合实现中... ...");
        } else {
            $.messager.alert('提示', '请选择一条记录！', 'warning');
            return false;
        }
    }
    function del() {
                var rows = $('#list_sa').datagrid('getSelections');
                if (rows&&rows.length == 1) {
                    $.messager.confirm('Confirm','您确定要删除该条记录吗?',function(r){
                        if (r){
                            $.ajax({
                                type: "POST",
                                url: "<%=request.getContextPath()%>/sa/delRp",
                                data:{"saId":rows[0].saId},
                                dataType: "json",
                                success: function (data)
                                {
                                    if(data.tag){
                                        $.messager.alert('我的提示','您的操作已成功!');
                                        loadTable();
                                    }
                                },
                                error:function (data) {
                                    alert("2"+JSON.stringify(data));
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert('提示', '请选择一条记录！', 'warning');
                    return false;
                }

    }
</script>
</head>
<body id="login_bg" align="center">
			<p>路线规划管理</p>
            <div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
                <div style="margin-bottom:0px">
                    <form id="ff1" method="post">

                        &nbsp;&nbsp; 路线名称：<input class="easyui-textbox" name="rpLineName" style="width:12%" data-options="validType:'maxLength[100]'" >
                         &nbsp;&nbsp; 线路有效状态：<select class="easyui-combobox" name="rpStatus" style="width:10%;" editable="false">
                                                            <option ></option>
                                                            <option value="1">有效</option>
                                                            <option value="0">无效</option>
                                                      </select>
                        &nbsp;&nbsp; &nbsp;&nbsp;开始时间：<input class="easyui-datetimebox"  style="width:10%;" name="startTime" editable="false">
                                        &nbsp;&nbsp;结束时间：<input class="easyui-datetimebox"  style="width:10%;" name="endTime" editable="false">
                    <div style="display:inline; float:right">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm1()" style="width:80px">查询</a>&nbsp;&nbsp;
                    </div>
                    </form>
                </div>
            </div>
            <div id="list_sa"></div>
            <div id="mywin" class="easyui-window"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:450px;padding:5px;">
                <div  style="width:80%;max-width:300px;padding:10px 60px;">
                    <form id="ff2" method="post">
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saNum" style="width:100%" data-options="label:'车船号码:',required:true,validType:'maxLength[20]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saCode" style="width:100%" data-options="label:'车台号码:',required:true,validType:'maxLength[20]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saType" style="width:100%" data-options="label:'类型:',required:true,validType:'maxLength[1]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saColor" style="width:100%" data-options="label:'颜色:',required:true,validType:'maxLength[5]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saMotorNum" style="width:100%" data-options="label:'发动机号:',required:true,validType:'maxLength[20]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saChassisNum" style="width:100%" data-options="label:'底盘号码:',required:true,validType:'maxLength[20]'">
                        </div>
                        <div style="margin-bottom:10px">
                            <input class="easyui-textbox" name="saUse" style="width:100%;height:60px" data-options="label:'用途:',multiline:true,validType:'maxLength[200]'">
                            <input type="hidden" id="tag"/>
                            <input type="hidden" id="saId" name="saId" />
                        </div>
                    </form>
                </div>
                <div style="text-align:center;">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm2()" style="width:80px">提交</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
                </div>
            </div>
            <script>
                //查询
                function submitForm1(){
                    if($('#ff1').form("validate")){//通过校验
                       var search_data= $('#ff1').serializeObject();
                       loadTable(search_data);
                    }
                }
                //新增&编辑
                function submitForm2(){
                    if($('#ff2').form("validate")) {//通过校验
                            var tag=$('#tag').val();
                            var actionUrl;
                            var form_data = $('#ff2').serializeObject();
                            if(tag&&tag=='add'){
                                var actionUrl="<%=request.getContextPath()%>/sa/saveSa";
                            }else if(tag&&tag=='edit'){
                                var actionUrl="<%=request.getContextPath()%>/sa/updateSa";
                            }
                            $.ajax({
                                type: "POST",
                                url:actionUrl,
                                data:form_data,
                                dataType: "json",
                                success: function (data)
                                {
                                    if(data.tag){
                                        $.messager.alert('我的提示','您的操作已成功!');
                                        $('#mywin').window('close');
                                        loadTable();
                                     }
                                },
                                error:function (data) {  alert("2"+JSON.stringify(data));   }
                            });
                    }
                }
                function clearForm(){
                    $('#ff2').form('clear');
                }

            </script>
</body>
</html>