<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">

    $(function () {
        //--加载表格数据
        loadUserTable();
    });

    //--获取用户管理数据
    function loadUserTable(queryData) {
        //--显示的列模型
        var columns = [[
            {field: 'ck', checkbox: true},
            {field: 'userId', title: '用户序号', width: 264, align: 'center', sortable: 'true',},
            {field: 'orgId', title: '机构序号', width: 264, align: 'center', sortable: 'true'},
            {field: 'userName', title: '用户名称', width: 264, align: 'center'},
            {
                field: 'sex', title: '性别', width: 264, align: 'center', formatter: function (value, row, index) {
                if (value == '0') {//0-女，1-男
                    return '女';
                } else if (value == '1') {
                    return '男';
                }
            }
            },
            {field: 'userLoginname', title: '登录名称', width: 264, align: 'center'},
            {field: 'userPsw', title: '密码', width: 264, align: 'center'},
            {
                field: 'orgName', title: '机构名称', width: 264, align: 'center', formatter: function (value, row, index) {
                return row.organization.orgName;
            }
            },
            {
                field: 'roleId', title: '角色编号', width: 264, align: 'center', formatter: function (value, row, index) {
                var tmpRoleStr = "";
                for (var i = 0; i < row.roleList.length; i++) {
                    if (i == row.roleList.length - 1) {
                        tmpRoleStr = tmpRoleStr + row.roleList[i].roleId;
                    } else {
                        tmpRoleStr = tmpRoleStr + row.roleList[i].roleId + "-";
                    }
                }
                return tmpRoleStr;
            }
            },
            {
                field: 'roleName', title: '角色', width: 264, align: 'center', formatter: function (value, row, index) {
                var tmpRoleStr = "";
                for (var i = 0; i < row.roleList.length; i++) {
                    tmpRoleStr = tmpRoleStr + row.roleList[i].roleName + "<br>";
                }
                return tmpRoleStr;
            }
            },
            {field: 'phoneNumber', title: '电话号码', width: 264, align: 'center'},
            {field: 'birthday', title: '出生日期', width: 264, align: 'center'}
        ]];


        //--获取数据库中的数据
        var jsonData = {
            url: '<%=request.getContextPath()%>/login/getUsersData',
            title: '用户列表',
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
            striped: true,//--隔行显示不同背景色
            pageSize: '10',
            width: '100%',
            height: '400',
            singleSelect: false,//为true时只能选择单行
            queryParams: queryData,
            // onDblClickRow : otherMethod.onDblClickRow,
            onLoadSuccess: function (data) {
                // alert(JSON.stringify(data));
            },
            singleSelect: true,//为false时可以选择多行
            collapsible: true,
            toolbar: []
        };

        $('#list_user').datagrid(jsonData);//加载数据
        $('#list_user').datagrid('hideColumn', 'userId');
        $('#list_user').datagrid('hideColumn', 'orgId');
        $('#list_user').datagrid('hideColumn', 'roleId');
        $('#list_user').datagrid('hideColumn', 'userLoginname');
        $('#list_user').datagrid('hideColumn', 'userPsw');
        $('#list_user').datagrid('hideColumn', 'roleName');

    }

    //查询
    function submitUserSearchForm() {
        if ($('#userSearchForm').form("validate")) {//通过校验
            var search_data = $('#userSearchForm').serializeObject();
            loadUserTable(search_data);
        }
    }

    function clearUserSearchForm() {
        $('#userSearchForm').form('clear');
    }

</script>


<div id="selectUserModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:800px;height:580px;padding:5px;">
    <!-- 用户管理查询条件 -->
    <div class="easyui-panel" title="查询" style="width:100%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
        <div style="margin-bottom:0px">
            <form id="userSearchForm" method="post">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                用户名称：<input class="easyui-textbox" name="userName" style="width:12%;"
                            data-options="validType:'maxLength[20]'">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!--角色：<input class="easyui-combobox" name="roleId" id="roleIdSearch" style="width:12%"/>-->

                <div style="display:inline; float:right">
                    <a href="javascript:;" class="easyui-linkbutton" onclick="submitUserSearchForm()"
                       style="width:80px">查询</a>&nbsp;&nbsp;
                    <a href="javascript:;" class="easyui-linkbutton" onclick="clearUserSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
                </div>
            </form>
        </div>
    </div>

    <!-- 用户管理列表 -->
    <div id="list_user"></div>
    <br>
    <div style="display:inline; float:right">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitSelectUser()" style="width:80px">分配</a>&nbsp;&nbsp;
        <a href="javascript:;" class="easyui-linkbutton" onclick="cancelSelectUser()" style="width:80px">取消</a>&nbsp;&nbsp;
    </div>
</div>