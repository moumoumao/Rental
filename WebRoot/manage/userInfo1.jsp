<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.min.css" type="text/css"></link>
<title>个人中心</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/my-modal.js"></script>
<style type="text/css">
#img_div{
width:100%;
 text-align: center;
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
  <div id="img_div">  <img src="../img/img1.png" 
   class="img-circle" style="width: 100px;height: 100px;"></div>
   <ul id="myTab" class="nav nav-tabs">
	   <li class="active"><a href="#base" data-toggle="tab"> 基本信息</a></li>
	   <li><a href="#house" data-toggle="tab">发布的房源</a></li>
	   <li class="dropdown">
	      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
	         data-toggle="dropdown">
	         Java <b class="caret"></b></a>
	      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
	         <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
	         <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
	      </ul>
	   </li>
	</ul>
   <div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="base">
      <table class="table table-bordered" style="margin-top: 5px;">
		   <tbody>
		      <tr>
		         <th>用户名:</th>
		         <td>${rental_user.loginName } </td>
		         <th>昵称:</th>
		         <td>${rental_user.userName } </td>
		      </tr>
		      <tr>
		         <th>手机:</th>
		         <td>${rental_user.phone } </td>
		         <th>邮箱地址:</th>
		         <td>${rental_user.phone  } </td>
		      </tr>
		      <tr>
		         <th>年龄:</th>
		         <td>${rental_user.userSex } </td>
		         <th>出生年月:</th>
		         <td>${rental_user.birth} </td>
		      </tr>
		      <tr>
		         <th>性别:</th>
		         <td>${rental_user.userSex} </td>
		         <th>最后登录:</th>
		         <td>${rental_user.lastDate } </td>
		      </tr>
		   </tbody>
		</table>
   </div>
   <div class="tab-pane fade" id="house">房源</div>

</div>

   
  </body>
</html>



