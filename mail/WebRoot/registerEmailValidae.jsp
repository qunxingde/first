<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'registerEmailValidae.jsp' starting page</title>
 
  </head>
  
  <body>
	<h2>注册激活</h2>
    <form action="register?action=register" method="post" >
    账户名:&nbsp;<input type="text" id="acc" name="acc" value="" ><br/>
    密码: &nbsp;  <input type="text" id="pa" name="pa" value="" ><br/>
    确认密码:<input type="text" id="repa" name="repa" value="" ><br/>
    电子邮箱:<input type="text" id="email" name="email" value="" ><br/>
    手机号码:<input type="text" id="phone" name="phone" value="" ><br/>
    <input type="submit" value="提交">
    </form>
  </body>
</html>
