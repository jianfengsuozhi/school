package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ImportExcel;
import common.Validate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.CourseDAO;
import dao.MarkDAO;
import dao.StudentDAO;
import dao.TeamDAO;
import entity.Course;
import entity.Mark;
import entity.Student;
import entity.Team;

public class AjaxServlet extends HttpServlet{
	@Override
	public  void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
        String str = uri.substring(
       		 uri.lastIndexOf("/"), 
       		 uri.lastIndexOf("."));
        response.setContentType("text/html;charset=utf-8");
        if(str.equals("/courseList")){
        	CourseDAO courseDAO = new CourseDAO();
        	List<Course> list = null;
        	try {
				list = courseDAO.list();
				JSONArray fromObject = JSONArray.fromObject(list);
				response.getWriter().write(fromObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(str.equals("/scholarship")){
        	
        }else if(str.equals("/export")){
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
	        	ImportExcel.importE(list);
	        	ImportExcel.download(ImportExcel.path, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

        }else if(str.equals("/phone")){
        	 String phone = request.getParameter("phone");
        	 String str1 = Validate.validatePhone(phone);
   			 Map<String,String> map = new HashMap<>();
   			 map.put("str", str1);
   			 JSONObject fromObject = JSONObject.fromObject(map);
   			 response.getWriter().write(fromObject.toString());
	   		
        }else if(str.equals("/email")){
        	String email = request.getParameter("email");
       	 	String str1 = Validate.validateEmail(email);
  			 Map<String,String> map = new HashMap<>();
  			 map.put("str", str1);
  			 JSONObject fromObject = JSONObject.fromObject(map);
  			 response.getWriter().write(fromObject.toString());
        }
	}
}
