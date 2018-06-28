<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String name = (String) session.getAttribute("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
</head>
<body>
	<h1>메인페이지</h1>
	<%
		if (name != null) {
	%>
	<%=name%>
	님 환영합니다.
	<br>
	<a href="<%=path%>/admin.devil">관리자 페이지</a><br>
	<a href="<%=path%>/logout.devil">로그아웃</a>
	<%
		} else{		
	%>
	<br>
	
	<a href="<%=path%>/login.devil">로그인</a> <br>
	<a href="<%=path%>/join_us.devil">회원가입</a> 
	<%} %>
</body>
</html>