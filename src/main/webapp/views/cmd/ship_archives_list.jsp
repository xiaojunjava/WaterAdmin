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
            {field:'saName',title:'车船名称',width:264,align:'center',sortable:'true'},
            {field:'saOwner',title:'车船主人',width:264,align:'center',sortable:'true'},
            {field:'saNum',title:'车船号码',width:264,align:'center',sortable:'true'},
            {field:'saCode',title:'车台号码',width:264,align:'center',sortable:'true'},
            {field:'saType',title:'类型',width:264,align:'center'},
            {field:'saColor',title:'颜色',width:264,align:'center'},
            {field:'saMotorNum',title:'发动机号',width:264,align:'center'},
            {field:'saChassisNum',title:'底盘号码',width:264,align:'center'},
            {field:'saUse',title:'用途',width:264,align:'center'}
        ]];
        var IsCheckFlag=false;
        var rowIndexTo;//保存当前保存的是那条数据
        var jsonData = {
            url : '<%=request.getContextPath()%>/sa/getSADatas',
            title:'车船信息列表',
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
            width:'98%',
            height : '400',
            singleSelect:false,//为true时只能选择单行
            queryParams : queryData,
            onLoadSuccess : function(data) {    },
            onSelect:function (rowIndex, rowData) {
                if(!IsCheckFlag){
                    IsCheckFlag = true;
                    rowIndexTo=rowIndex;
                }else if(rowIndexTo==rowIndex){
                    IsCheckFlag = false;
                    $(this).datagrid("unselectRow",rowIndex);
                }else{
                    IsCheckFlag = false;
                }
            },
            singleSelect : true,//为false时可以选择多行
           // collapsible:true,
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
        $('#ff2').form('clear');//清除表单数据
        $("#tag").val("add");//操作标识
        $("#mywin").panel({title:"&nbsp;新增操作"});
        $('#mywin').window('open');
    }
    function edit() {
        var rows = $('#list_sa').datagrid('getSelections');

        if (rows&&rows.length == 1) {
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/sa/getSa",
                data:{"saId":rows[0].saId},
                async:false,//有了数据下面再打开窗口
                dataType: "json",
                success: function (data)
                {
                    $("#ff2").form('load',data);//默认值加载
                    $("#tag").val("edit");//操作标识
                 },
                error:function (data) {
                    alert("2"+JSON.stringify(data));
                }
            });
        } else {
            $.messager.alert('提示', '请选择一条记录！', 'warning');
            return false;
        }
        //先选择一条记录
        $("#mywin").panel({title:"&nbsp;编辑操作"});
        $('#mywin').window('open');
    }
    function del() {
                var rows = $('#list_sa').datagrid('getSelections');
                if (rows&&rows.length == 1) {
                    $.messager.confirm('Confirm','您确定要删除该条记录吗?',function(r){
                        if (r){
                            $.ajax({
                                type: "POST",
                                url: "<%=request.getContextPath()%>/sa/delSa",
                                data:{"saId":rows[0].saId},
                                dataType: "json",
                                success: function (data)
                                {
                                    if(data.tag){
                                        $.messager.alert('我的提示','您的操作已成功!');
                                        $('#mywin').window('close');
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
<jsp:include page="../loadingDiv.jsp" />
            <div class="easyui-panel" title="查询" style="width:98%;padding:10px 10px 10px 10px;margin:0px 0px 5px 0px">
                <div style="margin-bottom:0px">
                    <form id="ff1" method="post">
                                  车台号：<input class="easyui-textbox" name="saCode" style="width:12%" data-options="validType:'maxLength[20]'" >
                   &nbsp;&nbsp; 颜色：<input class="easyui-textbox" name="saColor" style="width:12%" data-options="validType:'maxLength[10]'" >
                   &nbsp;&nbsp; 发动机号：<input class="easyui-textbox" name="saMotorNum" style="width:15%" data-options="validType:'maxLength[20]'" >

                    <div style="display:inline; float:right">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm1()" style="width:70px">查询</a>&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm1()" style="width:70px">清空</a>
                    </div>
                    </form>
                </div>
            </div>
            <div id="list_sa"></div>
            <div id="mywin" class="easyui-window"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="top:60px;left:100px;width:400px;height:520px;padding:5px;">
                <div  style="width:90%;max-width:280px;padding:10px 10px 0px 40px;">
                    <form id="ff2" method="post">
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saName" style="width:100%" data-options="label:'车船名称:',required:true,validType:'maxLength[25]'">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" name="saOwner" style="width:100%" data-options="label:'车船主人:',required:true,validType:'maxLength[10]'">
                        </div>
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
                                 actionUrl="<%=request.getContextPath()%>/sa/saveSa";
                            }else if(tag&&tag=='edit'){
                                 actionUrl="<%=request.getContextPath()%>/sa/updateSa";
                            }
                            $.ajax({
                                type: "POST",
                                url:actionUrl,
                                data:form_data,
                                dataType: "json",
                                success: function (data)
                                {
                                    if(data.tag){
                                        $.messager.alert('操作提示','您的操作已成功!');
                                        $('#mywin').window('close');
                                        loadTable();
                                     }
                                },
                                error:function (data) {  alert("2"+JSON.stringify(data));   }
                            });
                    }
                }
                function clearForm1(){
                    $('#ff1').form('clear');
                }
                function clearForm(){
                    $('#ff2').form('clear');
                }

            </script>
</body>
</html>