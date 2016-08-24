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
    <span>学生管理</span>
   <div>
     <div style="border:black dotted 1px;height:0px;width:90%;margin:0 auto 0 auto;">
   </div>
     
     <div id="cn">
                              
          <form action="queryStudent.student" method="post">
                                  学生名:<input type="text" name="studentName" id="cName"/>${queryTip}
            <input type="submit" value="查询"/>
            <input type="reset" value="重置"/> 
          </form>
       
     </div>
     <table>
       <tr>
         <th style="width:16%;">学号</th>
         <th style="width:16%;">姓名</th>
         <th style="width:16%;">入学时间</th>
         <th style="width:16%;">出生年月</th>
         <th style="width:10%;">性别</th>
         <th style="width:18%;">班级</th>   
                        
       </tr>
       <c:forEach var="student" items="${perpageClassInfo}" >
       <tr>
         <td>${student.code}
          <c:if test="${student.id == id }">
          <span style="font-size:10px">${deleteTip}</span>
          </c:if>
         </td>
         <td>${student.name}</td>
         <td>${student.enrollDate}</td>
         <td>${student.birthday}</td>
         <td>${student.sex}</td>
         <td>
         ${student.teamName}
         <a href="deleteStudent.student?name=${student.name}" onclick="return confirm('确认要删除${student.name}')" style="text-decoration:none;">删除</a>
         <a href="toUpdate.student?id=${student.id}&code=${student.code}&name=${student.name}&enrollDate=${student.enrollDate}&birthday=${student.birthday}&sex=${student.sex}&teamId=${student.teamId}&phone=${student.phone}&address=${student.address}&nation=${student.nation}"  
           style="text-decoration:none;">修改</a>
         <a href="detail.student?id=${student.id}" style="text-decoration:none;">查询详细信息</a>
         </td>
                                                
       </tr>
       </c:forEach>
     </table>
     <div id="bottom">
         <span style="font-size:20px;">
                                            共<c:if test="${studentSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${studentSum}</c:if>条记录    
                                           共<c:if test="${pageSum==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${pageSum}</c:if>页     
                                           第<c:if test="${currentPage==null}" var="rs">0</c:if> 
               <c:if test="${!rs}">${currentPage}</c:if>页
         </span>
         <a href="student.student?currentPage=${firstPage}" onclick="return ${firstPage!=pageSum}" style="margin-left:30px;">首页</a>
         <a href="student.student?currentPage=${currentPage-1}" onclick="lastPage();return ${currentPage>1}">上一页</a>
         <a href="student.student?currentPage=${currentPage+1}" onclick="nextPage();return ${currentPage<pageSum}">下一页</a>
         <a href="student.student?currentPage=${pageSum}" onclick="return ${firstPage!=pageSum}">尾页</a>
         <a href="toAdd.student">添加</a>
     </div>
    </div>
  </div>
</body>
</html>