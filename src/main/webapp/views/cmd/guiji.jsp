<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>指挥调度</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/default.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/guiji.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/dijit/themes/nihilo/nihilo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/esri/css/esri.css">

    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css"/>


    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/default.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/utils.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/init.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/historyRoute.js"></script>


    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/guiji.js"></script>
    <script type="text/javascript">
        $(function () {
            showtime();//显示时间
            //loadTable();//加载数据表格
            //searchAddCar();//添加搜索框
            loadTablePL();
            searchAddPer();

            listSa();//显示车船列表
            listPL();//列表人员
        });
    </script>
</head>
<body class="guiji">
<div class="header">
    <div class="logo">
        <img src="<%=request.getContextPath()%>/resources/cmd/image/zhdd_logo.png" alt="">
    </div>
    <div class="logout" onclick="window.location='<%=request.getContextPath()%>/login.jsp'">
        <img src="<%=request.getContextPath()%>/resources/cmd/image/logout.png" alt="" class="logoutimg">
    </div>
    <div class="userinfo">
        <div class="text">
            <div class="username">
                调度员：张婷婷
            </div>
            <div id="show">时钟</div>
        </div>
        <div class="userimg">
            <img src="<%=request.getContextPath()%>/resources/cmd/image/hujihua.jpg" alt="" class="usericon"/>
        </div>
    </div>
    <div class="nav">
        <a href="<%=request.getContextPath()%>/main/jump.cmd">首页</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.video">视频监控</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.guiji" class="underline">历史轨迹</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.route">线路预设</a>
    </div>
</div>
<div class="main">
    <div class="left">
        <div class="top">
            <div class="person">
                <div class="info_img">
                    <img src="<%=request.getContextPath()%>/resources/cmd/image/guiji_person.png" alt="">
                </div>
                <div id="person_list" style="height:70%;width:108%;overflow-y:scroll;overflow-x:hidden;"></div>
            </div>
            <div class="car">
                <div class="<%=request.getContextPath()%>/resources/cmd/info_img">
                    <img src="<%=request.getContextPath()%>/resources/cmd/image/guiji_car.png" alt="">
                </div>
                <div id="car_list" style="height:70%;width:108%;overflow-y:scroll;overflow-x:hidden;"></div>
            </div>
            <div class="ship">
                <div class="info_img">
                    <img src="<%=request.getContextPath()%>/resources/cmd/image/guiji_ship.png" alt="">
                </div>
                <div id="ship_list" style="height:70%;width:108%;overflow-y:scroll;overflow-x:hidden;"></div>
            </div>
        </div>
        <div class="bottom">
            <!-- 表格开始 -->
            <table id="list_sa"></table>
            <!-- 表格结束 -->
        </div>
    </div>
    <div class="right" id="allmap"></div>
</div>
</body>

</html>
