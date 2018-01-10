<%--
  Description: 执法文书-->行政处罚告知书
  User: lvzhixue
  Date: 2017/12/28 14:46
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>执法文书</title><!--行政处罚告知书-->
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

        //--获取执法文书数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'ciId', title: '案件序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'prId', title: '巡查上报序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'ciName', title: '案件名称', width: 180, align: 'center'},
                {field: 'ciDatetime', title: '发生时间', width: 150, align: 'center'},
                {field: 'ciType', title: '案件类型', width: 100, align: 'center'},
                {field: 'ciStatus', title: '案件状态', width: 80, align: 'center', formatter: function(value,row,index){
                    if (value == '1'){
                        return '立案申请';
                    }else if (value == '2'){
                        return '案件处理中';
                    }else if (value == '3'){
                        return '案件结案';
                    }else if (value == '4'){
                        return '无效案件';
                    }
                }},
                {field: 'ciContent', title: '案件内容', width: 200, align: 'center'},
                {field: 'ciPlace', title: '案件地点', width: 80, align: 'center'},
                {field: 'ciAcceptPerson', title: '受理人', width: 100, align: 'center'},
                {field: 'userId', title: '取证人编号', width: 100, align: 'center'},
                {field: 'userName', title: '取证人', width: 100, align: 'center'},
                {field: 'applyReason', title: '申请理由', width: 200, align: 'center'},
                {field: 'remark', title: '备注', width: 200, align: 'center'},
                {field: 'opt', title: '操作', width: 120, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='editXzcfgzs("+row.ciId+","+row.prId+")' style='width:80px'>编辑</a>&nbsp;&nbsp;"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='openUploadModule()' style='width:80px'>上传回执</a>&nbsp;&nbsp;"
                        +"</div>";

                    return tmpOptStr;
                }}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/caseinfo/getApprovalCaseList',
                title: '执法文书列表',
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
                width: '99%',
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

            $('#list_approval').datagrid(jsonData);//加载数据
            $('#list_approval').datagrid('hideColumn','prId');
            $('#list_approval').datagrid('hideColumn','ciId');
            $('#list_approval').datagrid('hideColumn','ciType');
            $('#list_approval').datagrid('hideColumn','applyReason');
            $('#list_approval').datagrid('hideColumn','ciAcceptPerson');
            $('#list_approval').datagrid('hideColumn','userId');

        }

        //--执法文书通过
        function approvalCaseAdopt(ciId, prId) {

            alert('执法文书通过');
            $.messager.confirm('确认', '您确定要通过该案件吗?', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/approvalCaseAdopt",
                        data: {
                            "ciId": ciId,
                            "prId": prId
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.tag) {
                                $.messager.alert('我的提示', '您的通过操作已成功!');
                                $('#acceptanceModule').window('close');
                                loadTable();
                            }
                        },
                        error: function (data) {
                            $.messager.alert('错误', '您的通过操作失败，如果疑问，请联系技术支持人员！');
                        }
                    });
                }
            });

        }

        //--执法文书驳回
        function approvalCaseRefuse(ciId, prId) {
            alert('执法文书驳回');
            $.messager.confirm('确认', '您确定要驳回该案件吗?', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/approvalCaseRefuse",
                        data: {
                            "ciId": ciId,
                            "prId": prId
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.tag) {
                                $.messager.alert('我的提示', '您的驳回操作已成功!');
                                $('#acceptanceModule').window('close');
                                loadTable();
                            }
                        },
                        error: function (data) {
                            $.messager.alert('提示', '您的驳回操作失败，如果疑问，请联系技术支持人员！');
                        }
                    });
                }
            });

        }     

        //--打开编辑行政处罚告知书页面
        function editXzcfgzs(ciId, prId) {

            $("#xzcfgzsModule").panel({title: "&nbsp;编辑行政处罚告知书"});
            $('#xzcfgzsModule').window('open');
        }

        //--打开上传回执窗口
        function openUploadModule(){
            $('#uploadReceiptForm').form('clear');

            $("#uploadReceiptModule").panel({title: "&nbsp;上传回执"});
            $('#uploadReceiptModule').window('open');
        }
    </script>
</head>
<body id="login_bg" align="center">
<%@include file="../../views/loadingDiv.jsp"%>

<!-- 执法文书查询条件 -->
<div class="easyui-panel" title="查询" style="width:99%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="approvalSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起止时间：<input class="easyui-datebox" name="startTime" style="width:12%;" data-options="validType:'maxLength[20]'">
            -
            <input class="easyui-datebox" name="endTime" style="width:12%;" data-options="validType:'maxLength[20]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitApprovalSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearApprovalSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 执法文书列表 -->
