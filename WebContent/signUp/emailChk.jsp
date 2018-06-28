<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	boolean idExist = (Boolean) request.getAttribute("idExist");
	String email = (String) request.getAttribute("control-email");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function winClose() {
		opener.document.myForm.control-email.value = "<%=email%>";
		window.close();
		//self.close();
		//close();
	}
</script>
</head>
<body>
<section id="wrop">
		<%
			if (!idExist) {
		%>
		<section id="idUseAbleArea">
			<h1>사용 가능한 아이디입니다.</h1>
			<a href="javaScript:winClose()">닫기</a>
		</section>
		<%
			} else {
		%>
		<section id="idUseDisableArea"></section>
		<h1>이미 있는 아이디입니다. 다른 아이디를 입력하세요</h1>
		<form action="idCheck">
			<label for="customer_id">아이디 : </label><input type="text" name="customer_id" id="customer_id"><br><br>
			<input type="submit" value="전송"> 
		</form>
		<%
			}
		%>
	</section>
</body>
</html>