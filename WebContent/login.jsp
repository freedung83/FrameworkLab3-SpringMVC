<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="message"/></title>
</head>
<body>
<center>
<h1><spring:message code="message"/></h1>
<a href="loginView.do?lang=en">English</a>
<a href="loginView.do?lang=ko">Korea</a>
<hr>
<form action="login.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td><spring:message code="message.user.EMPLOYEE_NUMBER"/></td>
			<td><input type="text" name="EMPLOYEE_NUMBER"/></td>
		</tr>
		<tr>
			<td><spring:message code="message.user.EMPLOYEE_NAME"/></td>
			<td><input type="password" name="EMPLOYEE_NAME"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="<spring:message code="message"/>"/>
			</td>
		</tr>
	</table>
</form>
<hr>
</center>
</body>
</html>