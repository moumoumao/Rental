//首页的dialog 中的ajax操作
/**
 * 传值显示dialog
 * @param html 内容按照bootstrap 中 modal body 和foot格式写
 * @param title 标题
 * @return
 */
function showDialog(html,title){

	$("#modalContal").html(html);
	$("#modalTItle").html(title);
	$('#modal-dialog').css({
	    width: 'auto'
	}); 
	$('#myModal').modal()
	
}
function addBar(id,name){
	$('#'+id).append('<nav class="navbar navbar-inverse navbar-fixed-top">'+
  '<div class="container">'+
    '<div class="navbar-header">'+
      '<a class="navbar-brand" href="#">网站</a>'+
   '</div>'+
    '<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">'+
      '<ul class="nav navbar-nav">'+
        '<li class="active"><a href="/Rental/index.jsp">首页<span class="sr-only">(current)</span></a></li>'+
        '<li><a href="#">item1</a></li>'+
        '<li><a href="/Rental/manage/room_list.jsp" target="_blank">list</a></li>'+
        '<li><a href="#">item3</a></li>'+
        '<li class="dropdown">'+
          '<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">下拉列表 <span class="caret"></span></a>'+
          '<ul class="dropdown-menu" role="menu">'+
            '<li><a href="/Rental/manage/houseinfo_create.jsp">新增</a></li>'+
            '<li><a href="/Rental/manage/room_create1.jsp">新增1</a></li>'+
            '<li><a href="/Rental/manage/user_list.jsp" target="menuFrame">用户列表</a></li>'+
            '<li><a href="#">角色管理</a></li>'+
            '<li class="divider"></li>'+
           ' <li><a href="#">Separated link</a></li>'+
          '<li class="divider"></li>'+
         '<li><a href="#">One more separated link</a></li>'+
        '</ul>'+
       '</li>'+
      '</ul>'+
      '<form class="navbar-form navbar-left" role="search">'+
        '<div class="form-group">'+
         ' <input type="text" class="form-control" placeholder="Search">'+
        '</div>'+
       ' <button type="submit" class="btn btn-default">Submit</button>'+
      '</form>'+
      '<ul class="nav navbar-nav navbar-right">'+
        '<li class="dropdown">'+
          '<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">'+name +'<span class="caret"></span></a>'+
         ' <ul class="dropdown-menu" role="menu" >'+
            '<li><a href="/Rental/manage/userInfo.jsp">基本信息</a></li>'+
            '<li><a href="/Rental/manage/personal_center.jsp" >个人中心</a></li>'+
            '<li><a href="/Rental/manage/system.jsp" target="menuFrame">系统设置</a></li>'+
            '<li class="divider"></li>'+
            '<li><a href="/Rental/user!doQuit.action"><i class="glyphicon glyphicon-off"></i>退出登录</a></li>'+
         '</ul>'+
        '</li>'+
      '</ul>'+
    '</div>'+
  '</div>'+
'</nav>'
);
}
