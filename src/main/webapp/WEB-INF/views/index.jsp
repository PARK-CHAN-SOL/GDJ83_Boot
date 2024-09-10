<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="메롱" src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1617087525/noticon/uedixpon6rcvcqbtuf0c.gif">

	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="기본값"></spring:message>

	<c:if test="${empty member}">
		<h1>로그인 안해써여</h1>
		<a href="/member/login">
			<button>login</button>
		</a>
	</c:if>
	<c:if test="${not empty member}">
		<h1>로그인 돼써여</h1>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<a href="/member/logout">
			<button>logout</button>
		</a>
		<a href="/member/mypage">
			<button>mypage</button>
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