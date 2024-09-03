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
					<td>${qnaVO.boardTitle}</td>
					<td>${qnaVO.boardWriter}</td>
					<td>${qnaVO.boardContents}</td>
					<td>${qnaVO.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>