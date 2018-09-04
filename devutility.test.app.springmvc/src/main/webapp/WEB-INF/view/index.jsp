<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>/home/index</title>
</head>
<body>
	<h2>Hello Spring MVC! Welcome</h2>
	<h3>message: ${message}</h3>
	<h3><%=request.getAttribute("message") %></h3>
</body>
</html>