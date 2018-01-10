<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>视频监控</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cmd/css/video.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/layui/layui.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/cmd/js/video.js"></script>
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
        <a href="<%=request.getContextPath()%>/main/jump.cmd">首页</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.zhdd">指挥调度</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.csjg">采沙监管</a><div>|</div><a href="<%=request.getContextPath()%>/main/jump.cmd.jkgl"  class="underline">监控管理</a>
    </div>
  </div>
<div class="main">
  <table>
	<tbody>
		<tr>
			<td><h2><div>&nbsp;</div><span>无人机监控</span></h2></td>
			<td class="right_line"><h2></h2></td>
			<td><h2><div>&nbsp;</div><span>长江云监控</span></h2></td>
			<td><h2></h2></td>
			<td><h2></h2></td>
		</tr>
		<tr>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1wurenji.PNG" /><h4>1号无人机</h4></td>
			<td class="right_line"><image src="<%=request.getContextPath()%>/resources/cmd/image/no2wurenji.png" /><h4>2号无人机</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1yunjiankong.png" /><h4>1号云监控</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no2yunjiankong.png" /><h4>2号云监控</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no3yunjiankong.png" /><h4>3号云监控</h4></td>
		</tr>
		<tr>
			<td colspan="5">
				<div class="bottom_line1"></div>
			</td>
		</tr>
		<tr>
			<td><h2><div>&nbsp;</div><span>执法仪监控</span></h2></td>
			<td class="right_line"><h2></h2></td>
			<td><h2><div>&nbsp;</div><span>办公楼监控</span></h2></td>
			<td><h2></h2></td>
			<td><h2></h2></td>
		</tr>
		<tr>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1zhifayi.png" /><h4>1号执法仪</h4></td>
			<td class="right_line"><image src="<%=request.getContextPath()%>/resources/cmd/image/no1zhifayi.png" /><h4>2号执法仪</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>1号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>2号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>3号办公楼</h4></td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="bottom_line2"></div>
			</td>
			<td colspan="3">
				
			</td>
		</tr>
		<tr>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>4号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>5号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>6号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>7号办公楼</h4></td>
			<td><image src="<%=request.getContextPath()%>/resources/cmd/image/no1bangonglou.png" /><h4>8号办公楼</h4></td>
		</tr>
	</tbody>
  </table>
</div>
</body>
</html>