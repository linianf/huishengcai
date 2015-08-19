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
	<title>短信发送</title>
</head>
<body>
  <c:if test="${not empty error}">
		<center><font color="red">${error}</font></center>
  </c:if>
   <form action="${pageContext.request.contextPath}/hsh/sms/sendSms.html"  method="post">
  接收手机号码：<textarea rows="8" id="mobile" name="mobile" style="width:100%;"></textarea>(多个请用半角的冒号分隔一次最多支持200个)<br>
  内容：<textarea rows="8" id="content" name="content" style="width:100%;"></textarea><br>
     <input type="submit" value="发送"/>
   </form>
</body>
</html>
