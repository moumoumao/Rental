<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/main.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/login.css" type="text/css"></link>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.css" type="text/css"></link>
<title>登录</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/jquery.form.js"></script>
<script src="/Rental/layer/layer.js"></script>
<style>
.center{
	width:60%;
	margin: auto;
}
</style>

<script type="text/javascript">

$(function(){
	$('#sub').click(function(){
		var ii = layer.load();
		$('#subForm').ajaxSubmit({
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
                layer.close(ii);
                layer.msg(data.msg);
                if(data.resultCode==200){
                    window.location.href="index.jsp";
                }
                $(this).resetForm(); // 提交后重置表单
            }
            
            });
        return false; // 阻止表单自动提交事件
    
	});
});
</script>
</head>
<body>
<div id="container">
	<div class="log-title" ><div onclick="javascript:window.location.href='index.jsp'">重庆租房</div></div>
	<div id="content" >
			<div class="show_div" style="border: 1px solid #E3E3E3; ">
				<div class="panel panel-default">
   				<div class="panel-body opt">
				<div class="wel-login-title">欢迎登录</div>
				 <br>
					<div style="margin: 10px auto ; width: 60%;">
				   		<form class="bs-example bs-example-form" role="form"  action="user!doLogin.action" method="post" id="subForm">
				   		
						      <div class="input-group">
						         <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
						         <input type="text" class="form-control" placeholder="用户名"  name="user.loginName" >
						      </div>
					      	  <br>
					     	  <div class="input-group">
								  <span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span></span>
								  <input type="password" class="form-control" placeholder="密码"  name="user.userPwd" >
							  </div>
							  <br>
							   <br>
					      	<div  style="text-align: center;">
					      	<a type="button" class="btn btn-primary" href="regist.jsp" >注册</a>
					      	<button type="button" class="btn btn-success" id="sub">登录</button>
					       </div>
					   </form>
			   		</div>
				</div>
	        </div>
	        </div>
		</div>
	
</div>
	
</body>
</html>