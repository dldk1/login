<%@page import="com.devil.vo.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	//사용자 리스트를 담은 리스트를 불러 오자	
	ArrayList<UserVO> getUserList = (ArrayList<UserVO>) request.getAttribute("userList");
	// AdminServlet에 저장시킨 getUsetList를 받아 올 수 있다.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>관리자 페이지</title>
</head>
<body>
	

<div class="container">
	<h1>관리자 페이지</h1>
	관리자만 보여져야함.
	<br> 로그인한 사용자를 관리자라고 생각하면
	<br> 로그인한 사람만 여기 페이지가 보여지게끔 해야함
	<br>

	<a href="<%=path%>/logout.devil">로그아웃</a><br>
  <h2>회원정보</h2>          
  <table class="table table-hover">
    <thead>
      <tr>
        <th>U_IDX</th>
        <th>이름</th>
        <th>비밀번호</th>
        <th>Email</th>
        <th>전화번호</th>        
      </tr>
    </thead>
    <tbody>
        <%
		for (UserVO vo : getUserList) {
	%>
       <tr>    
        <td><%=vo.getU_idx()%></td>
        <td><%=vo.getName()%></td>
        <td><%=vo.getPw()%></td>
        <td><%=vo.getEmail()%></td>
        <td><%=vo.getPhone()%></td>    
      </tr> 
    <%
		}
	%>
    </tbody>
  </table>
</div>

</body>
</html>