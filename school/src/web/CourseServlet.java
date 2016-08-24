package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import dao.CourseDAO;
import dao.TeamDAO;
import entity.Course;
import entity.CourseSchedule;
import entity.Team;

public class CourseServlet extends HttpServlet {
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
          if(str.equals("/courseOperate")){
          	String deleteTip = request.getParameter("deleteTip");
          	if("1".equals(deleteTip)){
          		request.setAttribute("deleteTip", "无法删除该课程");
          	}else{
          		request.setAttribute("deleteTip", "");
          	}
        	  
        	ServletContext context = getServletContext();
            if(context.getAttribute("perPageSum")==null){
            	context.setAttribute("perPageSum", 4);
            }
            try {
            	 CourseDAO course = new CourseDAO();
			     int courseSum = course.courseSum();
		         int perPageSum = (Integer)context.getAttribute("perPageSum");
		         int pageSum = (int)Math.ceil(1.0*courseSum/perPageSum);
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
		        	 end = courseSum;
		         }  
		         List<Course> list = course.
		        		 perpageCourseInfo(start, end);
				       
		         request.setAttribute("perpageClassInfo", list);
		         request.setAttribute("currentPage", currentPage);
		         request.setAttribute("firstPage", 1);
		         request.setAttribute("pageSum", pageSum);
		         request.setAttribute("courseSum", courseSum);
		         request.getRequestDispatcher("courseOperate.jsp").
					 forward(request, response);
			  } catch (Exception e) {
					e.printStackTrace();
			  }

          }else if(str.equals("/queryCourse")){
        	  String courseName = request.getParameter("courseName");
        	  if(courseName.trim().equals("")){
        		  request.setAttribute("queryTip", "课程名不能为空");
        		  request.getRequestDispatcher("courseOperate.course?deleteTip=-1").
        		     forward(request, response);
        		  return;
        	  }
        	  try {
				Course course = new CourseDAO().
						 findCourseByCoursename(courseName);
				if(course==null){
					request.setAttribute("queryTip", "没有这个课程名");
	        		request.getRequestDispatcher("courseOperate.course?deleteTip=-1").
	        		    forward(request, response);
	        		return;
				}
				List<Course> list = new ArrayList<Course>();
				list.add(course);
				request.setAttribute("perpageClassInfo", list);
				request.setAttribute("currentPage", 1);
				request.setAttribute("firstPage", 1);
		        request.setAttribute("pageSum", 1);
		        request.setAttribute("courseSum", 1);
				request.getRequestDispatcher("courseOperate.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
          }else if(str.equals("/addCourse")){
        	  String courseName = request.getParameter("courseName");
        	  
        	  if(courseName.trim().equals("")){
        		  request.setAttribute("addCourseTip", "课程名不能为空");
        		  request.getRequestDispatcher("addCourse.jsp").
        		     forward(request, response);
        		  return;
        	  }
        	  	   
			try {
				Course course = new CourseDAO().
							 findCourseByCoursename(courseName);
				if(course!=null){
						request.setAttribute("addCourseTip", "课程名冲突");
		        		request.getRequestDispatcher("addCourse.jsp").
		        		    forward(request, response);
		        		return;
				 }
				 course = new Course();
				 course.setName(courseName);
				 
				 new CourseDAO().addCourse(course);
				 response.sendRedirect("courseOperate.course?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	  
          }else if(str.equals("/deleteCourse")){
        	  String courseName = request.getParameter("courseName");
        	  courseName = new String(courseName.getBytes("iso-8859-1"),"utf-8");
			  Course course = null;
			try {
				course = new CourseDAO().
							 findCourseByCoursename(courseName);
				 new CourseDAO().deleteCourse(courseName);
				 response.sendRedirect("courseOperate.course?deleteTip=-1");
			} catch (Exception e) {
				session1.setAttribute("courseId", course.getId());
				response.sendRedirect("courseOperate.course?deleteTip=1");
        		return;
			}        	  
          }else if(str.equals("/updateCourse")){
        	  String courseName = request.getParameter("courseName");
        	  String newCourseName = request.getParameter("newCourseName");
        	  
        	  if(courseName.trim().equals("")){
        		  request.setAttribute("courseName1", courseName);
        		  request.setAttribute("newCourseName", newCourseName);
        		  request.setAttribute("updateCourseTip", "课程名不能为空");
        		  request.getRequestDispatcher("updateCourse.jsp").
        		     forward(request, response);
        		  return;
        	  }
        	  if(newCourseName.trim().equals("")){
        		  request.setAttribute("courseName1", courseName);
        		  request.setAttribute("newCourseName", newCourseName);
        		  request.setAttribute("updateCourseTip1", "课程名不能为空");
        		  request.getRequestDispatcher("updateCourse.jsp").
        		     forward(request, response);
        		  return;
        	  }
			  
			try {
				Course oldCourse = new CourseDAO().
							 findCourseByCoursename(courseName);
				if(oldCourse==null){
					    request.setAttribute("courseName1", courseName);
	        		    request.setAttribute("newCourseName", newCourseName);
						request.setAttribute("updateCourseTip", "课程名不存在");
		        		request.getRequestDispatcher("updateCourse.jsp").
		        		    forward(request, response);
		        		return;
				 }
				Course newCourse = new CourseDAO().
						 findCourseByCoursename(newCourseName);
			    if(newCourse!=null){
			    	request.setAttribute("courseName1", courseName);
	        		request.setAttribute("newCourseName", newCourseName);
					request.setAttribute("updateCourseTip1", "课程名已存在");
	        		request.getRequestDispatcher("updateCourse.jsp").
	        		    forward(request, response);
	        		return;
			     }
			     oldCourse.setName(newCourseName);
			    
				 new CourseDAO().updateCourse(oldCourse);
				 response.sendRedirect("courseOperate.course?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}        	          	  
        	  
          }
	    }

}
