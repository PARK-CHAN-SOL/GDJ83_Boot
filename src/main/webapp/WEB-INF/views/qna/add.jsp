<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Qna Add</h1>
	<form:form modelAttribute="qnaVO" method="post" enctype="multipart/form-data">
		<form:input path="boardTitle"/> <!-- path: VO의 setter/getter이름 --><br>
		<form:errors path="boardTitle"></form:errors><br>
		<form:input path="boardWriter"/><br>
		<form:errors cssStyle="" path="boardWriter"></form:errors><br>
		<form:textarea path="boardContents"/><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<form:button>Add</form:button><br>
	</form:form>
</body>
</html>