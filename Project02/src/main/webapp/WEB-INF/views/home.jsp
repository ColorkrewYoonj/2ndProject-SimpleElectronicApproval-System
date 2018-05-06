<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>SCIMASTER 사내 결제 시스템</h1>
<div>
<form action="login" method="post">
	<table>
		<tr>
			<td>아이디 : </td>
			<td><input type="text" id = "employee_id" name = "employee_id"></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" id = "password" name = "password"></td>
		</tr>
		<tr>
			<td><input type="submit" value = "로그인"></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
