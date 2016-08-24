<%@ page import="java.util.*" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/delete.css" />

</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="delete">
	    <form action="deleteCourse.course" method="post">
		   <table>
		     <tr>
		       <td>要删除的课程名称</td>
		       <td><input type="text" name="courseName" value="${name }"/>
		       <span>${deleteCourseTip}</span>
		       </td>
		       
		     </tr>
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>