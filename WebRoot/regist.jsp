<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="/Rental/css/main.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/login.css" type="text/css"></link>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/bootstrap-theme.min.css" type="text/css"></link>
<title>注册</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/jquery.form.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$('#sub').click(function(){
		if(!checkNull()){
			return false; 
		}else{
		var ii = layer.load();
		$('#subForm').ajaxSubmit({
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
                layer.close(ii);
                layer.msg(data.msg);
                if(data.resultCode==200){
                    window.location.href="login.jsp";
                }
                $(this).resetForm(); // 提交后重置表单
            }
            
            });
        return false; // 阻止表单自动提交事件
		}
	});
	$(".form-control").blur(function(){
		checkNull();
	});
});
/**
 * 验证字段是否为空
 */
function checkNull(){
	 var name = $('#name').val();
	 var pwd = $('#pwd').val();
	 var repwd =$('#repwd').val();
	 if(name==''){
		 layer.tips('用户名不能为空', '#name');
		 return false;
	 }
	 $.ajax({
			url:'user!isExit.action',
			data:{'user.loginName':$('#name').val()},
			async: false,
         	type: "POST",
         	dataType: "JSON",
			success:function(data){
				layer.tips(data.msg, '#name');
				if(data.resultCode==200){
				}else{
					return false;
				}
					
			}
	});
	 if(pwd==''){
		 layer.tips('密码不能为空', '#pwd');
		 return false;
	 }
	 if(repwd!=pwd){
		 layer.tips('两次密码输入不一致！', '#repwd');
		 return false;
	 }
	 return true;
}
</script>
</head>
<body>                   
<div id="container">
	<div class="log-title" ><div onclick="javascript:window.location.href='index.jsp'">重庆租房</div></div>
	<div id="content">
		<div class="show_div">
        
        <div class="panel panel-default">
   		<div class="panel-body opt">
   		 <div class="wel-login-title">欢迎注册</div>
   		<div style="margin: 10px auto ; width: 60%;">
	   		<form class="bs-example bs-example-form" role="form"  action="user!saveUser.action" method="post" id="subForm">
			      <div class="input-group">
			         <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
			         <input type="text" class="form-control" name="user.loginName" id="name" placeholder="用户名">
			      </div>
		      	  <br>
		     	  <div class="input-group">
					  <span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" class="form-control"  id="pwd" placeholder="密码"  name="user.userPwd" >
				  </div>
				  <br>
				   <div class="input-group">
					  <span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" class="form-control" id="repwd" placeholder="确认密码">
				  </div>
				  <br/>
		      	<div  style="text-align: center;">
		      	<a type="button" class="btn btn-primary" href="login.jsp" >登录</a>
		      	<button type="button" class="btn btn-success" id="sub">注册</button>
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