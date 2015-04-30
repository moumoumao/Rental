<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/Rental/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="/Rental/css/bootstrap-theme.min.css" type="text/css"></link>
<title>首页</title>
<script src="/Rental/js/jquery-1.9.1.js"></script>
<script src="/Rental/js/bootstrap.min.js"></script>
<script src="/Rental/js/my-modal.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">网站</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/Rental/manage/main.jsp" target="blank">首页<span class="sr-only">(current)</span></a></li>
        <li><a href="/Rental/manage/main.jsp" target="parent">item1</a></li>
        <li><a href="/Rental/manage/room_list.jsp" >list</a></li>
        <li><a href="#">item3</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">下拉列表 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/Rental/manage/houseinfo_create.jsp" >新增</a></li>
            <li><a href="/Rental/manage/room_create1.jsp" >新增1</a></li>
            <li><a href="/Rental/manage/user_list.jsp" >用户列表</a></li>
            <li><a href="#">角色管理</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>

      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${rental_user.userName } <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu" >
            <li><a href="/Rental/manage/userInfo.jsp" >基本信息</a></li>
            <li><a href="/Rental/manage/personal_center.jsp" >个人中心</a></li>
            <li><a href="/Rental/manage/system.jsp" >系统设置</a></li>
            <li class="divider"></li>
            <li><a href="/Rental/user!doQuit.action"><i class="glyphicon glyphicon-off"></i>退出登录</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
