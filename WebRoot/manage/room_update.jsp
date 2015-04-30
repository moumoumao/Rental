<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.min.css" type="text/css"></link>
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.css" type="text/css"></link>
<title>修改房源</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.js"></script>
<script src="/Rental/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/Rental/js/jquery.form.js"></script>
<script src="/Rental/layer/layer.js"></script>
<script src="/Rental/js/my-modal.js"></script>

<style type="text/css">
.img_div{
	width: 150px;
	height: 150px;
	border: 1px solid #dedede;
	float: left;
	margin-left: 5px;
}
.add_img{
	margin-top: 50px;
	margin-left: 62px;
	font-size: 25px;
}
a:hover,
a:active,
a:focus{
	text-decoration: none;
	outline: none;
}
.num{
width: 60px;
margin-right: 20px;
margin-left: 20px;
}
.myimg{
width:100%;
height: 100%;
border: 0px;
}
.auto{
margin-right: 30px;
}
.bar{
	margin-top: 55px;
}
</style>
<script type="text/javascript">
var img_len=0;
$(function(){
	var name = '${rental_user.userName }';
	addBar('bar',name);
	//var roomId = '${roomId }';
	//根据Id查找
	/*$.ajax({
		type:'POST',
		dataType:'JSON',
		data:{'room.roomId':roomId},
		url:'/Rental/room!findById.action',
		success:function(data){
			if(data.resultCode=='200'){
				$.each(data.data,function(i,obj){
					$('#type').append('<option value="'+obj.typeId+'">'+obj.typeName+'</option>');	
				});
			}
		}
	});
		
	
	$('#tot,#now').change(function(){
		$('#floor').val($('#now').val()+'/'+$('#tot').val());
	});
	$(".form-control,select,div").popover({trigger: 'manual',placement:'top'});
	*/
});
function uploadImg(type){
	$('#'+type).ajaxSubmit({
		dataType:'JSON',
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            // 此处可对 data 作相关处理
            if(data.resultCode=='200'){
                if(type=='outForm'){
                	$('#out_img').before("<div class='img_div'><input type='image' name='roomInside' src='/Rental/upload/room/"+data.data+"' value='/upload/room/"+data.data+"' class='img-thumbnail myimg'></div>");
                }else{
                	$('#in_img').before("<div class='img_div'><input type='image' name='roomOutside' src='/Rental/upload/room/"+data.data+"' value='/upload/room/"+data.data+"' class='img-thumbnail myimg'></div>");
                }
                
            }
            $(this).resetForm(); // 提交后重置表单
			return false; // 阻止表单自动提交事件
		}
	});  
}
function addForm(){
	
	if($('#area').val()!='-1'&&$('#type').val()!='-1'&&$('#rentType').val()!='-1'&&$('#buildDate').val()!=''){
		 var params = $("#addForm").serialize();
		 $.ajax({
             type: 'POST',
             url: 'room!addRoom.action',
             dataType:'JSON',
             data: params,
             success: function(data){
				 alert(data.msg);
	             if(data.resultCode=='200'){
	            	 $('.roomId').val(data.data.roomId);
	            	 var com = layer.confirm('房源已添加成功', {
	 	         	    btn: ['添加图片','继续添加房源',]//按钮
	 	         	}, function(){
	 	         		layer.close(com);
	 	         		$('#imgDiv').show();
	 	         		layer.open({
	 	         		    type: 1,
	 	         		    skin: 'layui-layer-molv', //样式类名
	 	         		    area: ['auto', 'auto'], //宽高
	 	         		    shift: 2,
	 	         		    content: $('#imgDiv')
	 	         		});
	 	         	}, function(){
	 	         		$('#reset').click();
	 	         	});
	             }
             }
           });
		
	}
	return false;
}
</script>
  </head>
  
  <body id="accordion">
  <div id="bar"></div>
  <div class="container bar">
<form role="form"  id="addForm" onsubmit="return addForm()">

  <div class="panel panel-info">
  <div class="panel-heading">
	   	<a data-toggle="collapse" data-parent="#accordion" href="#base"> 
	   	  <h4 class="panel-title"> 基本信息</h4></a>
