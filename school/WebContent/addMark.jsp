<%@ page import="java.util.*" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib
     uri="http://java.sun.com/jsp/jstl/core"
     prefix="c"
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/add.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#commit").click(function(){
		  var studentId = $("#add #select1 option:selected").val();
		  var courseId = $("#add #select2 option:selected").val();
		  var score = $("#add #score").val();
		   $("#add form").attr("action",
		    		"add.mark?studentId="+
		    		studentId+"&courseId="+courseId+"&score="+score);
	   });
   });

</script>
</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="add">
	    
	    <form action="" method="post">
		   <table>
		     <tr>
		       <td>学生</td>
		       <td>
		         <select id="select1">
                     <c:forEach items="${studentList}" var="student">             
                      <option value="${student.id}">${student.name }</option>
                    </c:forEach>  
             	</select> 
             	${tip }
		       </td>
		     </tr>
		   	 <tr>
		       <td>课程</td>
		       <td>		         
		          <select id="select2">
                     <c:forEach items="${courseList}" var="course">             
                      <option value="${course.id}">${course.name }</option>
                    </c:forEach>  
             	</select> 
		       </td>
		     </tr> 
		     <tr>
		       <td>分数</td>
		       <td><input type="text" name="courseName" id="score"/>
		       </td>
		     </tr>    
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>