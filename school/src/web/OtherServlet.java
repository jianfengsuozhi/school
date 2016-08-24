package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class OtherServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		HttpSession session = request.getSession();
		if (uri.equals("/login")) {
			String name = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
		
			if (name.equals("")) {
				request.setAttribute("msg", "用户名不能为空");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;
			}
			if (pwd.equals("")) {
				request.setAttribute("msg", "密码不能为空");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;
			}
			try {
				UserDAO dao = new UserDAO();
				User user = dao.findByUserName(name);
				if (user == null||!user.getUserPwd().equals(pwd)) {
					request.setAttribute("msg", "用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
					return;
				}
                
				int uId = user.getUserId();
				session.setAttribute("uid", uId);
				session.setAttribute("uname", name);

				// 重定向到发帖页面
				response.sendRedirect("home.jsp");

			} catch (Exception e) {
				
			}

		} else if (uri.equals("/register")) {

			// 获取表单数据封装成对象
			String pname = request.getParameter("pname");
			String pwd = request.getParameter("pwd");
			String ppwd = request.getParameter("ppwd");
			String email = request.getParameter("email");
			
			// 获取输入的验证码，比对验证码
			String code = request.getParameter("number").toUpperCase();
			
			String number = (String) session.getAttribute("c_msg");
			
			if (!code.equals(number.toUpperCase())) {
				// 绑定提示信息
				request.setAttribute("c_msg", "验证码错误");
				// 转发到register.jsp
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else if (pwd.trim().equals("") || !pwd.equals(ppwd)) {
				request.setAttribute("pwd", "密码不能为空或者不正确");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else if (pname.trim().equals("")) {
				request.setAttribute("pname", "用户名不能为空");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else {
				try {
					UserDAO userDAO = new UserDAO();
					User user = userDAO.findByUserName(pname);
					if (user != null) {
						request.setAttribute("pname", "用户名重复");
						request.getRequestDispatcher("register.jsp").forward(
								request, response);
						return;
					} else {
						user = new User();
						user.setUserName(pname);
						user.setUserPwd(pwd);
						user.setUserEmail(email);
						userDAO.save(user);
						response.sendRedirect("login.jsp");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (uri.equals("/exit")) {
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}
}
