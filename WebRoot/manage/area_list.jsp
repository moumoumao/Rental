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
.red{
	color: red;
}
.green{
	color: green;
}
</style>
<title>区域管理</title>
<script type="text/javascript">
var currentNo=1;
$(function(){
	findUser(1,5);
	layer.config({
	    extend: '../layer/extend/layer.ext.js'
	}); 
});
function findUser(pageNo,pageSize){
	
	$.ajax({
		url:'/Rental/area!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo,
			'area.areaName':$('#areaName').val(),'area.describe':$('#areaDesc').val()},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#userList').html('');
			if(data.aaData==''){
				str+="<tr><td colspan='6'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,obj){      
					str+="<tr><td>"+isNullStr(obj.areaId)+" </td>"+"<td>"+isNullStr(obj.areaName)+" </td>"+
					"<td>"+isNullStr(obj.describe)+" </td>"+"<td>"+isNullStr(obj.create.userName)+" </td>"+
					"<td>"+isNullStr(obj.createDate)+" </td>"+"<td>"+
					"<a href='javascript:updateArea("+obj.areaId+",\""+obj.areaName+"\",\""+obj.describe+"\")'><span class='glyphicon glyphicon-pencil green'></span></a>  &nbsp;  "+
					" &nbsp;  <a href='javascript:deleteArea("+obj.areaId+");'><span class='glyphicon glyphicon-remove red'></span></a>"+" </td></tr>";
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

function deleteArea(id){
	layer.confirm('确定要删除这条数据吗？',{icon:4,title:'提示'},function(){
		var ii = layer.load('加载中');
		$.ajax({
			url:'/Rental/area!deleteById.action',
			data:{'area.areaId':id},
			type:'POST',
			dataType:'JSON',
			success:function(data){
				layer.close(ii);
				layer.msg(data.msg);
				if(data.resultCode==200){
		             	findUser(1,5);
		        }
				
			}
		});
	});
	

}
function addArea(){
	$('#modalTitle').html('新增区域');
	$('#name').val('');
	$('#desc').val('');
	$('#areaId').val('');
	$('#myModal').modal();
}
function updateArea(id,name,desc){
	$('#modalTitle').html('修改区域');
	$('#name').val(name);
	$('#desc').val(desc);
	$('#areaId').val(id);
	$('#myModal').modal();
	
}
function subForm(){
	var title = $('#modalTitle').html();
	if(title=='修改区域'){
		$('#form').attr('action','/Rental/area!updateInfo.action');
	}else{
		$('#form').attr('action','/Rental/area!addArea.action');
	}
	var ii = layer.load();
	$('#form').ajaxSubmit({
           success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
           layer.close(ii);
           layer.msg(data.msg);
           if(data.resultCode==200){
             	findUser(1,5);
             	$('#myModal').modal('hide');
            }
            $(this).resetForm(); // 提交后重置表单
       }
            
      });
    return false; // 阻止表单自动提交事件
    
}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">查询条件</h3>
  </div>
  <div class="panel-body">
  区域名：<input type="text" id="areaName"> &nbsp;简介：<input type="text" id="areaDesc"> 
  &nbsp;<button type="button" class="btn btn-default" onclick="findUser(1,5)"> <span class="glyphicon glyphicon-search"></span> 查询</button>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">
    	区域列表
    	<button type="button" class="btn btn-success btn-sm" style="float: right; margin-right:10px; margin-top: -5px;" onclick="addArea()">
		  <span class="glyphicon glyphicon-plus"></span>新增
		</button>
	</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>区域ID</th>
		            <th>区域名</th>
		            <th>区域简介</th>
		            <th>创建者</th>
		            <th>创建时间</th>
		            <th >操作</th>
		        </tr>
		    </thead>
		    <tbody  id="userList">
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
            <h4 class="modal-title" id="modalTitle"></h4>
         </div>
         <div class="modal-body" id="body">
         <form action="/Rental/area!updateInfo.action" role="form" method="post" id="form">
         <input type="hidden" name="area.areaId" id="areaId">
          <table class="table">
		  	<tr>
		  		<td>区域名称：</td>
		  		<td><input type="text" class="form-control" id="name" name="area.areaName"> </td>
		  	</tr>
		  	<tr>
		  		<td>区域简介：</td>
		  		<td><textarea type="text" class="form-control" id="desc" name="area.describe"></textarea></td>
		  	</tr>
		  </table>
         </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="subForm();">
               确定
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->

</body>
</html>