<div id="list_approval"></div>


<!--行政处罚告知书，当事人陈述、申辩-->
<div id="xzcfgzsModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:800px;height:650px;padding:0px;">

    <div class="easyui-tabs" style="width:100%;height:560px">
        <div id="xzcfgzsSubModule" title="行政处罚告知书" style="padding:10px">
            <!-- 行政处罚告知书 -->
            <img src="../resources/handingcase/tmpImg/xzcfgzs.png">
        </div>
        <div id="cssbModule" title="当事人陈述、申辩" data-options="" style="padding:10px">
            <!-- 当事人陈述、申辩 -->
            <div align="center">
                <form id="newCaseModuleForm" method="post">
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="prSiteDescription" id="prSiteDescription" style="width:80%;height:420px;"
                               data-options="label:'陈述、申辩:',required:false,validType:'maxLength[100]',multiline:true" >
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br/>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="zfws_ok()" style="width:80px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="zfws_cancel()" style="width:80px">取消</a>
    </div>
</div>

<!--上传回执窗口-->
<div id="uploadReceiptModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:800px;height:650px;padding:0px;">
    <form id="uploadReceiptForm">
        <div style="width:80%;max-width:300px;padding:10px 60px;">
            <input multiple style="width:300px" id="fileputHB" name="fileputHB" class="easyui-filebox"
                           data-options="label:'回执文件:',onChange:change_photo">
            <div id="Imgdiv" style="margin-top: 20px">
                回执预览：<img id="Img" width="600px" height="480px" style="margin-top: 20px"/>
            </div>
        </div>
    </form>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadReceipt_ok()" style="width:80px">上传</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadReceipt_cancel()" style="width:80px">取消</a>
    </div>
</div>

<script>
    //查询
    function submitApprovalSearchForm() {
        if ($('#approvalSearchForm').form("validate")) {//通过校验
            var search_data = $('#approvalSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearApprovalSearchForm(){
       $('#approvalSearchForm').form('clear');
    }

    //--执法文书-确定
    function zfws_ok(){
        $('#xzcfgzsModule').window('close');
    }

    //--执法文书-取消
    function zfws_cancel(){
        $('#xzcfgzsModule').window('close');
    }

    //--上传回执--上传
    function uploadReceipt_ok(){
        $('#uploadReceiptModule').window('close');
    }
    //--上传回执--取消
    function uploadReceipt_cancel(){
        $('#uploadReceiptModule').window('close');
    }

    //--选择照片
    function change_photo(){
        PreviewImage($("input[name='fileputHB']")[0], 'Img', 'Imgdiv');
    }

    //--预览
    function PreviewImage(fileObj,imgPreviewId,divPreviewId){
        var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
        var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();
        var browserVersion= window.navigator.userAgent.toUpperCase();
        if(allowExtention.indexOf(extention)>-1){
            if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等
                if(window.FileReader){
                    var reader = new FileReader();
                    reader.onload = function(e){
                        document.getElementById(imgPreviewId).setAttribute("src",e.target.result);
                    }
                    reader.readAsDataURL(fileObj.files[0]);
                }else if(browserVersion.indexOf("SAFARI")>-1){
                    alert("不支持Safari6.0以下浏览器的图片预览!");
                }
            }else if (browserVersion.indexOf("MSIE")>-1){
                if(browserVersion.indexOf("MSIE 6")>-1){//ie6
                    document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);
                }else{//ie[7-9]
                    fileObj.select();
                    if(browserVersion.indexOf("MSIE 9")>-1)
                        fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问
                    var newPreview =document.getElementById(divPreviewId+"New");
                    if(newPreview==null){
                        newPreview =document.createElement("div");
                        newPreview.setAttribute("id",divPreviewId+"New");
                        newPreview.style.width = document.getElementById(imgPreviewId).width+"px";
                        newPreview.style.height = document.getElementById(imgPreviewId).height+"px";
                        newPreview.style.border="solid 1px #d2e2e2";
                    }
                    newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
                    var tempDivPreview=document.getElementById(divPreviewId);
                    tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);
                    tempDivPreview.style.display="none";
                }
            }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox
                var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
                if(firefoxVersion<7){//firefox7以下版本
                    document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());
                }else{//firefox7.0+
                    document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));
                }
            }else{
                document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);
            }
        }else{
            alert("仅支持"+allowExtention+"为后缀名的文件!");
            fileObj.value="";//清空选中文件
            if(browserVersion.indexOf("MSIE")>-1){
                fileObj.select();
                document.selection.clear();
            }
            fileObj.outerHTML=fileObj.outerHTML;
        }
    }
</script>


</body>
</html>