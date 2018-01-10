<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>采沙船主通讯录管理</title>
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

        //--获取采沙船主通讯录数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'asaId', title: '序号', width: 80, align: 'center', sortable: 'true',},
                {field: 'asaName', title: '船主姓名', width: 80, align: 'center', sortable: 'true'},
                {field: 'asaSex', title: '性别', width: 80, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){//0-女，1-男
                        return '女';
                    }else if (value == '1'){
                        return '男';
                    }
                }},
                {field: 'asaIdCardNo', title: '身份证号码', width: 80, align: 'center'},
                {field: 'asaEmail', title: '邮箱地址', width: 80, align: 'center'},
                {field: 'asaTel', title: '手机号码', width: 80, align: 'center'},
                {field: 'asaType', title: '类型', width: 80, align: 'center',formatter:function(value,row,index){
                    if (value == '2'){//2采沙船主，3-运沙船主
                        return '采沙船主';
                    }else if (value == '3'){
                        return '运沙船主';
                    }
                }},
                /*{field: 'countryId', title: '所属国家', width: 80, align: 'center'},
                {field: 'cityId', title: '所在城市', width: 80, align: 'center'},*/
                {field: 'street', title: '街道', width: 80, align: 'center'},
                {field: 'zipcode', title: '邮编', width: 80, align: 'center'},
                {field: 'birthday', title: '出生日期', width: 80, align: 'center'}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/shipmaster/getShipmasterData?asaType=2',
                title: '采沙船主列表',
                halign: 'center',
                align: 'center',
                method: 'get',
                columns: columns,
                rownumbers: true,
                remoteSort: true,
                pagination: true,
                autoRowHeight: false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
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

            $('#list_asalcai').datagrid(jsonData);//加载数据
            $('#list_asalcai').datagrid('hideColumn','asaId');

        }
        function add() {
            $('#asalcaiModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#asalcaiModule").panel({title: "&nbsp;添加采沙船主"});
            $('#asalcaiModule').window('open');
        }

        function edit() {
            var rows = $('#list_asalcai').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#asalcaiModuleForm').form('clear');

                //--给字段赋值
                $("#asaId").val(rows[0].asaId);//--主键，唯一
                $("#asaName").textbox('setValue',rows[0].asaName);
                $("#asaSex").combobox('setValue',rows[0].asaSex);
                $("#asaIdCardNo").textbox('setValue',rows[0].asaIdCardNo);
                $("#asaEmail").textbox('setValue',rows[0].asaEmail);
                $("#asaTel").textbox('setValue',rows[0].asaTel);
                $("#street").textbox('setValue',rows[0].street);
                $("#zipcode").textbox('setValue',rows[0].zipcode);
                $("#birthday").textbox('setValue',rows[0].birthday);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }
            //先选择一条记录
            $("#asalcaiModule").panel({title: "&nbsp;编辑采沙船主通讯录"});
            $('#asalcaiModule').window('open');
        }
        function del() {
            var rows = $('#list_asalcai').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('Confirm', '您确定要删除该条记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/shipmaster/delShipmaster",
                            data: {"asaId": rows[0].asaId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#asalcaiModule').window('close');
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
<!-- 采沙船主通讯录查询条件 -->
<div class="easyui-panel" title="查询" style="width:98%;padding:10px 10px 10px 10px;margin:0px 0px 5px 0px">
    <div style="margin-bottom:0px">
        <form id="asalcaiSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            船主姓名：<input class="easyui-textbox" name="asaName" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            性别：<select class="easyui-combobox" name="asaSex" style="width:12%;" data-options="required:false">
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitAsalcaiSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearAsalcaiSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 采沙船主通讯录列表 -->
<div id="list_asalcai"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="asalcaiModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:550px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>
        <input type="hidden" id="asaId" /><!--序号，主键，唯一-->
        <form id="asalcaiModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="asaName" id="asaName" style="width:100%"
                       data-options="label:'船主姓名:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="asaSex" id="asaSex" style="width:100%;" label="性别:" data-options="required:false">
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="asaIdCardNo" id="asaIdCardNo" style="width:100%"
                       data-options="label:'身份证号码:',required:true,validType:'maxLength[18]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="asaEmail" id="asaEmail" style="width:100%"
                       data-options="label:'邮箱地址:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="asaTel" id="asaTel" style="width:100%"
                       data-options="label:'手机号码:',required:true,validType:'maxLength[11]'">
            </div>
            <%--<div style="margin-bottom:20px">
                <input class="easyui-textbox" name="countryId" id="countryId" style="width:100%"
                       data-options="label:'所属国家:',required:false,validType:'phoneNum'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="cityId" id="cityId" style="width:100%"
                       data-options="label:'所在城市:',required:false,validType:'maxLength[20]'">
            </div>--%>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="street" id="street" style="width:100%"
                       data-options="label:'街道:',required:false,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="zipcode" id="zipcode" style="width:100%"
                       data-options="label:'邮编:',required:false,validType:'maxLength[6]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="birthday" id="birthday" style="width:100%"
                       data-options="label:'出生日期:',required:false,validType:'maxLength[10]'">
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
    function submitAsalcaiSearchForm() {
        if ($('#asalcaiSearchForm').form("validate")) {//通过校验
            var search_data = $('#asalcaiSearchForm').serializeObject();
            loadTable(search_data);
        }
    }
    function clearAsalcaiSearchForm(){
        $('#asalcaiSearchForm').form('clear');
    }

    //新增&编辑
    function submitForm2() {
        if ($('#asalcaiModuleForm').form("validate")) {//通过校验
            var tag = $('#tag').val();
            var actionUrl;
            var form_data = $('#asalcaiModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/shipmaster/saveShipmaster?asaType=2";
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/shipmaster/updateShipmaster?asaType=2&asaId="+$("#asaId").val();
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
                        $('#asalcaiModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    function clearForm() {
        $('#asalcaiModuleForm').form('clear');
    }

</script>
</body>
</html>