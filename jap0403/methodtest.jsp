<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%!
			int multiple(int a, int b) {
				return a * b;
		}
		%>
			
		<%
			for (int i = 2; i < 10; i++){
				for (int j = 1; j < 10; j++){
		%>
				<%=i + "*" + j + "=" + multiple(i, j)%>	
		<%
				}
				out.println("<br>");
			}
		%>
		
	</body>
</html>