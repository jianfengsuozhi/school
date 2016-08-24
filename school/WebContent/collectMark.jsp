<%@ page import="java.util.*,entity.Student" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib
     uri="http://java.sun.com/jsp/jstl/core"
     prefix="c"
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/class.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 	$('#select1').change(function(){
 		var value = $("#select1 option:selected").val();
 		if(value == 1){
			$.ajax({
				type:"get",
				url:"/school/courseList.ajax",
				dataType:"json",
				success:function(data){
					$("#aim").empty();
					$("#aim").append("课程:");
					$("#aim").append("<select id='s' name='courseId'> </select>");
					for(var i=0;data.length;i++){
						var course = data[i];
						$("#aim #s").append("<option value="+course.id+" >"+course.name+"</option>");
					}
				}
			}); 
 		}
 		if(value == -1){
 			$("#aim").empty();
 		}
 	});
});





</script>
<body style="background:rgb(219,219,219);">
  <%@include file="togetherUse.jsp" %>
  
  <div id="content">
    <span>成绩管理</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
   </div>
     
     <div id="cn">
          <form action="course.mark" method="post">
          	选择: <select name="course.mark" id="select1">
		            <option selected="selected" value="-1">--请选择--</option>              
		            <option  value="1">--课程--</option>              
             	</select>
            <div id="aim"  style="display:inline;"></div>
            <input id="commit" type="submit" value="查询"/>
            <a href="export.ajax?courseId=${courseId}">导出</a>
          </form>
       
     </div>
     <table>
       <tr>
         <th style="width:16%;">课程</th>
         <th style="width:16%;">班级</th>
         <th style="width:16%;">学号</th>
         <th style="width:16%;">学生</th>
         <th style="width:10%;">分数</th>
       </tr>
       <c:forEach var="mark" items="${list}" >
       <tr>
         <td>${mark.courseName}</td>
         <td>${mark.teamName}</td>
         <td>${mark.code}</td>
         <td>${mark.studentName}</td>
         <td>
           ${mark.score}
        </td>
       </tr>
       </c:forEach>
     </table>

    </div>
  </div>
</body>
</html>