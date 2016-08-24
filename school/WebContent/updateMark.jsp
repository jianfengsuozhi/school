<%@ page import="java.util.*" 
	pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/update.css" />
</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="delete">
	    <form action="update.mark" method="post">
		   <table>
		     <tr>
		       <td>课程:</td>
		       <td>
		        <input value="${id}" name="id" hidden="hidden"/>
		       <span>${courseName}</span>
		       </td>
		      </tr>
		     <tr>
		       <td>班级:</td>
		       <td>
		       <span>${teamName}</span>		       
		       </td>
		     </tr>		      
		     <tr>
		       <td>学号</td>
		       <td>
		       <span>${code}</span>		       
		       </td>
		     </tr>		      
		     <tr>
		       <td>学生</td>
		       <td>
		       <span>${studentName}</span>		       
		       </td>
		     </tr>		      
		     <tr>
		       <td>分数</td>
		       <td><input type="text" name="score" value="${score}"/>
		       </td>
		     </tr>		      
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>