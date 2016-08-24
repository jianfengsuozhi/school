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
import dao.StudentDAO;
import dao.TeamDAO;


import entity.CourseInfo;
import entity.Student;
import entity.Student;
import entity.Team;

public class StudentServlet extends HttpServlet {
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
         if(str.equals("/student")){
         	String deleteTip = request.getParameter("deleteTip");
         	if("-1".equals(deleteTip)){
         		request.setAttribute("deleteTip", "");
         	}else{
         		request.setAttribute("deleteTip", "无法删除");
         	}
        	ServletContext context = getServletContext();
            if(context.getAttribute("perPageSum")==null){
            	context.setAttribute("perPageSum", 4);
            }
            try {
            	 StudentDAO student = new StudentDAO();
			     int studentSum = student.studentSum();
		         int perPageSum = (Integer)context.getAttribute("perPageSum");
		         int pageSum = (int)Math.ceil(1.0*studentSum/perPageSum);
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
		        	 end = studentSum;
		         }  
		         List<Student> list = student.
		        		 perpageStudentInfo(start, end);
				       
		         request.setAttribute("perpageClassInfo", list);
		         request.setAttribute("currentPage", currentPage);
		         request.setAttribute("firstPage", 1);
		         request.setAttribute("pageSum", pageSum);
		         request.setAttribute("studentSum", studentSum);
		         request.getRequestDispatcher("student.jsp").
					 forward(request, response);
			  } catch (Exception e) {
					e.printStackTrace();
			  }

          }else if(str.equals("/queryStudent")){
        	  String studentName = request.getParameter("studentName");
        	  if(studentName.trim().equals("")){
        		  request.setAttribute("queryTip", "学生名不能为空");
        		  request.getRequestDispatcher("student.student?deleteTip=-1").
        		     forward(request, response);
        		  return;
        	  }
        	  try {
				Student student = new StudentDAO().
						findStudentByStudentname(studentName);
						 
				if(student==null){
					request.setAttribute("queryTip", "没有这个学生名");
	        		request.getRequestDispatcher("student.student?deleteTip=-1").
	        		    forward(request, response);
	        		return;
				}
				List<Student> list = new ArrayList<Student>();
				list.add(student);
				request.setAttribute("perpageClassInfo", list);
				request.setAttribute("currentPage", 1);
				request.setAttribute("firstPage", 1);
		        request.setAttribute("pageSum", 1);
		        request.setAttribute("studentSum", 1);
				request.getRequestDispatcher("student.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
          }else if(str.equals("/addStudent")){
        	  String code = request.getParameter("code");
        	  String studentName = request.getParameter("name");
        	  String enrollDate = request.getParameter("enrollDate");
        	  String birthday = request.getParameter("birthday");
        	  String sex = request.getParameter("sex");
        	  String teamId = request.getParameter("teamId");
        	  String phone = request.getParameter("phone");
        	  String address = request.getParameter("address");
        	  String nation = request.getParameter("nation");
			try {
				if("".equals(code)||"".equals(studentName)){
					request.setAttribute("teamNameTip", "学号或姓名不能为空");
					request.getRequestDispatcher("toAdd.student").
	        		    forward(request, response); 
					return;
				}
				Student student = new StudentDAO().
							 findStudentByStudentname(studentName);
				 if(student != null){
						request.setAttribute("teamNameTip", "该学生已经存在");
						request.getRequestDispatcher("toAdd.student").
		        		    forward(request, response); 
						return;
				 }
				 student = new Student();
				 student.setName(studentName);
				 student.setBirthday(birthday);
				 student.setCode(code);
				 student.setEnrollDate(enrollDate);
				 student.setSex(sex);
				 student.setTeamId(teamId);
				 student.setAddress(address);
				 student.setPhone(phone);
				 student.setNation(nation);
				 new StudentDAO().addStudent(student);
				 response.sendRedirect("student.student?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	  
          }else if(str.equals("/deleteStudent")){
        	  String studentName = request.getParameter("name");
              studentName = new String(studentName.getBytes("iso-8859-1"),"utf-8");  
        	  
        	  Student student = null;
			try {
				student = new StudentDAO().
							 findStudentByStudentname(studentName);
				if(student==null){
						request.setAttribute("deleteStudentTip", "学生名不存在");
		        		request.getRequestDispatcher("deleteStudent.jsp").
		        		    forward(request, response);
		        		return;
				 }
				 new StudentDAO().deleteStudent(studentName);
				 response.sendRedirect("student.student?deleteTip=-1");
			} catch (Exception e) {
				session1.setAttribute("id", student.getId());
				response.sendRedirect("student.student?deleteTip=1");
        		return;	
			}        	  
          }else if(str.equals("/toAdd")){
        	  try {
        		TeamDAO teamDAO = new TeamDAO();
				List<Team> list = teamDAO.list();
				request.setAttribute("list", list);
        		request.getRequestDispatcher("addStudent.jsp").
    		    forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
          }else if(str.equals("/toUpdate")){
        	  String id = request.getParameter("id");
        	  String code = request.getParameter("code");
        	  String name = request.getParameter("name");
        	  name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
        	  String phone  = request.getParameter("phone");
        	  String address = request.getParameter("address");
        	  address = new String(address.getBytes("ISO-8859-1"),"UTF-8");
        	  String nation = request.getParameter("nation");
        	  nation = new String(nation.getBytes("ISO-8859-1"),"UTF-8");
        	  String birthday = request.getParameter("birthday");
        	  String enrollDate = request.getParameter("enrollDate");
        	  String sex = request.getParameter("sex");
        	  sex = new String(sex.getBytes("ISO-8859-1"),"UTF-8");
        	  String teamId = request.getParameter("teamId");
        	  
        	  TeamDAO teamDAO = new TeamDAO();
        	  try {
				List<Team> list = teamDAO.list();
				request.setAttribute("teamList", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	  request.setAttribute("id", id);
        	  request.setAttribute("code", code);
        	  request.setAttribute("name", name);
        	  request.setAttribute("enrollDate", enrollDate);
        	  request.setAttribute("birthday", birthday);
        	  request.setAttribute("sex", sex);
        	  request.setAttribute("teamId", teamId);
        	  request.setAttribute("phone", phone);
        	  request.setAttribute("address", address);
        	  request.setAttribute("nation", nation);
        	  request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
          }else if(str.equals("/update")){
        	  String id = request.getParameter("id");
        	  String code = request.getParameter("code");
        	  String name = request.getParameter("name");
        	  String birthday = request.getParameter("birthday");
        	  String enrollDate = request.getParameter("enrollDate");
        	  String sex = request.getParameter("sex");
        	  String teamId = request.getParameter("teamId");
        	  String phone = request.getParameter("phone");
        	  String address = request.getParameter("address");
        	  String nation = request.getParameter("nation");
        	  
			try {
				 StudentDAO studentDAO = new StudentDAO();
				 studentDAO.update(id, code, name, enrollDate, birthday, sex, teamId, phone, address, nation);
				 response.sendRedirect("student.student?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
          }else if(str.equals("/detail")){
        	  String id = request.getParameter("id");
        	  StudentDAO studentDAO = new StudentDAO();
        	  Student student = studentDAO.findById(id);
        	  TeamDAO teamDAO = new TeamDAO();
        	  try {
				Team team = teamDAO.findNameByTeamId(student.getTeamId());
				student.setTeamName(team.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	  request.setAttribute("student", student);
        	  request.getRequestDispatcher("detailStudent.jsp").forward(request, response);
          }

	}
}
