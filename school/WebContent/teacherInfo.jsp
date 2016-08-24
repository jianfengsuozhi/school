<%@ page import="java.util.*,entity.Team" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib
     uri="http://java.sun.com/jsp/jstl/core"
     prefix="c"
 %>
<html>
<head>


<meta charset="utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/course.css" />
</head>
<body style="background:rgb(219,219,219);">
  <%@include file="togetherUse.jsp" %>
  
  <div id="content">
    <span>教师管理</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
     </div>
    <table>
       <tr>
         <th style="width:22%;">开课课程</th>
         <th style="width:22%;">班级</th>
         <th style="width:22%;">学期</th>
         <th style="width:22%;">学分</th>         
       </tr>
       <c:forEach var="teacherInfo" items="${teacherInfo}" >
       <tr>
         <td>${teacherInfo.courseName}</td>
         <td>${teacherInfo.teamName}</td>
         <td>${teacherInfo.semester}</td>
         <td>${teacherInfo.credit} </td>
       </tr>
       </c:forEach>
     </table>
     <div id="bottom">
         <a href="teacher.teacher" id="a1">返回</a>
      </div>
    </div>
  </div>
</body>
</html>