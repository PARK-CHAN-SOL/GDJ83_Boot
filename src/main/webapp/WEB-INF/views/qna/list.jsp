<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1><spring:message code="board.name"></spring:message></h1>

	<table>
		<thead>
			<tr>
				<th>Num</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Contents</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="qnaVO">
				<tr>
					<td>${qnaVO.boardNum}</td>
					<td><a href="./detail?boardNum=${qnaVO.boardNum}">${qnaVO.boardTitle}</a></td>
					<td>${qnaVO.boardWriter}</td>
					<td>${qnaVO.boardContents}</td>
					<td>${qnaVO.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="./add">Add</a>
</body>
</html>