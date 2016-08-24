<%@ page import="java.util.*,entity.Teacher" 
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
 </script>
</head>
<body style="background:rgb(219,219,219);">
  <%@include file="togetherUse.jsp" %>
  
  <div id="content">
    <span>教师管理</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
   </div>
     
     <div id="cn">
                              
          <form action="queryTeacher.teacher" method="post">
                                  教师名:<input type="text" name="teacherName" id="cName"/>${queryTip}
            <input type="submit" value="查询"/>
            <input type="reset" value="重置"/> 
          </form>
       
     </div>
     <table>
       <tr>
         
         <th style="width:45%;">教师名</th>
         <th style="width:45%;">操作</th>
       </tr>
       <c:forEach var="teacher" items="${perpageClassInfo}" >
       <tr>
         <td>${teacher.name}
         <c:if test="${teacher.id == id }">
          <span style="font-size:20px">${deleteTip}</span>
          </c:if>
         </td>
         <td>
         <a href="teacherInfo.teacher?teacherName=${teacher.name}&teacherId=${teacher.id}" style="text-decoration:none;">查看教师课程信息</a>
         <a href="deleteTeacher.teacher?teacherName=${teacher.name}" onclick="return confirm('确认要删除${teacher.name}')" style="text-decoration:none;">删除</a>
         <a href="updateTeacher.jsp?teacherName=${teacher.name}" style="text-decoration:none;">修改</a>
         </td>
       </tr>
       </c:forEach>
     </table>
     <div id="bottom">
         <span style="font-size:20px;">
                                            共<c:if test="${teacherSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${teacherSum}</c:if>条记录    
                                           共<c:if test="${pageSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${pageSum}</c:if>页     
                                           第<c:if test="${currentPage==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${currentPage}</c:if>页
         </span>
         <a href="teacher.teacher?currentPage=${firstPage}" onclick="return ${firstPage!=pageSum}" style="margin-left:30px;">首页</a>
         <a href="teacher.teacher?currentPage=${currentPage-1}" onclick="lastPage();return ${currentPage>1}">上一页</a>
         <a href="teacher.teacher?currentPage=${currentPage+1}" onclick="nextPage();return ${currentPage<pageSum}">下一页</a>
         <a href="teacher.teacher?currentPage=${pageSum}" onclick="return ${firstPage!=pageSum}">尾页</a>
         <a href="addTeacher.jsp">添加</a>
     </div>
    </div>
  </div>
</body>
</html>