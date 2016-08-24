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
		  var courseId = $("#add #select1 option:selected").val();
		  var teacherId = $("#add #select2 option:selected").val();
		  var semester = $("#add #select3 option:selected").val();
		  var credit = $("#add #credit").val();
		   $("#add form").attr("action",
		    		"addCourseSchedule.courseSchedule?courseId="+
		    		courseId+"&teacherId="+teacherId+"&semester="+semester+"&credit="+credit);
		   
	   });
   });

</script>
</head>

	<body style="background:rgb(219,219,219);">
	    <%@include file="togetherUse.jsp" %>
	    <div id="add">
	    
	    <form action="addCourseSchedule.courseSchedule" method="post">
		   <table>
		     <tr>
		       <td>课程名称</td>
		       <td>
              <select id="select1">
                <option value="-1">--请选择--</option>
               <c:forEach  var="item" items="${courses}" >
                 <option value="${item.id}">${item.name}</option>
               </c:forEach>
             </select>
             	  <span>${tip}</span>
		       </td>
		    </tr>
		    <tr>
		       <td>任课教师</td>
		       <td>
               <select id="select2">
                <option value="-1">--请选择--</option>
                <c:forEach  var="item" items="${teachers}" >
                 <option value="${item.id}">${item.name}</option>
               </c:forEach>
               </select>		       
		       </td>
		     </tr>
		     <tr>
		       <td>学期</td>
		       <td>
		         <select id="select3">
		           <option value="1" selected="selected">1</option>
		           <option value="2">2</option>
		           <option value="3">3</option>
		           <option value="4">4</option>
		         </select>
		       </td>
		      </tr>
		      <tr>
		       <td>学分</td>
		       <td><input type="text" name="credit" id="credit"/>
		      </td>
		      </tr>				     
		   </table>
		   <input id="commit" type="submit" value="提交"/>
		   <input id="reset" type="reset" value="重置"/>
		</form>  	    
	   </div>
</html>