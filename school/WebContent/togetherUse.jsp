<%@ page import="java.util.*" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<style>
  <style type="text/css">
    #content span{
	border:black solid 1px;
	height:200px;
	line-height:180px;
	font-size:45px;
	margin-left:35%;
    }
</style>



</head>
<body style="background:rgb(219,219,219);">
  <div id="title"><b>学生信息管理系统</b></div>
  <div id="navi">
     <ul>
       <li><a href="home.jsp">用户管理</a></li>
       <li><a href="team.team?deleteTip=-1">班级管理</a></li>
       <li><a href="courseOperate.course?deleteTip=-1">课程管理</a></li>
       <li><a href="teacher.teacher?deleteTip=-1">教师管理</a></li>
       <li><a href="student.student?deleteTip=-1">学生管理</a></li>
       <li><a href="mark.mark">成绩管理</a></li>
       <li><a href="exit.do">登录/退出系统</a></li>
     </ul>
   
  </div>
  
</body>
</html>