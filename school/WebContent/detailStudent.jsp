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

</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="add">
	    
	    <form action="student.student?deleteTip=-1" method="post">
		   <table>
		     <tr>
		       <td>学号: </td>
		       <td>
		         <span>${student.code}</span>
		       </td>
		     </tr>
		     <tr>
		       <td>姓名: </td>
		       <td>
		         <span>${student.name}</span>
		       </td>
		     </tr>
		     <tr>
		       <td>入学日期: </td>
		       <td>
		        <span>${student.enrollDate}</span>
		       </td>
		     </tr>
		     <tr>
		       <td>出生日期: </td>
		       <td>
		       <span>${student.birthday}</span>
		       </td>
		     </tr>	
		     <tr>
		       <td>性别: </td>
		       <td>
		       <span>${student.sex}</span>
		       </td>
		     </tr>
		     <tr>
		       <td>联系电话: </td>
		       <td>
		       <span>${student.phone}</span>
		       </td>
		     </tr>		     		     	     		     		          
		     <tr>
		       <td>家庭地址: </td>
		       <td>
		       <span>${student.address}</span>
		       </td>
		     </tr>		     		     	     		     		          
		     <tr>
		       <td>民族: </td>
		       <td>
		       <span>${student.nation}</span>
		       </td>
		     </tr>		     		     	     		     		          
		     <tr>
		       <td>班级名: </td>
		       <td>
		       <span>${student.teamName}</span>
		       </td>
		     </tr>		     		     	     		     		          
		   </table>
		   <input id="commit" type="submit" value="返回"/>
		</form>  	    
	   </div>
</html>