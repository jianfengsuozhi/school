package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDAO;
import dao.CourseScheduleDAO;
import dao.TeacherDAO;
import entity.Course;
import entity.CourseInfo;
import entity.CourseSchedule;
import entity.Teacher;

public class CourseScheduleServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
        String str = uri.substring(
       		 uri.lastIndexOf("/"), 
       		 uri.lastIndexOf("."));
        HttpSession session = null;
        HttpSession session1 = request.getSession();
        String uname = (String)session1.getAttribute("uname");
        if(uname==null){
        	response.sendRedirect("login.jsp");
        	return;
        }
        if(str.equals("/courseSchedule")){
           String classId = request.getParameter("classId");
           String className = request.getParameter("className");
           className = new String(className.getBytes("ISO-8859-1"),"UTF-8");
           try {
			List<CourseSchedule> courseScheduleInfo = new CourseScheduleDAO().
					     findCourseScheduleByTeamID(classId,null);
			List<Teacher> list = new TeacherDAO().list();
			session1.setAttribute("classId", classId);
			session1.setAttribute("className", className);
			request.setAttribute("teachers", list);
			request.setAttribute("courseScheduleInfo1", courseScheduleInfo);
			
			request.getRequestDispatcher("course.jsp").
                 forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

        }else if(str.equals("/queryCourseSchedule")){
        	String teacherId = request.getParameter("teacherId");
        	String classId = request.getParameter("classId");
        	List<CourseSchedule> courseScheduleInfo = null;
        	try {
				if("-1".equals(teacherId)){
					courseScheduleInfo = new CourseScheduleDAO().
						     findCourseScheduleByTeamID(classId,null);
				}else{
					courseScheduleInfo = new CourseScheduleDAO().
						     findCourseScheduleByTeamID(classId,teacherId);
				}
				request.setAttribute("courseScheduleInfo1", courseScheduleInfo);
				
				List<Teacher> list = new TeacherDAO().list();
				request.setAttribute("teachers", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("course.jsp").
            forward(request, response);
        }else if(str.equals("/deleteCourseSchedule")){
        	String id = request.getParameter("id");
        	try {
				new CourseScheduleDAO().deleteByCourseScheduleId(id);
				session = request.getSession();
				response.sendRedirect("courseSchedule.courseSchedule?classId="+
				                      session.getAttribute("classId")+"&className="+session.getAttribute("className"));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/addCourseSchedule")){
        	CourseSchedule schedule = new CourseSchedule();
        	String courseId = request.getParameter("courseId");
        	String teacherId = request.getParameter("teacherId");
        	String semester = request.getParameter("semester");
        	String credit = request.getParameter("credit");
        	
        	if(!"-1".equals(courseId)||!"-1".equals(teacherId)||"".equals(credit)){
        		String tip = "课程名称或任课教师或学分不能为空";
        	    response.sendRedirect("add.courseSchedule?tip="+tip);
        	    return;
        	}
        	session = request.getSession();
        	String classId = (String)session.getAttribute("classId");
        	
        	
        	schedule.setCredit(credit);
        	schedule.setSemester(semester);
        	schedule.setTeamId(classId);
        	schedule.setCourseId(courseId);
        	schedule.setTeacherId(teacherId);
        	try {
        		Boolean isExits = new CourseScheduleDAO().isExits(semester, credit, classId, courseId, teacherId);
        		if(isExits){
        			response.sendRedirect("add.courseSchedule?tip='该课程信息已存在'");
        			return;
        		}
				new CourseScheduleDAO().addSchedule(schedule);
				response.sendRedirect("courseSchedule.courseSchedule?classId="+
	                      session.getAttribute("classId")+"&className="+session.getAttribute("className"));				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/add")){
        	try {
        		String tip = request.getParameter("tip");
        		tip = new String(tip.getBytes("ISO-8859-1"),"UTF-8");
				List<Course> courses = new CourseDAO().list();
				request.setAttribute("courses", courses);
				List<Teacher> teachers = new TeacherDAO().list();
				request.setAttribute("teachers", teachers);
				request.setAttribute("tip", tip);
				request.getRequestDispatcher("addCourseSchedule.jsp").
	        	  forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }else if(str.equals("/courseInfo")){
            String courseId = request.getParameter("courseId");
            String courseName = request.getParameter("courseName");
            courseName = new String(courseName.getBytes("iso-8859-1"),"utf-8");
            Course course = new Course(courseId,courseName);
            try {
 			List<CourseInfo> courseInfo = new CourseScheduleDAO().
 					findCourseScheduleByCourseId(course);
 			session = request.getSession();
 			session.setAttribute("courseInfo", courseInfo);
 			request.setAttribute("courseName", courseName);
 			request.getRequestDispatcher("courseInfo.jsp").
    	      forward(request, response); 
 		} catch (Exception e) {
 			e.printStackTrace();
 		}        	
        	
        }
	}

}
