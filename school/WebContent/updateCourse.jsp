<%@ page import="java.util.*" 
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
<link rel="stylesheet" type="text/css" href="css/update.css" />

</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="delete">
	    <form action="updateCourse.course" method="post">
		   <table>
		     <tr>
		       <td>要修改的课程名称</td>
		       <%
		         String courseName = request.getParameter("courseName");
		         if(courseName!=null){
		        	 courseName = new String(courseName.getBytes("iso-8859-1"),
		        			       "utf-8");
		         }
		         String courseCredit = request.getParameter("courseCredit");
		       %>
		       <td>
		         <c:choose>
		         	<c:when test="${courseName1!=null}">
		         	  <input type="text" name="courseName" value="${courseName1}"/>
		         	</c:when>
		          	<c:otherwise>
		          	  <input type="text" name="courseName" value="<%=courseName%>"/>
		          	</c:otherwise>
		         </c:choose>
		      
		       <span>${updateCourseTip}</span>
		       </td>
		       		       
		      </tr>
		     <tr>
		       <td>新的课程名称</td>
		       <td><input type="text" name="newCourseName" value="${newCourseName}"/>
		       <span>${updateCourseTip1}</span>		       
		       </td>
		       
		       		       
		     </tr>		      
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>