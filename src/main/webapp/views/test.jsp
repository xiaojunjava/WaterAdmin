<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>水政监察</title>
<script type="text/javascript" src="../resources/jquery-3.2.1.js" ></script>
<script type="text/javascript" src="../resources/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(function(){
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/test/getTestData",
            data: {},
            cache: false,
            async : true,
            dataType: "json",
            success: function (data)
            {
              $("#test_div").html(JSON.stringify(data));
            },
            error:function (data) {
            alert("2"+JSON.stringify(data));
            }
        });
    });
    function redisA(){
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/test/testReidsAdd",
            dataType: "json",
            success: function (data)
            {
                $("#r1").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("error");
            }
        });
    }
    function redisB(){
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/test/testReidsGet",
            dataType: "json",
            success: function (data)
            {
                $("#r2").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("error");
            }
        });
    }
    function redisC(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/test/testReidsDel",
            dataType: "json",
            success: function (data)
            {
                $("#r3").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("error");
            }
        });
    }
    function listPr() {
        var mydata={"prId":3};
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/pr/getPatrolReportDatas",
            data:{"prId":3,"startTime":"2016-10-10"
            },//不能JSON.stringify(mydata)
            dataType: "json",
            success: function (data)
            {
                $("#table_div").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("2"+JSON.stringify(data));
            }
        });
    }
    function savePr() {
        var mydata={"plId":4, "prSiteDescription":"现场描述1", "prPosition":"周家屯1号院","prReportTime":"2017-12-12 10:10:10","remark":"测试" };
        //var mydata={"plId":4, "prSiteDescription":"现场描述1", "prPosition":"周家屯1号院","remark":"测试" };
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/pr/savePr",
            data: JSON.stringify(mydata),
            dataType: "json",
            contentType : 'application/json',
            success: function (data)
            {
                $("#savePr").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("2"+JSON.stringify(data));
            }
        });
    }
    function updatePr() {
        var jsonStr={"prId":3, "plId":4,"prPosition":"周家屯1号院","prReportTime":"2017-11-12 14:11:13","remark":"测试"};
        var jsonDate={ prId:3, plId:4, prPosition:"周家屯1号院", prReportTime:"2017-11-12 14:11:13", remark:"测试" };
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/pr/updatePr",
            data: JSON.stringify(jsonStr),
            cache: false,
            dataType: "json",
            contentType : 'application/json',
            success: function (data)
            {
                $("#updatePr").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("2"+JSON.stringify(data));
            }
        });
    }
    function listPra() {
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/pra/getPatrolReportAttachmentsData",
            data: {
                praId:"1"
            },
            cache: false,
            async : true,
            dataType: "json",
            success: function (data)
            {
                $("#list_pra").html(JSON.stringify(data));
            },
            error:function (data) {
                alert("2"+JSON.stringify(data));
            }
        });
    }
    //--上传照片文件
    function uploadPhotoFile(){
        $.ajaxFileUpload({
            fileElementId : ['photoFile1','photoFile2'],
            data:{ prId:3 },
            type : "POST",
            dataType : 'json',
            url : '<%=request.getContextPath()%>/pra/uploadFile',
            success : function(data) {
                alert(data);
                alert(JSON.stringify(data));
                if (data.tag) {
                    alert('上传成功！');
                } else {
                    alert(data.message);
                }
                ajaxLoadEnd();
            },
            error : function(data) {
                alert(JSON.stringify(data));
                ajaxLoadEnd();
            },
            complete : function(XMLHttpRequest, textStatus) {
            }
        });

    }
</script>
</head>
<body id="login_bg" >
            <p align="center"><li><a href="#" onclick="redisA()"> redis测试Add</a></li></p>
            <div id="r1"></div>
            <p align="center"><li><a href="#" onclick="redisB()"> redis测试Get</a></li></p>
            <div id="r2"></div>
            <p align="center"><li><a href="#" onclick="redisC()"> redis测试Del</a></li></p>
            <div id="r3"></div>
			<p align="center"><li>test数据2</li></p>
            <div id="test_div"></div>
            <p align="center"><li><a href="#" onclick="listPr()"> 巡查上报查询</a></li></p>
            <div id="table_div"></div>
            <p align="center"><li><a href="#" onclick="savePr()"> 巡查上报新增</a></li></p>
            <div id="savePr"></div>
            <p align="center"><li><a href="#" onclick="updatePr()"> 巡查上报更新</a></li></p>
            <div id="updatePr"></div>
            <p align="center"><li><a href="#" onclick="listPra()"> 巡查上报附件查询</a></li></p>
            <div id="list_pra"></div>
            <p align="center"><li>上传巡查上报附件测试</li></p>
            <div class="title">
                <p>
                    <input type="file" id="photoFile1" name="photoFiles" />
                    <input type="file" id="photoFile2" name="photoFiles" />
                    <input type="button" value="上传" onclick="uploadPhotoFile()" />
                </p>
            </div>
</body>
</html>