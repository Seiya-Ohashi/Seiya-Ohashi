<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP TASK</title>
</head>
<body>
<%! int substraction(int numA, int numB){
		return numA - numB;
	}
%>
	<p><h1>125 - 15 = <%= substraction(125,15) %></h1><p/>
	<h1>17 - 21 = <%= substraction(17,21) %></h1>
</body>
</html>