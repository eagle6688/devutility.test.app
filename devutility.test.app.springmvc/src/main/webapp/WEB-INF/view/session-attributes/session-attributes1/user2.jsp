<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>session-attributes1 user2</title>
</head>
<body>
	<h3>/sessionattributes/sessionattributes1/user2</h3>
	<div>
		<p>user in request</p>
		<p>name: ${requestScope.user2.name}</p>
		<p>age: ${requestScope.user2.age}</p>
		<p>id: ${requestScope.user2.id}</p>
	</div>
	<div>
		<p>user in session</p>
		<p>name: ${sessionScope.user.name}</p>
		<p>age: ${sessionScope.user.age}</p>
		<p>id: ${sessionScope.user.id}</p>
	</div>
</body>
</html>