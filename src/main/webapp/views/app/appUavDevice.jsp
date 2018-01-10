<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>无人机设备管理</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css"/>

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>
    <script type="text/javascript">

        $(function () {
            //--加载表格数据
            loadTable();
        });

        //--获取无人机设备数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'uavId', title: '序号', width: 264, align: 'center', sortable: 'true',},
                {field: 'uavCode', title: '无人机编号', width: 264, align: 'center', sortable: 'true'},
                {field: 'uavModel', title: '无人机型号', width: 264, align: 'center'},
                {field: 'purchaseDate', title: '购买日期', width: 264, align: 'center'},
                {field: 'status', title: '设备状态', width: 264, align: 'center', formatter: function(value,row,index){
                    if (value == '0'){
                        return '正常';
                    }else if (value == '1'){
                        return '故障';
                    }else if (value == '2'){
                        return '检修中';
                    }else if (value == '3'){
                        return '停用';
                    }
                }}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/uavDevice/getUavDeviceData',
                title: '无人机设备列表',
                halign: 'center',
                align: 'center',
                method: 'get',
                columns: columns,
                rownumbers: true,
                remoteSort: true,
                pagination: true,
                autoRowHeight: false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
                striped:true,//--隔行显示不同背景色
                pageSize: '10',
                width: '98%',
                height: '400',
                singleSelect: false,//为true时只能选择单行
                queryParams: queryData,
                // onDblClickRow : otherMethod.onDblClickRow,
                onLoadSuccess: function (data) {
                    // alert(JSON.stringify(data));
                },
                singleSelect: true,//为false时可以选择多行
                collapsible: true,
                toolbar: [{
                    text: '新增',
                    iconCls: 'icon-save',
                    handler: add						//handler类似事件
                }, '-', {
                    text: '编辑',
                    iconCls: 'icon-add',
                    handler: edit
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: del
                }]

            };

            $('#list_uav').datagrid(jsonData);//加载数据
            $('#list_uav').datagrid('hideColumn','uavId');

        }

        //--点击新增按钮动作
        function add() {
            $('#uavModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#uavModule").panel({title: "&nbsp;添加无人机设备"});//--标题
            $('#uavModule').window('open');//--打开新增页面
        }

        //--点击编辑按钮动作
        function edit() {
            var rows = $('#list_uav').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#uavModuleForm').form('clear');

                //--给字段赋值
                $("#uavId").val(rows[0].uavId);//--主键，唯一

                $("#uavCode").textbox('setValue',rows[0].uavCode);
                $("#uavModel").textbox('setValue',rows[0].uavModel);
                $("#purchaseDate").textbox('setValue',rows[0].purchaseDate);
                $("#status").combobox('setValue',rows[0].status);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }

            //先选择一条记录，再打开窗口
            $("#uavModule").panel({title: "&nbsp;编辑无人机设备"});
            $('#uavModule').window('open');
        }

        //--点击删除按钮动作
        function del() {
            var rows = $('#list_uav').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('Confirm', '您确定要删除该条记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/uavDevice/delUAV",
                            data: {"uavId": rows[0].uavId},
                            dataType: "json",
                            contentType : 'application/json',
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#uavModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                alert("2" + JSON.stringify(data));
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
<%@include file="../../views/loadingDiv.jsp"%>
<!-- 无人机设备查询条件 -->
<div class="easyui-panel" title="查询" style="width:98%;padding:10px 10px 10px 10px;margin:0px 0px 5px 0px">
    <div style="margin-bottom:0px">
        <form id="uavSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            无人机编号：<input class="easyui-textbox" name="uavCode" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            无人机状态：<select class="easyui-combobox" name="status" style="width:12%;">
                            <option value="0">正常</option>
                            <option value="1">故障</option>
                            <option value="2">检修中</option>
                            <option value="3">停用</option>
                      </select>

            <div style="display:inline; float:right">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitUavSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearUavSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 无人机设备列表 -->
<div id="list_uav"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="uavModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:450px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>

        <input type="hidden" id="uavId" /><!--序号，主键，唯一-->

        <form id="uavModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="uavCode" id="uavCode" style="width:100%"
                       data-options="label:'无人机编号:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="uavModel" id="uavModel" style="width:100%"
                       data-options="label:'无人机型号:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="purchaseDate"  id="purchaseDate" style="width:100%"
                       data-options="label:'购买日期:',required:true,validType:'maxLength[10]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="status" id="status" style="width:100%;" label="状态:" data-options="required:true">
                    <option value="0">正常</option>
                    <option value="1">故障</option>
                    <option value="2">检修中</option>
                    <option value="3">停用</option>
                </select>
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
    function submitUavSearchForm() {
        if ($('#uavSearchForm').form("validate")) {//通过校验
            var search_data = $('#uavSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearUavSearchForm(){
       $('#uavSearchForm').form('clear');
    }


    //新增&编辑
    function submitForm2() {
        if ($('#uavModuleForm').form("validate")) {//通过校验

            var tag = $('#tag').val();
            var actionUrl = "";
            var form_data = $('#uavModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/uavDevice/saveUAV";
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/uavDevice/updateUAV?uavId="+$("#uavId").val();
            }

            $.ajax({
                type: "POST",
                url: actionUrl,
                data: JSON.stringify(form_data),
                dataType: "json",
                contentType : 'application/json',
                success: function (data) {
                    if (data.tag) {
                        $.messager.alert('我的提示', '您的操作已成功!');
                        $('#uavModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    //--清空
    function clearForm() {
        $('#uavModuleForm').form('clear');
    }

</script>
</body>
</html>