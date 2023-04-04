<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Scripting Tag</title>
	</head>
	<body>
		<p> Today's date: <%=new java.util.Date() %></p>
		<%="Today's date: " + new java.util.Date() %>
	</body>
</html>