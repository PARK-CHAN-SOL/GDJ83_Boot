<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="메롱" src="/images/내루미_진화_(1).gif">

	<c:if test="${empty member}">
		<h1>로그인 안해써여</h1>
		<a href="/member/login">
			<button>login</button>
		</a>
	</c:if>
	<c:if test="${not empty member}">
		<h1>로그인 돼써여</h1>
		<a href="/member/logout">
			<button>logout</button>
		</a>
		<c:forEach items="${member.roles}" var="roleVO">
			<h3>당신 ${roleVO.roleName}</h3>
		</c:forEach>
	</c:if>

	<a href="/qna/list">
		<button>qna list</button>
	</a>
</body>
</html>