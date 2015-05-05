<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Rental/css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="/Rental/css/bootstrap-theme.css" type="text/css"></link>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<style type="text/css">
.info_list_left{
	width: 20%;
	height: 150px;
	float: left;
}
.info_list_right{
margin-left:20px;
width: 11%;
color:#EB5F00;
font-size:40px;
font-weight:bold;
float: left;
}
.info_list_mid{

margin-left:20px;
width:65%;
float: left;
}
.clear{
	clear: both;
}
.light{
	color: #797979;
	margin-right: 10px;
}
.badge{
	float: right;
}
</style>
<script src="/Rental/js/my-modal.js"></script>
<script type="text/javascript">
$(function(){
	findUser(1,5);
	var name = '${rental_user.userName }';
	addBar('bar',name);
});

function findUser(pageNo,pageSize){
	
	$.ajax({
		url:'/Rental/room!findByMixAndPage.action',
		data:{'pageBean.pageSize':pageSize,'pageBean.pageNo':pageNo},
		type:'POST',
		dataType:'JSON',
		success:function(data){
			var str="";
			$('#userList').html('');
			
			if(data==null){
				str+="<tr><td colspan='6'>没有检索到数据</td></tr>";
			}else{
				$.each(data.aaData, function(i,item){ 
					str+='<a href="/Rental/room!findByIdForUser.action?room.roomId='+item.roomId+'" target="_blank" ><div class="panel panel-success"><div class="panel-body"><div class="info_list_left">';
					var src='/Rental/img/img1.png';
					if(item.imgSet.length>0){
						src='/Rental/upload/room/'+item.imgSet[0].imgPath;
					}
					str+='<img src="'+src+'" alt="'+item.roomTitle+'"  class="img-thumbnail" style="width:200px;height: 150px;"></div>'+
					'<div class="info_list_mid">'+
					'<h4>'+item.roomTitle +'</h4>'+
					'<span class="light">'+item.roomArea+'平方米 </span><span class="light">|</span><span class="light">'+item.roomNum+'室'+
					item.hallNum +'厅'+item.kitchenNum+'厨'+item.toiletNum+'卫 </span><span class="light">|</span>'+
					'<span class="light">'+item.price+'元/m </span><span class="light">|</span><span class="light">'+
					item.floor +'层 </span><span class="light">|</span>'+
					'<span class="light">'+item.buildDate +'建造 </span>'+
					'<div class="light" style="margin:17px; ">'+
					'小区名:'+item.compounds +'<span style="margin: 30px;"></span>['+item.address +']'+
					'</div>'+
					'<span >发布时间： </span> <span class="light">['+item.updateDate+'] </span>'+
					'</div>'+
					'<div class="info_list_right">'+
					'<span class="badge" >'+item.viewCount+'</span>'+
					'<div style="margin-top: 40px;">'+item.price +'</div>'+
					'</div>'+
				  '</div>'+
				'</div></a>'; 
				 });
				str+='<nav style="text-align: center;"><ul class="pagination"><li><a href="javascript:findUser(1,'+data.pageSize+')">&laquo;</a></li>';
				for(var i =1 ;i<=data.pageCount;i++){
					if(i==data.pageNo){
						str+='<li class="active"><a href="javascript:findUser('+i+','+data.pageSize+')">'+i+'</a></li>';
					}else{
						str+='<li ><a href="javascript:findUser('+i+','+data.pageSize+')">'+i+'</a></li>';
					}
				}
				str+='<li><a href="javascript:findUser('+data.pageCount+','+data.pageSize+')">&raquo;</a></li></ul></nav>';
			}
			$('#userList').append(str);
			parent.getMenuHeight();
		}
	});
}
</script>
<title>Insert title here</title>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
</head>
<body>
<div id="bar"></div>
<div class="container" style="margin-top: 55px;" >
<div id="userList"></div>
</div>
</body>
</html>
