<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.css" type="text/css"></link>
<style type="text/css">
.view{
	margin-top:10px;
	margin-left:20px;
	padding:15px;
	float:left;
	font-size: 16px;
	 border-radius:30px;
}
a:hover,
a:active,
a:focus{
	text-decoration: none;
	outline: none;
}
</style>
<title>区域管理</title>
<script src="/Rental/js/jquery-1.8.2.js"></script>
<script src="/Rental/js/bootstrap.js"></script>
<script src="/Rental/js/bootstrap-treeview.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	  var roleId=<%=request.getParameter("roleId").toString() %>;
	  $.ajax({
		  type:'POST',
		  dataType:'JSON',
		  url:'/Rental/role!findById.action',
		  data:{'role.roleId':roleId},
		  success:function(data){
		  	if(data.resultCode=='200'){
			  	var obj = data.data;
			  	$('#roleId').val(obj.roleId);
			  	$('#roleName').val(obj.roleName);
			  	$('#roleContent').html(obj.roleContent);
			  	$.each(obj.powerSet,function(i,item){
			  		$('#event_output').append('<span class="label label-success view" id="'+item.fun.funName+'">'+item.fun.funName+'</span>'+
		      	        	  '<input type="hidden" value="'+item.fun.funId+'" name="funList"/>');
			  	});
			  	
		  	}
	  	  }
	  });
      $.ajax({
		type:'POST',
		dataType:'JSON',
		url:'/Rental/role!findAllFun.action',
		success:function(data){
  		 if(data.resultCode=='200'){
  	  		$('#tree').treeview({
  	          expandIcon: "glyphicon glyphicon-stop",
  	          collapseIcon: "glyphicon glyphicon-unchecked",
  	          nodeIcon: "glyphicon glyphicon-user",
  	          color: "#428bca",
  	          data: data.data,
  	          onNodeSelected: function(event, node) {
    	          
    	          if(node.nodes==null){
        	          if($('#'+node.text).length<=0){
        	        	  $('#event_output').append('<span class="label label-success view" id="'+node.text+'">'+node.text+'</span>'+
                	        	  '<input type="hidden" value="'+node.tags+'" name="funList"/>');
        	          }else{
        	        	  layer.msg('该权限已经添加！');
        	          }
    	          }      
  	          }
  	        });
  		 }
      	}
       });
      $('.view').live('dblclick',function(){
          $(this).remove();
      });
      $('#reset').click(function(){
    	 window.location.href="javascript:history.go(-1);"
      });
});
function addForm(){
	 var params = $("#form").serialize();
	 var ii = layer.load();
	 $.ajax({
         type: 'POST',
         url:'/Rental/role!updateRole.action',
         dataType:'JSON',
         data:params,
         success: function(data){
			 layer.close(ii);
			 layer.msg(data.msg);
             if(data.resultCode=='200'){
            	 window.location.href="role_list.jsp";
             }
         }
       });
     return false;
} 
</script>
</head>
<body>
<form id="form"  role="form" method="post" onsubmit="return addForm()">

<div class="panel panel-default">
  <div class="panel-heading">角色基本信息</div>
  <div class="panel-body">
  <input type="hidden" id="roleId" name="role.roleId"/>
	 <table class="table">
		 <tr>
		 <th style="width: 70px;">名称：</th>
		 <td><input class="form-control" required name="role.roleName" id="roleName"> </td>
		 </tr>
		 <tr>
		 <th>简介 :</th>
		 <td><textarea class="form-control" required name="role.roleContent" id="roleContent"></textarea> </td>
		 </tr>
	 </table>
  </div>
</div>
<div class="panel panel-default" >
  <div class="panel-heading">
  	<a data-toggle="collapse" data-parent="#accordion" href="#power"> 
	   	  <h4 class="panel-title"> 权限详情</h4></a></div>
  <div class="panel-body panel-collapse collapse" id="power">
    <div id="tree" style="float: left;width: 20%;margin-right: 10px;"></div> 
 <div id="event_output"  style="float: left;width: 75%;height: 90%;padding: 10px;height: 300px;" class="panel panel-default">
 
 </div>
  </div>
</div>
  <div style="text-align: center;">
  <button type="button" class="btn btn-default auto" id="reset" >返回</button>
	<button type="submit"  class="btn btn-success" >提交</button>
  </div>
</form>
</html>
