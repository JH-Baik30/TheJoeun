<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Scripting Tag</title>
	</head>
	<body>
		<%-- <% for (int i = 0; i <= 10; i++) {
			if (i % 2 == 0) 
				out.println(i + "<br>");
			}
		%>
			<!-- 연습문제) 구구단 7 -->
			
		<% for (int i = 1; i <= 9; i++) {
			out.println(7 * i+"<br>");
		}
		%>	--%>
		
		<% for (int i = 1; i <= 9; i++) {
			for (int j = 2; j <= 9; j++) {
				out.println(j + " * " + i + " = " + j * i + "\t\t");
			}
			out.println("<br>");
		}
		%>
						
	</body>
</html>
<!-- HTML주석  -->
<%-- JSP주석 --%>