</div>
  <div class="panel-body panel-collapse collapse in" id="base">
    <table  class="table table-bordered">
    	<tr>
		   <th>标题:</th>
		   <td colspan="5"><input type="text" class="form-control" name="room.roomTitle" required value="${room.roomId }"> </td>
		 </tr>
		 <tr>
		   <th>小区名:</th>
		   <td colspan="3"><input type="text" class="form-control"  name="room.compounds" required value="${room.compounds }"> </td>
		   <th>区域:</th>
		   <td>
		   ${room.area.areaName }
		   	<select class="form-control" id="area" name="room.area.areaId"  ><option value="-1">--请选择--</option></select>
		   </td>
		 </tr>
		 <tr>
		   <th>详细地址:</th>
		   <td colspan="3"><input type="text" class="form-control" name="room.address" required value="${room.address }"> </td>
		   <th>房屋类型:</th>
		   <td>
		   ${room.type.typeName }
		   	<select class="form-control" id="type" name="room.type.typeId" required><option value="-1">--请选择--</option></select>
		   </td>
		 </tr>
		 <tr>
		   <th>户型:</th>
		   <td colspan="3"><input type="number"  min="0"  class="num" name="room.roomNum" required value="${room.roomNum }">室
		   <input type="number"  min="0" class="num" name="room.hallNum" required value="${room.hallNum }"> 厅 
		   <input type="number"  min="0" class="num" name="room.kitchenNum" required value="${room.kitchenNum }">厨
		   <input type="number"  min="0"  class="num" name="room.toiletNum" required value="${room.toiletNum }">卫 </td>
		   <th>楼层:</th>
		   <td>
		   ${room.floor }
		   	<input type="number" class="num" id="now" required="required" min="1">层 /共
		   	<input type="number" class="num" id="tot"  min="1" required>层
		   	<input id="floor" name="room.floor" type="hidden">
			</td>
		</tr>
		 <tr>
		   <th>租金:</th>
		   <td><input type="text" name="room.price" type="number" required value="${room.price }"> 元/月</td>
		   <td colspan="2"></td>
		   <th>出租类型:</th>
		   <td>${room.rentType }
		    	<select class="form-control" name="room.rentType" id="rentType">
		    		<option value="-1">--请选择--</option>
		    		<option value="1">整租</option>
		    		<option value="2">合租</option>
		    	</select>
		   </td>
		 </tr>
		  <tr>
		  <th>面积:</th>
		   <td><input type="text" name="room.roomArea" required value="${room.roomArea }"> ㎡</td>
		   <td style="width: 20%;" colspan="2"></td>
		   <th>创建时间:</th>
		   <td>${room.buildDate }
		    	<div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="buildDate" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="buildDate" name="room.buildDate">
		   </td>
		 </tr>
		 <tr>
		   <th>描述:</th>
		   <td colspan="5"> ${room.roomContent }<textarea class="form-control" name="room.roomContent" required ></textarea> </td>
		 </tr>
		 <tr>
		   <th>联系人:</th>
		  
		   <td><input class="form-control" name="room.userName" required value="${room.userName }"> </td>
		   <td colspan="2"></td>
		   <th>联系电话:</th>
		   <td><input type="text" class="form-control" name="room.phone" required value="${room.phone }"> </textarea> </td>
		 </tr>
    </table>
  </div>
</div>

  <div style="text-align: center;">
  <button type="reset" class="btn btn-default auto" id="reset" >重置</button>
	<button type="submit" class="btn btn-success">提交</button>
  </div>
 </form>
 
  <div class="panel panel-info">
	  <div class="panel-heading">
		   	<a data-toggle="collapse" data-parent="#accordion" href="#base"> 
		   	  <h4 class="panel-title"> 室内图片</h4></a>
	  </div>
	  <div class="panel-body panel-collapse collapse in" id="base">
	  </div>
  </div>
  
   <div class="panel panel-info">
	  <div class="panel-heading">
		   	<a data-toggle="collapse" data-parent="#accordion" href="#base"> 
		   	  <h4 class="panel-title"> 室外图片</h4></a>
	  </div>
	  <div class="panel-body panel-collapse collapse in" id="base">
	  </div>
  </div>
     </body>
</html>
