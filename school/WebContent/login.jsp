<%@ page import="java.util.*" 
		 pageEncoding="UTF-8"
		 contentType="text/html;charset=UTF-8"
%>
<html>
  <head>
    <title>login</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
    	body{margin:0;padding:0;background:url(images/body_bg.png);}
    	#bac{width:1000px;height:650px;background:url(./images/login1.jpg); margin:40px auto;position:relative;}
    	#login{
    	width:420px;height:230px;
    	float:right;
    	background:#fff000;
    	margin-top:210px;
    	margin-right:180px;
    	}
    	#login h1{font-family: '微软雅黑';font-size:30px;color:#48d1cc;padding-left:30px;}
    	.form_table{
    	weight:240px;
    	height:80px;
    	margin-left:60px;
    	cellspacing:20px;
    	}
    	.form_table p{font-family: '微软雅黑';}
    	#msg{color:#f00;}
    	#but{
    	margin-left:120px;
    	background:#00bfff;
    	cursor:pointer;
    	border-radius:6px;
    	padding:5px;
    	border:none;
    	outline:none;
    	}
    	#reg{
    	display:line-block;
    	margin-left:50px;
    	background:#00bfff;
    	cursor:pointer;
    	border-radius:6px;
    	padding:5px;
    	border:none;
    	outline:none;
    	}
    	.role{margin-left:80px;}
    </style>
  </head>
  
  <body>
  	<div id="bac">
  		<div id="login">
  			<h1 >
				登 录
			</h1>
					<form action="login.do" method="post">
						<table class="form_table">
							<tr>
								<td valign="middle" align="right">
									<p>用户:</p>
								</td>
								<td valign="middle" align="left" id="msg">
									<input type="text"  name="uname" />
									<%=request.getAttribute("msg")==null?"" : (String)request.getAttribute("msg")%>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									<p>密码:</p>
								</td>
								<td valign="middle" align="left">
									<input type="password"  name="pwd" />
								</td>
							</tr>
						</table>
						
						<p>
							<input type="submit" id="but" value="登录" />

							<button id="reg"><a href="register.jsp">注册</a></button>
							
						</p>
					</form>
  		</div>
  	</div>
  </body>
</html>
