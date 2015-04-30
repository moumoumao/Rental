<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.min.css" type="text/css"></link>
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.css" type="text/css"></link>
<title>房源详情</title>
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
	$('#back').click(function(){
		javascript:history.go(-1);
	});
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
</script>
  </head>
  
  <body id="accordion">
  <div id="bar"></div>
  <div class="container bar">
	  <div class="panel panel-success">
		  <div class="panel-body">
			    <div style="text-align: center;">
			    <button type="button" class="btn btn-default " id="back" >
				  <span class="glyphicon glyphicon-arrow-left"></span>返回
				</button>
			    <button type="button" class="btn btn-primary" >
				  <span class="glyphicon glyphicon-share" ></span>提交审核
				</button>
				<button type="button" class="btn btn-success" >
				  <span class="glyphicon glyphicon-edit" ></span>修改信息
				</button>
				<button type="button" class="btn btn-danger" >
				  <span class="glyphicon glyphicon-trash" ></span>删除
				</button>
				
			  </div>
	  </div>
	</div>
      
  <div class="panel panel-info">
  <div class="panel-heading">
	   	<a data-toggle="collapse" data-parent="#accordion" href="#base"> 
	   	  <h4 class="panel-title"> 基本信息</h4></a>
</div>
  <div class="panel-body panel-collapse collapse in" id="base">
    <table  class="table table-bordered">
    	<tr>
		   <th>标题:</th>
		   <td colspan="5">${room.roomTitle }</td>
		 </tr>
		 <tr>
		   <th>小区名:</th>
		   <td colspan="3">${room.compounds} </td>
		   <th>区域:</th>
		   <td>${room.area.areaName } </td>
		 </tr>
		 <tr>
		   <th>详细地址:</th>
		   <td colspan="3">${room.address }</td>
		   <th>房屋类型:</th>
		   <td>
		   ${room.type.typeName }
		   </td>
		 </tr>
		 <tr>
		   <th>户型:</th>
		   <td colspan="3">${room.roomNum }  室   ${room.hallNum }  厅  ${room.kitchenNum}  厨    ${room.toiletNum }   卫 </td>
		   <th>楼层:</th>
		   <td>${room.floor }</td>
		</tr>
		 <tr>
		   <th>租金:</th>
		   <td>${room.price } 元/月</td>
		   <td colspan="2"></td>
		   <th>出租类型:</th>
		   <td>${room.rentType }</td>
		 </tr>
		  <tr>
		  <th>面积:</th>
		   <td>${room.roomArea } ㎡</td>
		   <td style="width: 20%;" colspan="2"></td>
		   <th>创建时间:</th>
		   <td>${room.buildDate }</td>
		 </tr>
		 <tr>
		   <th>描述:</th>
		   <td colspan="5"> ${room.roomContent }</td>
		 </tr>
		 <tr>
		   <th>联系人:</th>
		   <td>${room.userName } </td>
		   <td colspan="2"></td>
		   <th>联系电话:</th>
		   <td>${room.phone }</textarea> </td>
		 </tr>
    </table>
  </div>
</div>
  <div class="panel panel-info">
	  <div class="panel-heading">
		   	<a data-toggle="collapse" data-parent="#accordion" href="#inDiv"> 
		   	  <h4 class="panel-title"> 室内图片</h4></a>
	  </div>
	  <div class="panel-body panel-collapse collapse in" id="inDiv">
	  <c:forEach items="${room.imgSet}" var="img">  
	  <c:if test="${img.imgStyle=='in'}">
	  <div class="img_div"><img src="/Rental/upload/room/${img.imgPath }" class='img-thumbnail myimg'></div>
	  </c:if>
	  </c:forEach>
	  </div>
  </div>
  
   <div class="panel panel-info">
	  <div class="panel-heading">
		   	<a data-toggle="collapse" data-parent="#accordion" href="#outDiv"> 
		   	  <h4 class="panel-title"> 室外图片</h4></a>
	  </div>
	  <div class="panel-body panel-collapse collapse in" id="outDiv">
	  <c:forEach items="${room.imgSet}" var="img">  
	  <c:if test="${img.imgStyle=='out'}">
	  <div class="img_div"><img src="/Rental/upload/room/${img.imgPath }" class='img-thumbnail myimg'></div>
	  </c:if>
	  </c:forEach>
	  </div>
  </div>

     </body>
</html>
