<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>线路预设</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/route_plan.css">
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/dijit/themes/nihilo/nihilo.css"/>--%>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/esri/css/esri.css"/>--%>
  <script type="text/javascript">
        //配置arcgis拓展解析天地图服务类引用的路径
        dojoConfig = {
            parseOnLoad: true,
            packages: [{
                name: 'tdlib',
                location: this.location.pathname.replace(/\/[^/]+$/, "") + "/js/tdlib"
                //location: "js/tdlib"
            }]
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/layui/layui.js" ></script>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/init.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/route_plan.js"></script>
  <%--<script src="js/main/map.js"></script>--%>
  <%--<script type="text/javascript" src="js/main/utils.js"></script>--%>
</head>
<body>
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
        <img src="<%=request.getContextPath()%>/resources/cmd/image/hujihua.jpg" alt="" class="usericon" />
      </div>
    </div>
    <div class="nav">
      <a href="<%=request.getContextPath()%>/main/jump.cmd">首页</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.video">视频监控</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.guiji">历史轨迹</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.route" class="underline">线路预设</a>
    </div>
  </div>
<div class="main">
  <div class="xianlu_list">
	<div class="xianlu_title"></div>
      <div id="listRP">
      </div>
  </div>
  <div class="xianlu_tools">
  	<div class="xianlu_quyu">区域设定</div>
  	<div class="xianlu_sheding">线路设定</div>
  	<div class="xianlu_clear">清除</div>
  </div>
  <div class="xianlu_map" id="map"></div>
</div>
</body>
</html>