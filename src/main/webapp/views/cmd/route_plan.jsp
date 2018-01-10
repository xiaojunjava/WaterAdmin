<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>线路预设</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/route_plan.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/dijit/themes/nihilo/nihilo.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/esri/css/esri.css"/>
    <script type="text/javascript">
        //配置arcgis拓展解析天地图服务类引用的路径
        dojoConfig = {
            parseOnLoad: true,
            packages: [{
                name: 'tdlib',
                location: "<%=request.getContextPath()%>/resources/cmd/js/tdlib"
                //location: "js/tdlib"
            }]
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/layui/layui.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/utils.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/init.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/map.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/draw.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/common.js"></script>
</head>
<body>
<%--<div class="header">--%>
    <%--<div class="logo">--%>
      <%--<img src="<%=request.getContextPath()%>/resources/cmd/image/zhdd_logo.png" alt="">--%>
    <%--</div>--%>
    <%--<div class="logout" onclick="window.location='<%=request.getContextPath()%>/login.jsp'">--%>
      <%--<img src="<%=request.getContextPath()%>/resources/cmd/image/logout.png" alt="" class="logoutimg">--%>
    <%--</div>--%>
    <%--<div class="userinfo">--%>
      <%--<div class="text">--%>
        <%--<div class="username">--%>
          <%--调度员：张婷婷--%>
        <%--</div>--%>
        <%--<div id="show">时钟</div>--%>
      <%--</div>--%>
      <%--<div class="userimg">--%>
        <%--<img src="<%=request.getContextPath()%>/resources/cmd/image/hujihua.jpg" alt="" class="usericon" />--%>
      <%--</div>--%>
    <%--</div>--%>
    <%--<div class="nav">--%>
      <%--<a href="<%=request.getContextPath()%>/main/jump.cmd">首页</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.video">视频监控</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.guiji">历史轨迹</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.route" class="underline">线路预设</a>--%>
    <%--</div>--%>
 <%--</div>--%>

<div class="main">
  <div class="xianlu_list">
	<div class="xianlu_title"></div>
      <div id="listRP">
      </div>
  </div>
  <div class="xianlu_tools">
      <div class="xianlu_quyu" id="drawPolygon">区域设定</div>
      <div class="xianlu_sheding" id="drawPolyline">线路设定</div>
      <div class="xianlu_clear"onclick="allMap.graphics.clear();">清除</div>
  </div>
    <div id="mywin" class="easyui-window"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:450px;height:480px;padding:5px;">
        <div  style="width:95%;max-width:360px;padding:10px 20px;">
            <form id="f1" method="post">
                <div style="margin-bottom:20px">
                    <select class="easyui-combobox" name="rpType" id="rpType" label="类型:" labelPosition="left"
                            style="width:70%;" editable="false" required="true" validType="selectValueRequired['#rpType']">
                        <option value="">&nbsp;&nbsp;&nbsp;&nbsp;===请选择===</option>
                        <option value="1">航道路线</option>
                        <option value="2">区域边界</option>
                    </select>
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="rpLineName" id="rpLineName" style="width:100%" data-options="label:'名称:',required:true,validType:'maxLength[10]'">
                </div>
                <div style="margin-bottom:20px">
                    <select class="easyui-combobox" name="rpBelong" id="rpBelong" label="归属:" labelPosition="left"
                            style="width:70%;" editable="false" required="true" validType="selectValueRequired['#rpBelong']">
                        <option value="">&nbsp;&nbsp;&nbsp;&nbsp;===请选择===</option>
                        <option value="1">采沙航道</option>
                        <option value="2">采少区域</option>
                        <option value="2">执法航道</option>
                        <option value="2">执法区域</option>
                    </select>
                </div>
                <div style="margin-bottom:20px">
                    <select class="easyui-combobox" name="rpStatus" id="rpStatus" label="状态:" labelPosition="left"
                            style="width:70%;" editable="false" required="true" validType="selectValueRequired['#rpStatus']">
                        <option value="">&nbsp;&nbsp;&nbsp;&nbsp;===请选择===</option>
                        <option value="1">有效</option>
                        <option value="2">无效</option>
                    </select>
                </div>
                <div style="margin-bottom:10px">
                    <input class="easyui-textbox" name="rpPoints" id="rpPoints" style="width:100%;height:140px" data-options="label:'坐标集:',multiline:true" required="true">
                </div>
                <div style="margin-bottom:10px">
                    <input class="easyui-textbox" name="remark" style="width:100%;height:50px" data-options="label:'备注:',multiline:true,validType:'maxLength[200]'" required="true">
                </div>
            </form>
        </div>
        <div style="text-align:center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitF1()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearF1()" style="width:80px">清空</a>
        </div>
    </div>
  <div class="xianlu_map" id="map"></div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/route_plan.js"></script>
</html>