<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/signUp/style.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<title>회원가입</title>
</head>
<body>
	<script>
        console.log("<%=path%>/signup/script.js");
	</script>
	<form class="grop-from" id="signup">
		<div class="form-head">
			<span class="text"> </span><i class="icon-placeholder"></i>
		</div>
		<div class="form-body">
			<span class="error-text">Please Fill Out This Field</span>
			<div class="form-controls">
				<input id="control-name" placeholder="Name" /> <input
					id="control-phone" placeholder="Phone No" /> <input
					id="control-email" placeholder="Email" /> <input
					id="control-password" placeholder="Password" type="password" /> <input
					id="control-password-repeat" placeholder="Confirm Password"
					type="password" />
			</div>
		</div>
		<a class="form-action"><i class="icon-action"></i></a>
	</form>
</body>
<script src="<%=path%>/signUp/script.js"></script>
<script type="text/javascript">
	$('.form-head').click(function() {

		if ($(this).closest('.grop-from').attr('id') == 'signup') {
			$('.grop-from').attr('id', 'name');
			$('.icon-action').addClass('back');
		} else if ($(this).closest('.grop-from').attr('id') == 'success') {			
			$('.grop-from').attr('id', 'signup');
			
				sendData("<%=path%>");		
	
			//location.href="<%=path%>/login.devil";
		}
	});
	function sendData(path) {

		var name = $('#control-name').val();
		var phone = $('#control-phone').val();
		var email = $('#control-email').val();
		var pw = $('#control-password').val();
		var pwChk = $('#control-password-repeat').val();
		var emailChk = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var phoneChk = /^\d{3}-\d{3,4}-\d{4}$/;
		
		if(name.length=0){
			alert("빈 칸을 채워주세요.")
			return;
		}
		
		if (pw != pwChk) {
			alert("패스워드 확인하세요.")
			return;
		}	

		if (!emailChk.test(email)) {
			alert("이메일을 입력하세요.");
			return;
		}		
				
		var postUrl = path + "/join_us_proc.devil"
		$.post(postUrl, //form 의 action 역할
		{ // request.getParameter로 받는 것 , input의 name

			name : name,
			phone : phone,
			email : email,
			pw : pw

		},

		function(data, status) {
			console.log("status : " + status);
			console.log("data : " + data);
			if (status.trim() == "success" && data.trim() == "OK") {
				console.log("데이터 저장 성공");
			} else {
				console.log("데이터 저장 실패");
			}
			//alert("Data: " + data + "\nStatus: " + status);
		});

	}
</script>
</html>