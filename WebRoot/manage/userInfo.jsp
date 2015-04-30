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
   <div id="myTabContent" class="tab-content">
      <table class="table table-bordered" style="margin-top: 5px;">
		   <tbody>
		      <tr>
		         <th>用户名:</th>
		         <td>${rental_user.loginName } </td>
		         <th>昵称:</th>
		         <td>${rental_user.userName } </td>
		      </tr>
		      <tr>
		         <th  class="success">手机:</th>
		         <td>${rental_user.phone } </td>
		         <th>邮箱地址:</th>
		         <td>${rental_user.phone  } </td>
		      </tr>
		      <tr>
		         <th class="active">年龄:</th>
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

   
  </body>
</html>



