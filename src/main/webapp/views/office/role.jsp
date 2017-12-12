<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>角色管理</title>
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
        var curSetRoleId = 0;

        $(function () {
            //--加载表格数据
            loadTable();
        });

        function viewPopedom(roleId){
            curSetRoleId = roleId;
            //--获取roleId所对应的权限，设置权限树所有节点为未选中状态，进而根据本角色的权限设置权限树
            //先将回显数据全部清除
            var root=$("#tt").tree('getRoot');
            $("#tt").tree('uncheck',root.target);

            //根据roleId查询出在sys_privilege中对应的记录
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/role/getRoleModule",
                data: {"roleId": roleId},
                dataType: "json",
                success: function (data) {
                    //alert(data[0].roleId);
                    //alert(data[0].fmId);
                    $(data).each(function () {
                        //这里遍历获取到了 priRes的值， 即resId
                        // 根据resId查找到这个节点
                        var node = $('#tt').tree('find', this.fmId);
                        $('#tt').tree('check', node.target);
                    });
                },
                error: function (data) {
                    $.messager.alert('提示', '获取角色权限信息失败！');
                }
            });

            $("#popedomTreeDiv").panel({title: "&nbsp;角色权限"});
            $('#popedomTreeDiv').window('open');//--打开新增页面
        };

        //--获取角色管理数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'roleId', title: '角色序号', width: 264, align: 'center', sortable: 'true'},
                {field: 'roleName', title: '角色名称', width: 264, align: 'center', sortable: 'true'},
                {field: 'remark', title: '备注', width: 264, align: 'center'},
                {field: 'opt', title: '操作', width: 45, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='viewPopedom("+row.roleId+")' style='width:80px'>设置</a>&nbsp;&nbsp;"
                        +"</div>";

                    return tmpOptStr;
                }}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/role/getRoleList',
                title: '角色列表',
                halign: 'center',
                align: 'center',
                method: 'get',
                columns: columns,
                rownumbers: true,
                nowrap: false,
                remoteSort: true,
                pagination: true,
                autoRowHeight: false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
                striped:true,//--隔行显示不同背景色
                pageSize: '10',
                width: '90%',
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

            $('#list_role').datagrid(jsonData);//加载数据
            $('#list_role').datagrid('hideColumn','roleId');
        }

        //--点击新增按钮动作
        function add() {
            $('#roleModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#roleModule").panel({title: "&nbsp;添加角色"});//--标题
            $('#roleModule').window('open');//--打开新增页面
        }

        //--点击编辑按钮动作
        function edit() {
            var rows = $('#list_role').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#roleModuleForm').form('clear');

                //--给字段赋值
                $("#roleId").val(rows[0].roleId);//--主键，唯一

                //--初始化数据
                $("#roleName").textbox('setValue',rows[0].roleName);
                $("#remark").textbox('setValue',rows[0].remark);


                //alert(rows[0].roleList[0].roleId);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }

            //先选择一条记录，再打开窗口
            $("#roleModule").panel({title: "&nbsp;编辑角色"});
            $('#roleModule').window('open');
        }

        //--点击删除按钮动作
        function del() {
            var rows = $('#list_role').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('警告', '您确定要删除该角色吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/role/delRole",
                            data: {"roleId": rows[0].roleId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#roleModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                $.messager.alert('提示', '该角色正在使用中，不能被删除，如果疑问，请联系技术支持人员！');
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

<!-- 角色管理查询条件 -->
<div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="roleSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            角色名称：<input class="easyui-textbox" name="roleName" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!--角色：<input class="easyui-combobox" name="roleId" id="roleIdSearch" style="width:12%"/>-->

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitRoleSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearRoleSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 角色管理列表 -->
<div id="list_role"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="roleModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:200px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>

        <input type="hidden" id="roleId" /><!--角色序号，主键，唯一-->

        <form id="roleModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="roleName" id="roleName" style="width:100%"
                       data-options="label:'角色名称:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="remark" id="remark" style="width:100%"
                       data-options="label:'备注:',required:false,validType:'maxLength[20]'">
            </div>

        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitRoleModuleForm()" style="width:80px">提交</a>
        <a href="javascript:;" class="easyui-linkbutton" onclick="clearRoleModuleForm()" style="width:80px">清空</a>
    </div>
</div>

<%@include file="popedomTree.jsp"%>

<script>
    //查询
    function submitRoleSearchForm() {
        if ($('#roleSearchForm').form("validate")) {//通过校验
            var search_data = $('#roleSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearRoleSearchForm(){
       $('#roleSearchForm').form('clear');
    }


    //新增&编辑
    function submitRoleModuleForm() {
        if ($('#roleModuleForm').form("validate")) {//通过校验

            var tag = $('#tag').val();
            var actionUrl = "";

            var roleId = $("#roleId").val();//--角色编号
            //alert(roleId);

            var form_data = $('#roleModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/role/saveRole";
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/role/updateRole?roleId="+$("#roleId").val();
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
                        $('#roleModule').window('close');
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
    function clearRoleModuleForm() {
        $('#roleModuleForm').form('clear');
    }


</script>
</body>
</html>