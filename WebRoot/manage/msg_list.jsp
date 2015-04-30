<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.css" type="text/css"></link>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script src="/Rental/js/jquery.form.js"></script>
<style type="text/css">
</style>
<title>我的消息</title>
<script type="text/javascript">
var currentNo=1;
$(function(){
	findMsg(1,5);
	layer.config({
	    extend: '../layer/extend/layer.ext.js'
	}); 
});
function findMsg(pageNo,pageSize){
	var send=receive=0;
	var receiveShow=sendShow=0;
	var userId='${rental_user.userId}';
	if($('#type').val()==1){
		send=userId;
		sendShow=1;
	}else if($('#type').val()==2){
		receive=userId;
		receiveShow=1;
	}
	$.ajax({
		url:'/Rental/msg!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo,
			'message.mesTitle':$('#title').val(),'message.mesContent':$('#content').val(),
			'message.send.userId':send,'message.receive.userId':receive,'order':'asc',
			'message.receiveShow':receiveShow,'message.sendShow':sendShow},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#msgList').html('');
			if(data.aaData==''){
				str+="<tr><td colspan='6'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,obj){      
					str+="<tr><td>"+isNullStr(obj.mesId)+" </td>"+"<td>"+isNullStr(obj.mesTitle)+" </td>"+
					"<td>"+isNullStr(obj.send.userName)+" </td>"+
					"<td>"+isNullStr(obj.receive.userName)+" </td>"+
					"<td>"+isNullStr(obj.createDate)+" </td>"+"<td>"+
					"<a href='javascript:showMsg("+obj.mesId+",\""+obj.mesTitle+"\",\""+obj.mesContent+"\")'><span class='glyphicon glyphicon-search green'></span></a>  &nbsp;  ";
					var type=(obj.send.userId==userId)?1:2;
					str+=" &nbsp; <a href='javascript:deleteMsg("+obj.mesId+","+type+");'><span class='glyphicon glyphicon-remove red'></span></a>"+" </td></tr>";
				 });
				str+="<tr><td colspan='6' algin='center'><ul class='pagination'><li><a href='javascript:findMsg(1,"+data.pageSize+")'>&laquo;</a></li>";
				for(var i =1 ;i<=data.pageCount;i++){
					if(i==data.pageNo){
						currentNo=i;
						str+="<li class='active'><a href='javascript:findMsg("+i+","+data.pageSize+")'>"+i+"</a></li>";
					}else{
						str+="<li ><a href='javascript:findMsg("+i+","+data.pageSize+")'>"+i+"</a></li>";
					}
				}
				str+="<li><a href='javascript:findMsg("+data.pageCount+","+data.pageSize+")'>&raquo;</a></li></ul></td></tr>";
			}
			$('#msgList').append(str);
		}
	});
}
function isNullStr(val){
	return (val==null)?"":val;
}

function deleteMsg(id,type){
	var url=(type==1)?'/Rental/msg!deleteBySender.action':'/Rental/msg!deleteByReceive.action';
	layer.confirm('确定要删除这条消息吗？',{icon:4,title:'提示'},function(){
		var ii = layer.load('加载中');
		$.ajax({
			url:url,
			data:{'message.mesId':id},
			type:'POST',
			dataType:'JSON',
			success:function(data){
				layer.close(ii);
				layer.msg(data.msg);
				if(data.resultCode==200){
		             	findMsg(1,5);
		        }
				
			}
		});
	});
	

}
function showMsg(id,title,desc){
	$('#modalTitle').html('消息详情');
	$('#msgtitle').val(title);
	$('#desc').val(desc);
	$('#myModal').modal();
	
}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">查询条件</h3>
  </div>
  <div class="panel-body">
  标题：<input type="text" id="title"> &nbsp;内容：<input type="text" id="content"> &nbsp;
  类型：<select id="type"><option value="1">已发送</option><option value="2">已接收</option> </select> 
  &nbsp;<button type="button" class="btn btn-default" onclick="findMsg(1,5)"> <span class="glyphicon glyphicon-search"></span> 查询</button>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">
    	消息列表
	</h3>
  </div>
  <div class="panel-body">
  		<table class="table table-bordered">
			<thead>
				 <tr>
				  	<th>消息ID</th>
				    <th>标题</th>
				    <th>发送者</th>
				    <th>接受者</th>
				    <th>创建时间</th>
				    <th >操作</th>
				</tr>
			</thead>
			<tbody  id="msgList">
			</tbody>
		</table>
    
  </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title">消息详情</h4>
         </div>
         <div class="modal-body" id="body">
          <table class="table">
		  	<tr>
		  		<td>标题：</td>
		  		<td><input type="text" class="form-control" id="msgtitle" readonly="readonly"> </td>
		  	</tr>
		  	<tr>
		  		<td>内容：</td>
		  		<td><textarea type="text" class="form-control" id="desc"  readonly="readonly"></textarea></td>
		  	</tr>
		  </table>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->

</body>
</html>
