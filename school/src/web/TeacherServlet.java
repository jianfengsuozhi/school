package web;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;




import dao.DBUtil;
import dao.TeacherDAO;
import dao.TeamDAO;


import entity.CourseInfo;
import entity.Teacher;
import entity.Teacher;
import entity.Team;

public class TeacherServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
        String str = uri.substring(
       		 uri.lastIndexOf("/"), 
       		 uri.lastIndexOf("."));
        HttpSession session1 = request.getSession();
        String uname = (String)session1.getAttribute("uname");
        if(uname==null){
        	response.sendRedirect("login.jsp");
        	return;
        }
         if(str.equals("/teacher")){
         	String deleteTip = request.getParameter("deleteTip");
         	if("1".equals(deleteTip)){
         		request.setAttribute("deleteTip", "无法删除该教师");
         	}else{
         		request.setAttribute("deleteTip", "");
         	}
        	 
        	ServletContext context = getServletContext();
            if(context.getAttribute("perPageSum")==null){
            	context.setAttribute("perPageSum", 4);
            }
            try {
            	 TeacherDAO teacher = new TeacherDAO();
			     int teacherSum = teacher.teacherSum();
		         int perPageSum = (Integer)context.getAttribute("perPageSum");
		         int pageSum = (int)Math.ceil(1.0*teacherSum/perPageSum);
		         int currentPage;
		         if(request.getParameter("currentPage")==null){
		        	currentPage = 1; 
		         }else{
		        	 currentPage = Integer.parseInt(
		 		            request.getParameter("currentPage")
		 		            );
		         }
		         
		         int start = perPageSum * (currentPage-1)+1;
		         int end = perPageSum * currentPage;
		         if(currentPage==pageSum){
		        	 end = teacherSum;
		         }  
		         List<Teacher> list = teacher.
		        		 perpageTeacherInfo(start, end);
				       
		         request.setAttribute("perpageClassInfo", list);
		         request.setAttribute("currentPage", currentPage);
		         request.setAttribute("firstPage", 1);
		         request.setAttribute("pageSum", pageSum);
		         request.setAttribute("teacherSum", teacherSum);
		         request.getRequestDispatcher("teacher.jsp").
					 forward(request, response);
			  } catch (Exception e) {
					e.printStackTrace();
			  }

          }else if(str.equals("/queryTeacher")){
        	  String teacherName = request.getParameter("teacherName");
        	  if(teacherName.trim().equals("")){
        		  request.setAttribute("queryTip", "教师名不能为空");
        		  request.getRequestDispatcher("teacher.teacher?deleteTip=-1").
        		     forward(request, response);
        		  return;
        	  }
        	  try {
				Teacher teacher = new TeacherDAO().
						 findTeacherByTeachername(teacherName);
				if(teacher==null){
					request.setAttribute("queryTip", "没有这个教师名");
	        		request.getRequestDispatcher("teacher.teacher?deleteTip=-1").
	        		    forward(request, response);
	        		return;
				}
				List<Teacher> list = new ArrayList<Teacher>();
				list.add(teacher);
				request.setAttribute("perpageClassInfo", list);
				request.setAttribute("currentPage", 1);
				request.setAttribute("firstPage", 1);
		        request.setAttribute("pageSum", 1);
		        request.setAttribute("couseSum", 1);
				request.getRequestDispatcher("teacher.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
          }else if(str.equals("/addTeacher")){
        	  String teacherName = request.getParameter("teacherName");
        	  
        	  if(teacherName.trim().equals("")){
        		  request.setAttribute("addTeacherTip", "教师名不能为空");
        		  request.getRequestDispatcher("addTeacher.jsp").
        		     forward(request, response);
        		  return;
        	  }
        	  	   
			try {
				Teacher teacher = new TeacherDAO().
							 findTeacherByTeachername(teacherName);
				if(teacher!=null){
						request.setAttribute("addTeacherTip", "教师名冲突");
		        		request.getRequestDispatcher("addTeacher.jsp").
		        		    forward(request, response);
		        		return;
				 }
				 teacher = new Teacher();
				 teacher.setName(teacherName);
				 
				 new TeacherDAO().addTeacher(teacher);
				 response.sendRedirect("teacher.teacher?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	  
          }else if(str.equals("/deleteTeacher")){
        	  String teacherName = request.getParameter("teacherName");
        	  
        	  teacherName = new String(teacherName.getBytes("iso-8859-1"),"utf-8");
			  Teacher teacher = null;
			try {
				teacher = new TeacherDAO().
							 findTeacherByTeachername(teacherName);
				 new TeacherDAO().deleteTeacher(teacherName);
				 response.sendRedirect("teacher.teacher?deleteTip=-1");
			} catch (Exception e) {
				session1.setAttribute("id", teacher.getId());
				response.sendRedirect("teacher.teacher?deleteTip=1");
        		return;
			}        	  
          }else if(str.equals("/updateTeacher")){
        	  String teacherName = request.getParameter("teacherName");
        	  String newTeacherName = request.getParameter("newTeacherName");
        	  String newTeacherCredit = request.getParameter("newTeacherCredit");
        	  if(teacherName.trim().equals("")){
        		  request.setAttribute("teacherName1", teacherName);
        		  request.setAttribute("newTeacherName", newTeacherName);
        		  request.setAttribute("updateteacherTip", "教师名不能为空");
        		  request.getRequestDispatcher("updateTeacher.jsp").
        		     forward(request, response);
        		  return;
        	  }
        	  if(newTeacherName.trim().equals("")){
        		  request.setAttribute("teacherName1", teacherName);
        		  request.setAttribute("newTeacherName", newTeacherName);
        		  request.setAttribute("updateteacherTip1", "教师名不能为空");
        		  request.getRequestDispatcher("updateTeacher.jsp").
        		     forward(request, response);
        		  return;
        	  }
			  
			try {
				Teacher oldteacher = new TeacherDAO().
							 findTeacherByTeachername(teacherName);
				if(oldteacher==null){
	        		    request.setAttribute("teacherName1", teacherName);
	        		    request.setAttribute("newTeacherName", newTeacherName);
						request.setAttribute("updateteacherTip", "教师名不存在");
		        		request.getRequestDispatcher("updateTeacher.jsp").
		        		    forward(request, response);
		        		return;
				 }
				Teacher newTeacher = new TeacherDAO().
						 findTeacherByTeachername(newTeacherName);
			    if(newTeacher!=null){
	        		request.setAttribute("teacherName1", teacherName);
	        		request.setAttribute("newTeacherName", newTeacherName);
					request.setAttribute("updateteacherTip1", "教师名已存在");
	        		request.getRequestDispatcher("updateTeacher.jsp").
	        		    forward(request, response);
	        		return;
			     }
			     oldteacher.setName(newTeacherName);
			     
				 new TeacherDAO().updateTeacher(oldteacher);
				 response.sendRedirect("teacher.teacher?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}        	          	  
        	  
          }else if(str.equals("/teacherInfo")){
              String teacherId = request.getParameter("teacherId");
              try {
             HttpSession session = request.getSession();
   			 List<CourseInfo> teacherInfo = new TeacherDAO().
   					 findCourseScheduleByCourseId(teacherId);
   			
   			session = request.getSession();
   			session.setAttribute("teacherInfo", teacherInfo);
   			
   			request.getRequestDispatcher("teacherInfo.jsp").
      	      forward(request, response); 
   		} catch (Exception e) {
   			e.printStackTrace();
   		}        	
          }
	    }

}
