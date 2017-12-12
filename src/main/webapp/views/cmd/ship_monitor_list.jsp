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
        $(".datagrid-toolbar table tbody tr").append("<td width='80%'>&nbsp;&nbsp;</td><td width='10%'></td>");
        $(".datagrid-toolbar table").attr("align","center");
        $(".datagrid-toolbar table tbody tr td:first-child").attr("width","10%");
        $("#search").appendTo($(".datagrid-toolbar table tbody tr td:last-child"));
    });
    function loadTable(queryData){
        var columns = [[
            {field:'ck',checkbox:true},
            {field:'smBeginTime',title:'轨迹开始时间',width:264,align:'center'},
            {field:'smEndTime',title:'轨迹结束时间',width:264,align:'center'},
            {field:'smTraJectory',title:'轨迹（点集合）',width:264,align:'center'},
            {field:'remark',title:'备注',width:264,align:'center'}
        ]];
        var jsonData = {
            url : '<%=request.getContextPath()%>/sm/getSMDatas',
            title:'车船实时位置监控记录列表',
            halign:'center',
            align:'center',
            method:'get',
            columns : columns,
            striped:true,
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
            toolbar:[ {
                text: '删除',
                iconCls: 'icon-remove',
                handler: del
            } ]
        };
        $('#list_sa').datagrid(jsonData);//加载数据
    }
    function del() {
                var rows = $('#list_sa').datagrid('getSelections');
                if (rows&&rows.length == 1) {
                    $.messager.confirm('Confirm','您确定要删除该条记录吗?',function(r){
                        if (r){
                            $.ajax({
                                type: "POST",
                                url: "<%=request.getContextPath()%>/sm/delSm",
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
			<p>车船实时位置监控记录管理</p>
            <div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
                <div style="margin-bottom:0px">
                    <form id="ff1" method="post">
                                       &nbsp;&nbsp;开始时间：<input class="easyui-datetimebox"  style="width:10%;" name="startTime" editable="false">
                                        &nbsp;&nbsp;结束时间：<input class="easyui-datetimebox"  style="width:10%;" name="endTime" editable="false">
                    <div style="display:inline; float:right">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm1()" style="width:80px">查询</a>&nbsp;&nbsp;
                    </div>
                    </form>
                </div>
            </div>
            <div id="list_sa"></div>
            <div id="search" >
               <input  class="easyui-searchbox" data-options="prompt:'Please Input Value',searcher:submitForm1"   />
            </div>
            <script>
                //查询
                function submitForm1(){
                    if($('#ff1').form("validate")){//通过校验
                       var search_data= $('#ff1').serializeObject();
                       loadTable(search_data);
                    }
                }
            </script>
</body>
</html>