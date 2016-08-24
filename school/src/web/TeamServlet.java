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

import dao.CourseScheduleDAO;
import dao.TeamDAO;
import entity.Course;
import entity.CourseInfo;
import entity.Team;

public class TeamServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
        String str = uri.substring(
       		 uri.lastIndexOf("/"), 
       		 uri.lastIndexOf("."));
        HttpSession session = request.getSession();
        String uname = (String)session.getAttribute("uname");
        if(uname==null){
        	response.sendRedirect("login.jsp");
        	return;
        }
        if(str.equals("/home")){
        	request.getRequestDispatcher("home.jsp").
			   forward(request, response);
        }else if(str.equals("/team")){
        	String deleteTip = request.getParameter("deleteTip");
        	if("-1".equals(deleteTip)){
        		request.setAttribute("deleteTip", "");
        	}else{
        		request.setAttribute("deleteTip", "无法删除该班级");
        	}
        	ServletContext context = getServletContext();
            if(context.getAttribute("perPageSum")==null){
            	context.setAttribute("perPageSum", 4);
            }
            try {
            	 TeamDAO team = new TeamDAO();
			     int classSum = team.classSum();
		         int perPageSum = (Integer)context.getAttribute("perPageSum");
		         int pageSum = (int)Math.ceil(1.0*classSum/perPageSum);
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
		        	 end = classSum;
		         }  
		         List<Team> list = team.
		        		 perpageClassInfo(start, end);
		         request.setAttribute("perpageClassInfo", list);
		         request.setAttribute("currentPage", currentPage);
		         request.setAttribute("firstPage", 1);
		         request.setAttribute("pageSum", pageSum);
		         request.setAttribute("classSum", classSum);
		         request.getRequestDispatcher("team.jsp").
					 forward(request, response);
			  } catch (Exception e) {
					e.printStackTrace();
			  }

          }else if(str.equals("/queryTeam")){
        	  String className = request.getParameter("className");
        	  if(className.trim().equals("")){
        		  request.setAttribute("queryTip", "班级名不能为空");
        		  request.getRequestDispatcher("team.team?deleteTip=-1").
        		     forward(request, response);
        		  return;
        	  }
        	  try {
				Team team = new TeamDAO().
						 findTeamByClassname(className);
				if(team==null){
					request.setAttribute("queryTip", "没有这个班级名");
	        		request.getRequestDispatcher("team.team?deleteTip=-1").
	        		    forward(request, response);
	        		return;
				}
				List<Team> list = new ArrayList<Team>();
				list.add(team);
				request.setAttribute("perpageClassInfo", list);
				request.setAttribute("currentPage", 1);
				request.setAttribute("firstPage", 1);
		        request.setAttribute("pageSum", 1);
		        request.setAttribute("classSum", 1);
				request.getRequestDispatcher("team.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
          }else if(str.equals("/addTeam")){
        	  String className = request.getParameter("className");
        	  
        	  if(className.trim().equals("")){
        		  request.setAttribute("addTeamTip", "课程不能为空");
        		  request.getRequestDispatcher("addTeam.jsp").
        		     forward(request, response);
        		  return;
        	  }
        	 
			try {
				Team team = new TeamDAO().
							 findTeamByClassname(className);
				if(team!=null){
						request.setAttribute("addTeamTip", "班级名冲突");
		        		request.getRequestDispatcher("addTeam.jsp").
		        		    forward(request, response);
		        		return;
				 }
				 team = new Team();
				 team.setName(className);
				 new TeamDAO().addTeam(team);
				 response.sendRedirect("team.team?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	  
          }else if(str.equals("/deleteTeam")){
        	  String className = request.getParameter("className");
        	  className = new String(className.getBytes("ISO-8859-1"),"UTF-8");
			  Team team = null;
			try {
				team = new TeamDAO().
							 findTeamByClassname(className);
				 new TeamDAO().deleteTeam(className);
				 response.sendRedirect("team.team?deleteTip=-1");
			} catch (Exception e) {
				session.setAttribute("teamId", team.getId());
				response.sendRedirect("team.team?deleteTip=1");
        		return;				
			}        	  
          }else if(str.equals("/updateTeam")){
        	  String className = request.getParameter("className");
        	  String newClassName = request.getParameter("newClassName");
        	  if(className.trim().equals("")){
        		  request.setAttribute("className1", className);
        		  request.setAttribute("newClassName", newClassName);
        		  request.setAttribute("updateTeamTip", "班级名不能为空");
        		  request.getRequestDispatcher("updateTeam.jsp?className=").
        		     forward(request, response);
        		  return;
        	  }
        	  if(newClassName.trim().equals("")){
        		  request.setAttribute("className1", className);
        		  request.setAttribute("newClassName", newClassName);
        		  request.setAttribute("updateTeamTip1", "班级名不能为空");
        		  request.getRequestDispatcher("updateTeam.jsp?").
        		     forward(request, response);
        		  return;
        	  }
			  
			try {
				Team oldTeam = new TeamDAO().
							 findTeamByClassname(className);
				if(oldTeam==null){
	        		    request.setAttribute("className1", className);
	        		    request.setAttribute("newClassName", newClassName);
						request.setAttribute("updateTeamTip", "班级名不存在");
		        		request.getRequestDispatcher("updateTeam.jsp").
		        		    forward(request, response);
		        		return;
				 }
				Team newTeam = new TeamDAO().
						 findTeamByClassname(newClassName);
			    if(newTeam!=null){
        		    request.setAttribute("className1", className);
        		    request.setAttribute("newClassName", newClassName);
					request.setAttribute("updateTeamTip1", "班级名已存在");
	        		request.getRequestDispatcher("updateTeam.jsp").
	        		    forward(request, response);
	        		return;
			     }
			     oldTeam.setName(newClassName);
				 new TeamDAO().updateTeam(oldTeam);
				 response.sendRedirect("team.team?deleteTip=-1");
			} catch (Exception e) {
				e.printStackTrace();
			}        	          	  
        	  
          }
		
	    }

}
