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
	$(document).ready(function(){

	 	$('#phone').blur(function(){
			var phone1 = {phone:$("#phone").val() };
			$.ajax({
				type:"post",
				url:"/school/phone.ajax",
				dataType:"json",
				data:phone1,
				success:function(data){
					$("#phone1").html("<b>"+data.str+"</b>");
				}
			}); 
	 	});
	});

</script>
</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="add">
	    
	    <form action="addStudent.student" method="post">
		   <table>
		     <tr>
		       <td>学号</td>
		       <td><input type="text" name="code" id="code"/>
		       </td>
		     </tr>
		     <tr>
		       <td>姓名</td>
		       <td><input type="text" name="name" id="name"/>
		       <span>${teamNameTip}</span>
		       </td>
		     </tr>
		     <tr>
		       <td>入学日期</td>
		       <td><input type="text" name="enrollDate" id="enrollDate"/>
		       </td>
		     </tr>
		     <tr>
		       <td>出生日期</td>
		       <td><input type="text" name="birthday" id="birthday"/>
		       </td>
		     </tr>	
		     <tr>
		       <td>性别</td>
		       <td>
		         <select name="sex">
		           <option value="男">男</option>
		           <option value="女">女</option>
		         </select>
		       </td>
		     </tr>
			 <tr>
		       <td >联系电话:</td>
		       <td>
		       <input value="${phone}" name="phone" id="phone"/>	
		       <span id="phone1"></span>       
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
		       <td>班级名</td>
		       <td>
		         <select name="teamId">
		           <c:forEach items="${list}"  var="item">
			           <option value="${item.id}">${item.name}</option>
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