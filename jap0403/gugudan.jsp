<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>GuGuDan</title>
</head>
<body>
    <center>
        <h1>구구단</h1>
        <table border=1 cellpadding=0 cellspacing=2 width=500 >
            <%
                for (int i = 0; i < 10; i++) {
                    %>
            <tr align= center >
                <%  
                    for (int j = 1; j < 10; j++) {
                        %>
                <% 
                        if(i ==0 && j==1){
                            %><td align= center bgcolor=pink></td>
                <%
                        }else if(i==0){
                            %>
                <td bgcolor=pink><%= j+ "단" %></td>
                <%
                        } else if(j==1){
                            %>
                <td width = 700 align= center bgcolor=pink><%= i %></td>
                <%
                        }else {
                            %>
                <td align= center><%= j+"X"+i+"="+j*i %></td>
                <% 
                        }%>
                <%
                    }
                    %>
            </tr>
            <%
                }
            %>
        </table>
    </center>
</body>
</html>