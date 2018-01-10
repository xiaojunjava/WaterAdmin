<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>指挥调度</title>
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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/default.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/dijit/themes/nihilo/nihilo.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/esri/css/esri.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css" />

  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery.nicescroll.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/utils.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/arcgisapi/3.14/init.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/map.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/alertlist.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/main/layer.control.js"></script>

    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>

<script type="text/javascript">
    $(function () {
        $.getScript("<%=request.getContextPath()%>/resources/cmd/js/default.js").done(function() {
            showtime();
//            loadcss();
        });
        listShipAlarm();
    });
</script>
</head>
<body onload=" onLoad();">
  <div class="header">
    <div class="logo">
      <img src="<%=request.getContextPath()%>/resources/cmd/image/zhdd_logo.png" alt="">
    </div>
    <div class="logout">
        <a href="<%=request.getContextPath()%>/jump.exit" ><img src="<%=request.getContextPath()%>/resources/cmd/image/logout.png" alt="" class="logoutimg"></a>
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
      <a href="<%=request.getContextPath()%>/main/jump.cmd" class="underline">首页</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.zhdd">指挥调度</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.csjg">采沙监管</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.jkgl">监控管理</a>
    </div>
  </div>
  <div class="leftlisticon" id="leftlisticon" onclick="menu('leftopen');">
    <img src="<%=request.getContextPath()%>/resources/cmd/image/menu.png" alt="">
  </div>
  <%--<div class="rightlisticon" id="rightlisticon" onclick="menu('rightopen');">--%>
    <%--<img src="<%=request.getContextPath()%>/resources/cmd/image/menu.png" alt="">--%>
  <%--</div>--%>
  <div class="main">

    <div class="map"  id="map">

    </div>
    <div class="leftlist" id="leftlist">
      <div class="listtitle">
        <span>报警列表</span>
        <img src="<%=request.getContextPath()%>/resources/cmd/image/menu.png" onclick="menu('leftclose');" alt="">
      </div>
      <div class="" id="boxscroll4">
        <div class="listcontent wrapper" id="ship_alarm_list"></div>
      </div>
    </div>
    <%--<div class="rightlist" id="rightlist">--%>
      <%--<div class="listtitle">--%>
        <%--<span>车船列表</span>--%>
        <%--<img src="<%=request.getContextPath()%>/resources/cmd/image/menu.png" onclick="menu('rightclose');" alt="">--%>
      <%--</div>--%>
      <%--<!-- NOTE: 滚动 -->--%>
      <%--<div class="" id="boxscroll2">--%>
        <%--<div class="listcontent wrapper rightlistcontent" id="sa_list">--%>
          <%--<!-- NOTE: 卡片开始 -->--%>
          <%--<!-- NOTE: 循环结束 -->--%>
          <%--<p>&nbsp;</p>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
  </div>

  <div class="righttools">
    <div class="tags">
      <%--<a href="#" class="hoverrf" id="yunsha">运砂船</a>&nbsp;&nbsp;|--%>
      <%--<a href="#" class="hoverrf" id="caisha">采砂船</a>&nbsp;&nbsp;|--%>
      <a href="#" class="hoverrf" id="zhifa">执法船</a>|
      <a href="#" class="hoverrf" id="che">执法车</a>|
      <a href="#" class="hoverrf" id="ren">执法人</a>|
      <a href="#" class="hoverrf" id="shexiang">摄像头</a>
    </div>
    <div class="clear">
      <img src="<%=request.getContextPath()%>/resources/cmd/image/clear.png" alt="清空"><br /> 清空
    </div>
  </div>
  <div id="mywin" class="easyui-window"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:450px;height:300px;padding:5px;">
      <div  style="width:95%;max-width:360px;padding:10px 20px;">
          <form id="f1" method="post">
              <div style="margin-bottom:20px">
                  <select class="easyui-combobox" name="iiPriorityLevel" id="iiPriorityLevel" label="紧急程度:" labelPosition="left"
                          style="width:70%;" editable="false" required="true" validType="selectValueRequired['#iiPriorityLevel']">
                      <option value="">&nbsp;&nbsp;&nbsp;&nbsp;===请选择===</option>
                      <option value="1">1级</option>
                      <option value="2">2级</option>
                      <option value="3">3级</option>
                      <option value="4">4级</option>
                  </select>
              </div>
              <div style="margin-bottom:10px">
                  <input class="easyui-textbox" name="iiContent" style="width:100%;height:140px" data-options="label:'指令内容:',multiline:true,validType:'maxLength[200]'" required="true">
                  <input type="hidden" id="saId" name="saId" />
              </div>
          </form>
      </div>
      <div style="text-align:center;">
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitF1()" style="width:80px">提交</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearF1()" style="width:80px">清空</a>
      </div>
  </div>
  <div class="zoom right_hide_zoom" id="zoom">
    <div class="plus" id="zoomOut">
    </div>
    <div class="mini" id="zoomIn">
    </div>
  </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/zhdd.js"></script>
</html>
