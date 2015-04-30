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
<link rel="stylesheet" href="../css/bootstrap-select.css" type="text/css"></link>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script src="/Rental/js/jquery.form.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.js"></script>
<style type="text/css">
.red{
	color: red;
}
.green{
	color: green;
}
.myLable{
	padding-left: 10px;
}
</style>
<title>审核流程</title>
<script type="text/javascript">
var currentNo=1;
$(function(){
	findUser(1,5);
});
function findUser(pageNo,pageSize){
	
	$.ajax({
		url:'/Rental/room!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo,
			'room.create.userId':'${rental_user.userId}'},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#userList').html('');
			if(data.aaData==''){
				str+="<tr><td colspan='6'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,obj){      
					str+="<tr><td>"+isNullStr(obj.roomId)+" </td>"+"<td>"+isNullStr(obj.roomTitle)+" </td>"+
					"<td>"+isNullStr(obj.checkState)+" </td>"+"<td>"+isNullStr(obj.create.userName)+" </td>"+
					"<td>"+isNullStr(obj.createDate)+" </td>"+"<td>"+
					"<a href='javascript:showRoom("+obj.roomId+")'><span class='glyphicon glyphicon-search green'></span></a>  &nbsp;  "+
					" &nbsp;  <a href='javascript:deleteRoom("+obj.roomId+");'><span class='glyphicon glyphicon-remove red'></span></a>"+
					" &nbsp;  <a href='javascript:showCheck("+obj.roomId+");'><span class='glyphicon glyphicon-eye-open green'></span></a>"+" </td></tr>";
				 });
				str+="<tr><td colspan='6' algin='center'><ul class='pagination'><li><a href='javascript:findUser(1,"+data.pageSize+")'>&laquo;</a></li>";
				for(var i =1 ;i<=data.pageCount;i++){
					if(i==data.pageNo){
						currentNo=i;
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


</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">房源信息</h3>
  </div>
  <div class="panel-body">
  <input type="hidden" value="${room.roomId }">
    <table class="table table-bordered">
		<tr><th>标题：</th><td colspan="3">${room.roomTitle }</td> </tr>	 
		<tr><th style="width: 90px;">创建者：</th><td >${room.create.userName }</td><th style="width: 110px;">创建时间：</th><td>${room.createDate }</td> </tr>	 
	</table>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">审核流程</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>序号</th>
		            <th>审核者</th>
		            <th>审核者等级</th>
		            <th>审核状态</th>
		            <th>审核原因</th>
		            <th>审核时间</th>
		        </tr>
		    </thead>
		    <tbody  id="userList">
			</tbody>
		</table>
  </div>
</div>
</body>
</html>
