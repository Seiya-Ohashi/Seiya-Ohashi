<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP KADAI</title>
</head>
<body>
	<%!
		int count = 0;
	%>
	<h1>訪問回数:<%= count++ %></h1>
	<p>今日の日付:
	<%
		SimpleDateFormat today = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		out.print(today.format(date));
	%>
	<p/>
</body>
</html>