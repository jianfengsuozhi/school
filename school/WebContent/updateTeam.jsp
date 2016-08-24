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
<link rel="stylesheet" type="text/css" href="css/update.css" />

</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="delete">
	    <form action="updateTeam.team" method="post">
		   <table>
		     <tr>
		       <td>要修改的班级名称</td>
		       <%
		         String className = request.getParameter("className");
		       	 if(className != null){
			         className = new String(className.getBytes("ISO-8859-1"),"UTF-8");
		       	 }
		       %>
		       <td>
		        <c:choose>
		          <c:when test="${className1 != null}">
		            <input type="text" name="className" value="${className1}"/>
		          </c:when>
		          <c:otherwise>
		          	<input type="text" name="className" value="<%=className%>"/>
		          </c:otherwise>
		        </c:choose>
		       </td>
		      </tr>
		     <tr>
		       <td>新的班级名称</td>
		       <td><input type="text" name="newClassName" value="${newClassName}"/>
		       <span>${updateTeamTip1}</span>		       
		       </td>
		     </tr>		      
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>