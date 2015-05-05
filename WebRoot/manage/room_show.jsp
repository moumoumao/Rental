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
<script src="/Rental/js/bootstrap.js"></script>
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
.user{
margin-left: 80px;
}
.green{
	color: green;
	font-size: 20px;
}
.red{
	color: red;
}
</style>
<script type="text/javascript">
var img_len=0;
var inHtml,outHtml;
$(function(){
	var name = '${rental_user.userName }';
	addBar('bar',name);
	var userId=${rental_user.userId};
	var roomId=${room.roomId};
	$('.roomID').val(roomId);
	//收藏
	$('#bt_love').click(function(){
		$.ajax({
			  type:'POST',
			  dataType:'JSON',
			  url:'/Rental/love!addLove.action',
			  data:{'mylove.room.roomId':'${room.roomId}'},
			  success:function(data){
				  layer.msg(data.msg, {icon: 1});
				  $('#love').val(data.data);
				  $('#cancel_love').show();
				  $('#bt_love').hide();
		  	  }
		  });
	
	});
	//取消收藏
	$('#cancel_love').click(function(){
		
		var loveId = $('#love').val();
		$.ajax({
			url:'/Rental/love!removeLove.action',
			data:{'mylove.loveId':loveId},
			type:'POST',
			dataType:'JSON',
			success:function(data){
				layer.msg(data.msg, {icon: 1});
				$('#love').val(0);
				$('#cancel_love').hide();
				$('#bt_love').show();
			}
		});
	
	});
	//判断是否收藏

	$.ajax({
		url:'/Rental/love!isLove.action',
		data:{'mylove.room.roomId':roomId},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			if(data.resultCode==200){//收藏
				$('#love').val(data.data);
				$('#cancel_love').show();
				$('#bt_love').hide();
			}else if(data.resultCode==201){//未收藏
				$('#cancel_love').hide();
				$('#bt_love').show();
			}
		}
	});
	inHtml=$('#in').html();
	outHtml=$('#out').html();
	$('#out').html('');
	if($('#in jumbotron').length>0){
		$('#inCarousel').carousel({
	  		interval: 3000 
		});
	}
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		// 获取已激活的标签页的名称
		var activeTab = $(e.target).text(); 
		if(activeTab=='室内图'&&$('#in jumbotron').length>0){
			$('#in').html(inHtml);
			$('#out').html('');
			$('#inCarousel').carousel({
		  		interval: 3000 
			});
		}else{
			$('#out').html(outHtml);
			if($('#out jumbotron').length>0){
				$('#in').html('');
				$('#outCarousel').carousel({
			  		interval: 3000 
				});
			}
		}
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
/**
 * 显示发送消息dialog
 */
function showMsgDiv(i){

	if(i==1){
		$('#plForm,#appointForm').hide();
		$('#sendForm').show();
		$('#modalTitle').html('发送消息');
	}else if(i==2){
		$('#sendForm,#appointForm').hide();
		$('#plForm').show();
		$('#modalTitle').html('发布评论');
	}else{
		$('#sendForm,#plForm').hide();
		$('#appointForm').show();
		$('#modalTitle').html('新增预约');
	}
	$('#sendForm,#plForm,#appointForm').resetForm(); 
	$('#myModal').modal();
	
}
/**
 * 提交信息
 */
function subForm(){
	var title=$('#modalTitle').html();
	var formName='';
	
	if(title=='发送消息'){
		formName='sendForm';
	}else if(title=='发布评论'){
		formName='plForm';
	}else if(title=='新增预约'){
		formName='appointForm';
	}
	var ii = layer.load();
	alert($('#'+formName).serialize());
	$('#'+formName).ajaxSubmit({
           success: function(data) {
               layer.close(ii);
               layer.msg(data.msg);
               $('#myModal').modal('hide');
           }
     });
     return false; 
}
</script>
  </head>
  
  <body id="accordion">
  <div id="bar"></div>
  <div class="container bar">
  <div style="float: left;width: 30%;margin: 10px;text-align: center;" class="well">
  	<div class="img_div user"><img alt="" src="/Rental/img/img1.png" class='img-thumbnail myimg'></div>
  	<div style="clear: both;font-size: 15px;">
  	<br/>
  	<label>昵称：</label> ${rental_user.userName }
  	
	<br/>
	<button type="button" class="btn btn-default green" aria-label="Center Align">
	  <span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>${rental_user.phone }
	</button>
	<br><br/>
	<input type="hidden" id="love" value="0">
	<button type="button" class="btn btn-default" id="bt_love">
	  <span class="glyphicon glyphicon-heart red" aria-hidden="true"></span>收藏房源
	</button>
	<button type="button" class="btn btn-default" id="cancel_love">
	  <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>取消收藏
	</button>
	<button type="button" class="btn btn-default" aria-label="Left Align" onclick="showMsgDiv(1);">
	  <span class="glyphicon glyphicon-envelope " style="color: teal;" aria-hidden="true"></span>发送消息
	</button>
	<br/>
	<br/>
	<button type="button" class="btn btn-default" id="bt_appoint" onclick="showMsgDiv(3);">
	  <span class=" glyphicon glyphicon-bell"  style="color:maroon"></span>预约房源
	</button>
	<button type="button" class="btn btn-default"  onclick="showMsgDiv(2);">
	  <span class="glyphicon glyphicon-comment " style="color: teal;" aria-hidden="true"></span>评论房源
	</button>
  	</div>
  	
  </div>
  <div style="float: left;width: 68%;">
  <div class="panel panel-default">
		<div class="panel-body">
		<ul id="myTab" class="nav nav-tabs">
		   <li class="active"><a href="#in" data-toggle="tab">室内图</a></li>
		   <li><a href="#out" data-toggle="tab">室外图</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
		   <div class="tab-pane fade in active" id="in">
			   <div class="carousel slide" id="inCarousel">
				  <div class="carousel-inner">
				  <c:set var="className" value="active item"></c:set>
					  <c:forEach items="${room.imgSet}" var="img">  
					  <c:if test="${img.imgStyle=='in'}">
					  	  <div class="${className }"><img src="/Rental/upload/room/${img.imgPath }"style="width: 100%;height: 400px;"></div>
						  <c:if test="${className =='active item'}">
						  		<c:set var="className" value="item"></c:set>
						   </c:if>
					  </c:if>
					  </c:forEach>
					   <c:if test="${className =='active item'}">
					   <div class="jumbotron" style="text-align: center;">
					   	  <br/>
						  <h1>没有室内图</h1>
						  
						</div>
					   </c:if>
					  
				  </div>
				  <c:if test="${className !='active item'}">
					  <a class="carousel-control left" href="#inCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></a>
					  <a class="carousel-control right" href="#inCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
				  </c:if>
				</div>
		   </div>
		   <div class="tab-pane fade" id="out">
			  <div class="carousel slide" id="outCarousel" >
				  <div class="carousel-inner">
					  <c:set var="className" value="active item"></c:set>
					  <c:forEach items="${room.imgSet}" var="img">  
					  <c:if test="${img.imgStyle=='out'}">
					  	  <div class="${className }"><img src="/Rental/upload/room/${img.imgPath }"style="width: 100%;height: 400px;"></div>
						  <c:if test="${className =='active item'}">
						  		<c:set var="className" value="item"></c:set>
						   </c:if>
					  </c:if>
					  </c:forEach>
					   <c:if test="${className =='active item'}">
					   <div class="jumbotron" style="text-align: center;">
					    	<br/>
						  <h1>没有室外图</h1>
						</div>
					   </c:if>
				  </div>
				  <c:if test="${className !='active item'}">
					  <a class="carousel-control left" href="#outCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></a>
					  <a class="carousel-control right" href="#outCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
				  </c:if>
				</div>
		   </div>
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
            <h4 class="modal-title" id="modalTitle">发送消息</h4>
         </div>
         <div class="modal-body" id="body">
         <!-- 消息开始 -->
         <form action="/Rental/msg!sendMsg.action" role="form" method="post" id="sendForm">
          <table class="table">
		  	<tr>
		  		<td>标题：</td>
		  		<td><input type="text" class="form-control" id="name" name="message.mesTitle"> </td>
		  	</tr>
		  	<tr>
		  		<td>内容：</td>
		  		<td><textarea type="text" class="form-control" id="desc" name="message.mesContent"></textarea></td>
		  	</tr>
		  </table>
         </form>
         <!-- 消息结束 -->
         <!-- 评论开始 -->
         <form action="/Rental/comment!addComment.action" role="form" method="post" id="plForm">
         <input type="hidden" name="mycomment.room.roomId" class="roomID">
          <table class="table">
		  	<tr>
		  		<td>内容：</td>
		  		<td><textarea type="text" class="form-control" id="desc" name="mycomment.comContent"></textarea></td>
		  	</tr>
		  </table>
         </form>
         <!-- 评论结束 -->
         <!-- 预约开始 -->
         <form action="/Rental/msg!sendMsg.action" role="form" method="post" id="appointForm">
         <input type="hidden" name="" class="roomID">
          <table class="table">
		  	<tr>
		  		<td>预约人数：</td>
		  		<td><input type="text" class="form-control" id="name" name="message.mesTitle"> </td>
		  	</tr>
		  	<tr>
		  		<td>预约时间：</td>
		  		<td><input type="text" class="form-control" id="name" name="message.mesTitle"> </td>
		  	</tr>
		  	<tr>
		  		<td>联系人：</td>
		  		<td><input type="text" class="form-control" id="name" name="message.mesTitle"> </td>
		  	</tr>
		  	<tr>
		  		<td>联系电话：</td>
		  		<td><input type="text" class="form-control" id="desc" name="message.mesContent"/></td>
		  	</tr>
		  </table>
         </form>
         <!-- 预约结束 -->
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
