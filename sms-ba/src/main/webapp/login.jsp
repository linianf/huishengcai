<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta content="telephone=no" name="format-detection" />
	<title>后台登陆</title>
</head>
<body>
  <c:if test="${not empty error}">
		<center><font color="red">${error}</font></center>
  </c:if>
  <form action="${pageContext.request.contextPath}/hsh/sms/login.html"  method="post">
  <center>用户名：<input type="text" name="username" id="username" /></center><br>
  <center>密码：    <input type="password" name="password" id="password" /></center><br>
     <center> <input type="submit" value="登陆"/></center>
  </form>
  
</body>
</html>
