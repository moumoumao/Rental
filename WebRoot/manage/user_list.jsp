<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.css" type="text/css"></link>
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.css" type="text/css"></link>
<style type="text/css">
.myLable{
	padding-left: 10px;
}
</style>
<title>用户列表</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	findUser(1,5);
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        format:'yyyy-mm-dd',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
});
function findUser(pageNo,pageSize){
	
	$.ajax({
		url:'/Rental/user!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#userList').html('');
			if(data.aaData==''){
				str+="<tr><td colspan='8'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,obj){      
					str+="<tr><td>"+isNullStr(obj.userId)+" </td>"+"<td>"+isNullStr(obj.loginName)+" </td>"+
					"<td>"+isNullStr(obj.userName)+" </td>"+"<td>"+isNullStr(obj.loginFlag==1?"否":"是")+" </td>"+"<td>"+isNullStr(obj.userSex)+" </td>"+
					"<td>"+isNullStr(obj.createDate)+" </td>"+"<td>"+isNullStr(obj.lastDate)+" </td>"+"<td>"+
					"<a href='javascript:showUpdate("+obj.userId+")' title='分配'><span class='glyphicon glyphicon-cog'></span></a>  &nbsp;  "+
					" &nbsp;  <a href='javascript:blockUser("+obj.userId+","+obj.loginFlag+");' title='"+(obj.loginFlag==1?"屏蔽":"取消屏蔽")+"'><span class='glyphicon "+(obj.loginFlag==1?"glyphicon-ban-circle  red":"glyphicon-ok-circle green")+"'></span></a>"+
					" </td></tr>";
				 });
				str+="<tr><td colspan='8' algin='center'><ul class='pagination'><li><a href='javascript:findUser(1,"+data.pageSize+")'>&laquo;</a></li>";
				for(var i =1 ;i<=data.pageCount;i++){
					if(i==data.pageNo){
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

function showUpdate(){

}
/**
 * 屏蔽或解除屏蔽
 */
function blockUser(id,flag){
	layer.confirm('确定要'+(flag==1?'屏蔽':'取消屏蔽')+'这个用户吗？',{icon:4,title:'提示'},function(){
		$.ajax({
			url:'/Rental/user!updateLogonFlag.action',
			data:{'user.loginFlag':(flag==1)?2:1,'user.userId':id},
			type:'POST',
			dataType:'JSON',
			success:function(data){
				layer.msg(data.msg);
				if(data.resultCode==200){
					findUser(1,5);
				}
			}
		});
	});
	
}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">查询条件</h3>
  </div>
  <div class="panel-body">
   <table>
  	<tr>
  		<td class="myLable">  昵称：</td><td><input type="text" class="form-control"> &nbsp;</td>
  		<td class="myLable">  屏蔽：</td><td><select class="form-control"><option>请选择	</option><option>否</option><option>是</option></select></td>
  		<td class="myLable"> 性别：</td><td><select class="form-control"><option>请选择</option><option>男</option><option>女</option></select></td>
  		<td class="myLable">  审核级别：</td><td><select class="form-control"><option>请选择	</option><option>一级</option><option>二级</option></select></td>
  	</tr>
  	<tr>
  		<td class="myLable">创建时间：</td>
  		<td>
	  		 <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="startDate" data-link-format="yyyy-mm-dd">
			      <input class="form-control" size="16" type="text" value="" readonly required>
			      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			  </div>
			  <input type="hidden" id="startDate" name="room.buildDate"> 
  		</td>
  		<td class="myLable">  至 </td>
  		<td colspan="3">
	  		 <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="endDate" data-link-format="yyyy-mm-dd">
			      <input class="form-control" size="16" type="text" value="" readonly required>
			      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			  </div>
			  <input type="hidden" id="endDate" name="room.buildDate">
  		</td>
  		<td></td>
  		<td><button type="button" class="btn btn-default" onclick="findUser(1,5)"> <span class="glyphicon glyphicon-search"></span> 查询</button></td>
  	</tr>
  </table>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">用户管理</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>用户ID</th>
		            <th>登录名</th>
		            <th>昵称</th>
		            <th>屏蔽与否</th>
		            <th>性别</th>
		            <th>创建时间</th>
		            <th>最后登录时间</th>
		            <th >操作</th>
		        </tr>
		    </thead>
		    <tbody  id="userList">
			</tbody>
		</table>
  </div>
</div>
</body>
</html>
