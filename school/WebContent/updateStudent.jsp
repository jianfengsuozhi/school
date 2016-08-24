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
	    <form action="update.student" method="post">
		   <table>
		     <tr>
		       <td>学号:</td>
		       <td>
		        <input value="${id}" name="id" hidden="hidden"/>
		        <input value="${code}" name="code" hidden="hidden"/>
		        <span>${code}</span>
		       </td>
		      </tr>
		     <tr>
		       <td>姓名:</td>
		       <td>
		       <input value="${name}" name="name"/>   
		       <span>${teamNameTip}</span> 
		       </td>
		     </tr>		      
		     <tr>
		       <td>入学时间:</td>
		       <td>
		       <input value="${enrollDate}" name="enrollDate"/>	       
		       </td>
		     </tr>		      
		     <tr>
		       <td>出生年月:</td>
		       <td>
		       <input value="${birthday}" name="birthday"/>	       
		       </td>
		     </tr>		      
		     <tr>
		       <td>性别:</td>
		       <td>
		          <select name="sex">
		            <c:if test="${sex=='男'}">
		          	  <option selected="selected">男</option>
		          	  <option>女</option>
		           </c:if>
		          <c:if test="${sex!='男'}">
		          	 <option >男</option>
		          	  <option selected="selected">女</option>
		          </c:if>
		          </select>
		       </td>
		     </tr>	
		     
		     <tr>
		       <td>联系电话:</td>
		       <td>
		       <input value="${phone}" name="phone"/>	       
		       </td>
		     </tr>		      
		     <tr>
		       <td>地址:</td>
		       <td>
		       <input value="${address}" name="address"/>	       
		       </td>
		     </tr>		      
		     <tr>
		       <td>民族:</td>
		       <td>
		       <input value="${nation}" name="nation"/>	       
		       </td>
		     </tr>		      
		     <tr>
		       <td>班级:</td>
		       <td>
		         <select name="teamId">
		         	<c:forEach items="${teamList}" var="item">
		         	  <c:choose>
		         	   <c:when test="${item.id==teamId}">
		         	     <option selected="selected" value="${item.id}">${item.name}</option>
		         	   </c:when>
		         	   <c:otherwise>
		         	     <option value="${item.id}">${item.name}</option>
		         	   </c:otherwise>
		         	  </c:choose>
		         	</c:forEach>
		         </select>
		       </td>
		     </tr>		      
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>