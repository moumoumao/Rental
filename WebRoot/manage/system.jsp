<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.min.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/menu_list.css" type="text/css"></link>
<title>系统设置</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/my-modal.js"></script>
<script type="text/javascript">
</script>
<style type="text/css">
.barDiv{
	float: left;
}
.bar{
	margin-top: 55px;
}
</style>
<script type="text/javascript">
$(function(){
	var name = '${rental_user.userName }';
	addBar('bar',name);
});
</script>
  </head>
  
  <body>
  <div id="bar"></div>
  <div class="container bar">
 <div class="layout_left_menu">
			<ul id="menu" class="tendina">
				<li class="childUlLi">
					<a href="user_list.jsp" target="menuFrame2">
						<i class="glyphicon glyphicon-user"></i>用户列表
					</a>
				</li>
				<li class="childUlLi">
					<a href="role_list.jsp">
						<i class="glyphicon glyphicon-th-list"></i>角色管理
					</a>
				</li>
				<li class="childUlLi" data-toggle="collapse"  data-target="#sys">
					<a href="area_list.jsp" target="menuFrame2">
						<i class="glyphicon glyphicon-cog"></i> 区域管理
					</a>
				</li>
				<li class="childUlLi" data-toggle="collapse"  data-target="#sys">
					<a href="type_list.jsp" target="menuFrame2">
						<i class="glyphicon glyphicon-cog"></i> 房形管理
					</a>
				</li>
			</ul>
		</div>
		<div id="layout_right_content" class="layout_right_content">

			<div class="mian_content">
				<div id="page_content">
					<iframe id="menuFrame2" name="menuFrame2" src="main.jsp"
						style="overflow: visible;" scrolling="no" frameborder="no"
						height="600px" width="100%;"></iframe>
				</div>
			</div>
		</div>
</div>
</html>
