<%--
  Description:  取证记录列表
  RecOfEvidence: lvzhixue
  Date: 2017/12/20 11:36
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">

    $(function () {
        //--加载表格数据
        loadRecOfEvidenceTable();
    });

    //--获取取证记录管理数据
    function loadRecOfEvidenceTable(queryData) {
        //--显示的列模型
        var columns = [[
            {field: 'ecId', title: '调查取证记录序号', width: 264, align: 'center', sortable: 'true'},
            {field: 'ciId', title: '案件序号', width: 264, align: 'center', sortable: 'true'},
            {field: 'ecName', title: '记录名称', width: 264, align: 'center'},

            {field: 'ecDatetime', title: '记录时间', width: 264, align: 'center'},
            {field: 'ecRecPerson', title: '记录人', width: 264, align: 'center'},
            {field: 'ecType', title: '记录类型', width: 264, align: 'center'},
            {field: 'ecPlace', title: '记录地点', width: 264, align: 'center'},
            {field: 'ecContent', title: '记录内容', width: 264, align: 'center'},
            {field: 'remark', title: '备注', width: 264, align: 'center'},
            {field: 'opt', title: '操作', width: 80, align: 'center',formatter:function(value,row,index){
                var tmpOptStr = "<div style='display:inline; float:right'>"
                    +"<a href='javascript:;' class='easyui-linkbutton' onclick='approvalCaseAdopt("+row.ciId+","+row.prId+")' style='width:80px'>详情</a>&nbsp;&nbsp;"
                    +"</div>";

                return tmpOptStr;
            }}

        ]];


        //--获取数据库中的数据
        var jsonData = {
            url: '<%=request.getContextPath()%>/app/getEvidenceCollectionList',
            //title: '取证记录列表',
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

        $('#list_RecOfEvidence').datagrid(jsonData);//加载数据
        $('#list_RecOfEvidence').datagrid('hideColumn', 'ecId');
        $('#list_RecOfEvidence').datagrid('hideColumn', 'ciId');
    }

    //查询
    function submitRecOfEvidenceSearchForm() {
        if ($('#RecOfEvidenceSearchForm').form("validate")) {//通过校验
            var search_data = $('#RecOfEvidenceSearchForm').serializeObject();
            loadRecOfEvidenceTable(search_data);
        }
    }

    function clearRecOfEvidenceSearchForm() {
        $('#RecOfEvidenceSearchForm').form('clear');
    }

</script>


<div id="selectRecOfEvidenceModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:980px;height:500px;padding:5px;">
    <!-- 取证记录列表 -->
    <div id="list_RecOfEvidence"></div>
    <br>
    <div style="display:inline; float:right">
        <a href="javascript:;" class="easyui-linkbutton" onclick="cancelSelectRecOfEvidence()" style="width:80px">关闭</a>&nbsp;&nbsp;
    </div>
</div>