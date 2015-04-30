<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/bootstrap-theme.min.css" type="text/css"></link>
<title>首页</title>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="/Rental/js/my-modal.js"></script>
<script type="text/javascript">
var height = 0;
$(function(){
	$('.carousel').carousel({
  		interval: 3000 
	});
	$('#menuFrame').load(function(){
		getMenuHeight();
	});
	var name = '${rental_user.userName }';
	addBar('bar',name);
	
});
function getMenuHeight(){
	var ifm= document.getElementById("menuFrame");  
		var subWeb = document.frames ? document.frames["menuFrame"].document : ifm.contentDocument;  
		if(ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
			ifm.height = parseInt(ifm.height)+  parseInt(height);
			height=0;
			ifm.width = subWeb.body.scrollWidth;
		} 
}
</script>
</head>
<body>
<div id="bar"></div>
<div class="container">
<iframe id="menuFrame" name="menuFrame" src="/Rental/manage/main.jsp" 
style="overflow: visible;margin-top: 55px;" scrolling="no" frameborder="no" width="100%" ></iframe>
</div>

</body>
</html>
