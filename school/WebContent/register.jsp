<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
 <html>
  <head>
    <title>regist</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link href="css/register.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

	 	$('#email').blur(function(){
			var email1 = {email:$("#email").val() };
			$.ajax({
				type:"post",
				url:"/school/email.ajax",
				dataType:"json",
				data:email1,
				success:function(data){
					$("#phone1").html("<b>"+data.str+"</b>");
				}
			}); 
	 	});
	});
</script>
     </head>
     <body>
<div class="bac" id="bac">
	<div class="pos" id="pos">
    	<div class="fleft">
        	<h2>欢迎注册</h2>
        	<form action="register.do" method="post">
									<p>注册用户:<input type="text" name="pname" /></p>
									<% 
									   String pname = (String)request.getAttribute("pname");
									 %>
									 <%= pname==null?"":pname %>
									<p>登录密码:<input type="password" name="pwd"/></p>
									<% 
									   String pwd = (String)request.getAttribute("pwd");
									 %>
									 <%= pwd==null?"":pwd %>
									<p>确认密码:<input type="password" name="ppwd"/></p>
									<p>联系邮箱:<input type="text" name="email" id="email"/>
									   <span id="phone1"></span>
									</p>
									<p>验证码:<input type="text" name="number" /></p>
									<p><img id="num" src="code" />
									<a href="#" onclick="document.getElementById('num').src='code?'+new Date().getTime()" style="color:black;">换一张</a>
									</p>
									<%
									  String c_msg = (String)request.getAttribute("c_msg"); 
									 %>
									<%=c_msg==null?"":c_msg %>
            <button>注册</button>
        </div>
        <div class="fright">
        	<p>已有账号从这里<button><a href="login.jsp">登录</a></button></p>
        </div>
        </form>
    </div>
 </div>
</html>