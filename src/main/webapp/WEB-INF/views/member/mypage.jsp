<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>My Page</h1>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user"/>
		<h3>id: ${user.username}</h3>
		
		<h3>name: <sec:authentication property="name"/></h3>
		
		<sec:authentication property="principal.email" var="email"/>
		<h3>email: ${email}</h3>
	</sec:authorize>
	
	<a href="./update"><button>수정</button></a>

</body>
</html>