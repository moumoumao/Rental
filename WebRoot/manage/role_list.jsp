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
.red{
	color: red;
}
.green{
	color: green;
}
</style>
<title>区域管理</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script type="text/javascript">
var currentPage=1;
$(function(){
	findUser(1,5);
});
function findUser(pageNo,pageSize){
	
	$.ajax({
		url:'/Rental/role!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo,
			'role.roleName':$('#roleName').val(),'role.roleContent':$('#roleDesc').val()},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#userList').html('');
			if(data.aaData==''){
				str+="<tr><td colspan='6'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,obj){      
					str+="<tr><td>"+isNullStr(obj.roleId)+" </td>"+"<td>"+isNullStr(obj.roleName)+" </td>"+
					"<td>"+isNullStr(obj.roleContent)+" </td>"+"<td>"+
					"<a href='role_detail.jsp?roleId="+obj.roleId+"'><span class='glyphicon glyphicon-pencil green'></span></a>  &nbsp;  "+
					" &nbsp;  <a href='javascript:deleteRole("+obj.roleId+");'><span class='glyphicon glyphicon-remove red'></span></a>"+" </td></tr>";
				 });
				str+="<tr><td colspan='6' algin='center'><ul class='pagination'><li><a href='javascript:findUser(1,"+data.pageSize+")'>&laquo;</a></li>";
				for(var i =1 ;i<=data.pageCount;i++){
					if(i==data.pageNo){
						currentPage = i;
						str+="<li class='active'><a href='javascript:findUser("+i+","+data.pageSize+")'>"+i+"</a></li>";
					}else{
						str+="<li ><a href='javascript:findUser("+i+","+data.pageSize+")'>"+i+"</a></li>";
					}
				}
				str+="<li><a href='javascript:findUser("+data.pageCount+","+data.pageSize+")'>&raquo;</a></li></ul></td></tr>";
			}
			$('#userList').append(str);
		}
	});
}
function isNullStr(val){
	return (val==null)?"":val;
}

function deleteRole(id){
	var ii = layer.load();
	$.ajax({
		url:'/Rental/role!deleteById.action',
		data:{'role.roleId':id},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			findUser(currentPage,5);
			layer.close(ii);
			layer.msg(data.msg);
			
		}
	});

}
function addrole(){
	window.location.href="role_create1.jsp";
}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">查询条件</h3>
  </div>
  <div class="panel-body">
  角色名：<input type="text" id="roleName"> &nbsp;简介：<input type="text" id="roleDesc"> 
  &nbsp;<button type="button" class="btn btn-default" onclick="findUser(1,5)"> <span class="glyphicon glyphicon-search"></span> 查询</button>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">
    	角色列表
    	<button class="btn btn-success btn-sm" style="float: right; margin-right:10px; margin-top: -5px;" onclick="addrole()">
		  <span class="glyphicon glyphicon-plus"></span>新增
		</button>
	</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>角色ID</th>
		            <th>角色名</th>
		            <th>角色简介</th>
		            <th >操作</th>
		        </tr>
		    </thead>
		    <tbody  id="userList">
			</tbody>
		</table>
  </div>
</div>
<!-- 新增 
<div class="panel panel-default" id="addDialog">
  <div class="panel-heading">
    <h3 class="panel-title">新增区域</h3>
  </div>
  <div class="panel-body">
  <table>
  	<tr>
  		<td>区域名称：</td>
  		<td><input type="text"> </td>
  	</tr>
  	<tr>
  		<td>区域简介：</td>
  		<td><input type="text"></td>
  	</tr>
  	<tr>
  		<td colspan="2"><button type="button" class="btn btn-success" onclick="findUser(1,5)"> <span class="glyphicon glyphicon-search"></span> 查询</button> </td>
  	</tr>
  </table>
  </div>
</div>
-->
</body>
</html>
