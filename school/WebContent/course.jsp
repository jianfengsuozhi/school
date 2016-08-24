<%@ page import="java.util.*,entity.Team" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib
     uri="http://java.sun.com/jsp/jstl/core"
     prefix="c"
 %>
<html>
<head>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#cn #submit").click(function(){
		    var teacherId = $("#cn select option:selected").val();
		    var classId = $("#cn #classId").val();
		    $("#cn form").attr("action",
		    		"queryCourseSchedule.courseSchedule?teacherId="+
		    		teacherId+"&classId="+classId);
		 });
	});
  
</script>
<meta charset="utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/course.css" />
</head>
<body style="background:rgb(219,219,219);">
  <%@include file="togetherUse.jsp" %>
  
  <div id="content">
    <span>班级管理>>班级课程信息>>${className}课表信息</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
     </div>
     
     <div id="cn">
          <form action="queryCourseSchedule.courseSchedule" method="post">
             <input value="${classId}" id="classId" hidden="hidden"/>
                                 教师:
             <select>
               <option value="-1">--请选择--</option>
               <c:forEach  var="item" items="${teachers}" >
               <option value="${item.id}">${item.name}</option>
              </c:forEach>
             </select>
            <input type="submit" value="查询" id="submit"/>
            <input type="reset" value="重置"/> 
          </form>
       
     </div>
     <table>
       <tr>
         <th style="width:22%;">开课课程</th>
         <th style="width:22%;">任课老师</th>
         <th style="width:22%;">学期</th>
         <th style="width:22%;">学分</th>         
       </tr>
       <c:forEach var="courseSchedule" items="${courseScheduleInfo1}" >
       <tr>
         <td>${courseSchedule.courseName}</td>
         <td>${courseSchedule.teacherName}</td>
         <td>${courseSchedule.semester}</td>
         <td>${courseSchedule.credit} 
            <a href="deleteCourseSchedule.courseSchedule?id=${courseSchedule.id}" 
               onclick="return confirm('确认要删除${courseSchedule.courseName}')" 
               style="text-decoration:none;">删除</a>
         </td>
       </tr>
       </c:forEach>
     </table>
     <div id="bottom">
         <a href="add.courseSchedule?tip=" id="a1">添加</a>
      </div>
    </div>
  </div>
</body>
</html>