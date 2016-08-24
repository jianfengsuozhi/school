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
    function nextPage(){
    	if(${currentPage>=pageSum}){
    		alert("已经是最后一页,没有下一页");
    	}
    }
    function lastPage(){
    	if(${currentPage<=firstPage}){
    		alert("已经是第一页,没有上一页");
    	}
    }
    
    $(function(){
 	   $("#commit").click(function(){
 		  var courseId = $("#cn #select1 option:selected").val();
 		  var teamId = $("#cn #select2 option:selected").val();
 		  var studentId = $("#cn #select3 option:selected").val();
 		   $("#cn form").attr("action",
 		    		"query.mark?teamId="+
 		    		teamId+"&courseId="+courseId+"&studentId="+studentId);
 	   });
    });
 </script>
</head>
<body style="background:rgb(219,219,219);">
  <%@include file="togetherUse.jsp" %>
  
  <div id="content">
    <span>成绩管理</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
   </div>
     
     <div id="cn">
          <form action="mark.mark" method="post">
          	课程: <select name="course" id="select1">
		            <option selected="selected" value="-1">--请选择--</option>              
                    <c:forEach items="${courseList}" var="course">             
                      <option value="${course.id}">${course.name }</option>
                    </c:forEach>
             	</select>
                                  班级:<select name="team" id="select2">
                    <option selected="selected" value="-1">--请选择--</option> 
                    <c:forEach items="${teamList}" var="team">             
                      <option value="${team.id}">${team.name }</option>
                    </c:forEach>
                </select>
                                  
                                  学生: <select name="student" id="select3">
		            <option selected="selected" value="-1">--请选择--</option>              
                     <c:forEach items="${studentList}" var="student">             
                      <option value="${student.id}">${student.name }</option>
                    </c:forEach>  
             	</select> 
            <input id="commit" type="submit" value="查询"/>
            <a href="collectMark.jsp" style="text-decoration:none;">汇总分析</a>
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
       <c:forEach var="mark" items="${perpageClassInfo}" >
       <tr>
         <td>${mark.courseName}</td>
         <td>${mark.teamName}</td>
         <td>${mark.code}</td>
         <td>${mark.studentName}</td>
         <td>
           ${mark.score}
           <a href="delete.mark?id=${mark.id}" onclick="return confirm('确认要删除${mark.studentName}')" style="text-decoration:none;">删除</a>
           <a href="toUpdate.mark?id=${mark.id}&courseName=${mark.courseName}
           &teamName=${mark.teamName}&code=${mark.code}&studentName=${mark.studentName}&score=${mark.score}"  
           style="text-decoration:none;">修改</a>
        </td>
       </tr>
       </c:forEach>
     </table>
     <div id="bottom">
         <span style="font-size:20px;">
                                            共<c:if test="${courseSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${courseSum}</c:if>条记录    
                                           共<c:if test="${pageSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${pageSum}</c:if>页     
                                           第<c:if test="${currentPage==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${currentPage}</c:if>页
         </span>
         <a href="mark.mark?currentPage=${firstPage}" onclick="return ${firstPage!=pageSum}" style="margin-left:30px;">首页</a>
         <a href="mark.mark?currentPage=${currentPage-1}" onclick="lastPage();return ${currentPage>1}">上一页</a>
         <a href="mark.mark?currentPage=${currentPage+1}" onclick="nextPage();return ${currentPage<pageSum}">下一页</a>
         <a href="mark.mark?currentPage=${pageSum}" onclick="return ${firstPage!=pageSum}">尾页</a>
         <a href="toAdd.mark">添加</a>
         
     </div>
    </div>
  </div>
</body>
</html>