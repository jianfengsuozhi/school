package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import dao.CourseDAO;
import dao.CourseScheduleDAO;
import dao.MarkDAO;
import dao.StudentDAO;
import dao.TeamDAO;
import entity.Course;
import entity.CourseSchedule;
import entity.Mark;
import entity.Student;
import entity.Team;

public class MarkServlet extends HttpServlet{
	
	
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
        
        if(str.equals("/mark")){
        	ServletContext context = getServletContext();
            if(context.getAttribute("perPageSum")==null){
            	context.setAttribute("perPageSum", 4);
            }
            try {
           	 	 Common common = new Common();
			     int recordSum = common.recordSum("mark");
		         int perPageSum = (Integer)context.getAttribute("perPageSum");
		         int pageSum = (int)Math.ceil(1.0*recordSum/perPageSum);
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
		        	 end = recordSum;
		         } 
		         
		         MarkDAO markDao = new MarkDAO();
		         List<Mark> list = markDao.list(start, end);
		         CourseDAO courseDAO = new CourseDAO();
		         List<Course> courseList = courseDAO.list();
		         request.setAttribute("courseList", courseList);
		         
		         StudentDAO studentDao = new StudentDAO();
		         List<Student> studentList = studentDao.list();
		         request.setAttribute("studentList", studentList);
		         
		         TeamDAO teamDAO = new TeamDAO();
		         List<Team> teamList = teamDAO.list();
		         request.setAttribute("teamList", teamList);
		         
		         for (Mark mark : list) {
		        	 Course course = courseDAO.findNameByCourseId(mark.getCourseId());
					 mark.setCourseName(course.getName());
					 
					 Student student = studentDao.findById(mark.getStudentId());
					 mark.setCode(student.getCode());
					 mark.setStudentName(student.getName());
					 
					 Team team = teamDAO.findNameByTeamId(student.getTeamId());
					 mark.setTeamName(team.getName());
				}
		         request.setAttribute("perpageClassInfo", list);
		         request.setAttribute("currentPage", currentPage);
		         request.setAttribute("firstPage", 1);
		         request.setAttribute("pageSum", pageSum);
		         request.setAttribute("courseSum", recordSum); 
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        	request.getRequestDispatcher("mark.jsp").forward(request, response);
        }else if(str.equals("/query")){
        	String teamId = request.getParameter("teamId");
        	String courseId = request.getParameter("courseId");;
        	String studentId = request.getParameter("studentId");
        	
        	Boolean isStudent = !"-1".trim().equals(studentId);
        	Boolean isCourse = !"-1".trim().equals(courseId);
        	Boolean isTeam = !"-1".trim().equals(teamId);
        	
        	if(!isStudent && !isCourse && !isTeam){
        		response.sendRedirect("mark.mark");
        		return;
        	}
        	
        	MarkDAO markDAO = new MarkDAO();
        	List<Mark> list = new ArrayList<Mark>();
        	List<Mark> markList;
			try {
				markList = markDAO.listAll();
				for (Mark mark : markList) {
	        		 String studentId2 = mark.getStudentId();
	        		 if(isStudent && !studentId2.equals(studentId)){
	        			 continue;
	        		 }
	        		 
	        		 String courseId2 = mark.getCourseId();
	        		 if(isCourse && !courseId2.equals(courseId)){
	        			continue; 
	        		 }
	        		 StudentDAO studentDao = new StudentDAO();
	        		 Student student = studentDao.findById(studentId2);
	        		 mark.setCode(student.getCode());
	        		 mark.setStudentName(student.getName());
	        		 
	        		 String teamId2 = student.getTeamId();
	        		 if(isTeam && !teamId2.equals(teamId)){
	        			 continue;
	        		 }
	        		 TeamDAO teamDAO = new TeamDAO();
	        		 Team team;
					 team = teamDAO.findNameByTeamId(teamId2);
					 mark.setTeamName(team.getName());
					
		        	 CourseDAO courseDAO = new CourseDAO();
		        	 Course course;
					 course = courseDAO.findNameByCourseId(courseId2);
					 mark.setCourseName(course.getName());
					
					list.add(mark);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        	
			try {
				CourseDAO courseDAO = new CourseDAO();
				List<Course> courseList = courseDAO.list();
				request.setAttribute("courseList", courseList);
				
				 StudentDAO studentDao = new StudentDAO();
		         List<Student> studentList = studentDao.list();
		         request.setAttribute("studentList", studentList);
		         
		         TeamDAO teamDAO = new TeamDAO();
		         List<Team> teamList = teamDAO.list();
		         request.setAttribute("teamList", teamList);
			} catch (Exception e) {
				e.printStackTrace();
			}
	         
			request.setAttribute("perpageClassInfo", list);
			request.setAttribute("currentPage", 1);
			request.setAttribute("firstPage", 1);
	        request.setAttribute("pageSum", 1);
	        request.setAttribute("courseSum", list.size());
			request.getRequestDispatcher("mark.jsp").
			forward(request, response);
        }else if(str.equals("/add")){
        	String studentId = request.getParameter("studentId");
        	String courseId = request.getParameter("courseId");
        	String score = request.getParameter("score");
        	MarkDAO markDAO = new MarkDAO();
        	Boolean exits = null;
			try {
				exits = markDAO.isExits(studentId, courseId);
				if(exits){
					  request.setAttribute("tip", "成绩信息已存在");
	        		  request.getRequestDispatcher("toAdd.mark").
	        		     forward(request, response);
	        		  return;
				}
				markDAO.insert(score, studentId, courseId);
				request.getRequestDispatcher("mark.mark").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/delete")){
        	String id = request.getParameter("id");
        	MarkDAO markDAO = new MarkDAO();
        	try {
				markDAO.delete(id);
				request.getRequestDispatcher("mark.mark").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/toAdd")){
	        
			try {
				CourseDAO courseDAO = new CourseDAO();
		        List<Course> courseList;
				courseList = courseDAO.list();
		        request.setAttribute("courseList", courseList);
		         
		        StudentDAO studentDao = new StudentDAO();
		        List<Student> studentList = studentDao.list();
		        request.setAttribute("studentList", studentList);
				request.getRequestDispatcher("addMark.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

        }else if(str.equals("/toUpdate")){
        	String id = request.getParameter("id");
        	String courseName = request.getParameter("courseName");
        	courseName = new String(courseName.getBytes("ISO-8859-1"),"UTF-8");
        	String teamName = request.getParameter("teamName");
        	teamName = new String(teamName.getBytes("ISO-8859-1"),"UTF-8");
        	String code = request.getParameter("code");
        	String studentName = request.getParameter("studentName");
        	studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");
        	String score = request.getParameter("score");
        	try {
        		request.setAttribute("id", id);
        		request.setAttribute("courseName", courseName);
        		request.setAttribute("teamName", teamName);
        		request.setAttribute("code", code);
        		request.setAttribute("studentName", studentName);
        		request.setAttribute("score", score);
				request.getRequestDispatcher("updateMark.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/update")){
        	String id = request.getParameter("id");
        	String score = request.getParameter("score");
        	MarkDAO markDAO = new MarkDAO();
        	try {
				markDAO.update(id, score);
				response.sendRedirect("mark.mark");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/course")){
        	String courseId = request.getParameter("courseId");
        	MarkDAO markDAO = new MarkDAO();
        	List<Mark> list;
			try {
				list = markDAO.findByCourseId(courseId);
	        	CourseDAO  courseDAO = new CourseDAO();
	        	StudentDAO studentDAO = new StudentDAO();
	        	TeamDAO teamDAO = new TeamDAO();
	        	for (Mark mark : list) {
		        	 Course course = courseDAO.findNameByCourseId(mark.getCourseId());
					 mark.setCourseName(course.getName());
					 
					 Student student = studentDAO.findById(mark.getStudentId());
					 mark.setCode(student.getCode());
					 mark.setStudentName(student.getName());
					 
					 Team team = teamDAO.findNameByTeamId(student.getTeamId());
					 mark.setTeamName(team.getName());
				}
	        	request.setAttribute("list", list);
	        	request.setAttribute("courseId", courseId);
	        	request.getRequestDispatcher("collectMark.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

        }
	}

}
