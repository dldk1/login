$('.form-action').click(function() {

	var form_id = $('.grop-from').attr('id');
	$('.icon-action').addClass('back');

	if ($('#control-' + form_id).val() != '') {
		console.log(form_id);
		switch (form_id) {
		case 'name':
			form_id = "phone";
			break;
		case "phone":
			console.log("폰입력창");
			form_id = "email";
			break;
		case "email":
			console.log("이메일 입력후 클릭을 하면 여기로 들어옴");
			var email = $('#control-' + form_id).val();
			console.log('사용자가 입력한 내용 : ' + email);
			var aa = email_check(email);
			console.log("사용여부 : " + aa);
			if (aa == false) {
				$('#err-text').html('중복된 이메일이 있습니다.');
				form_id = "email";
			} else {
				$('#err-text').html('');
				form_id = "password";
			}

			break;
		case "password":
			form_id = "password-repeat";
			break;
		case "password-repeat":
			form_id = "success";
			break;
		case "success":
			form_id = "signup";
			break;
		}
		$('.icon-action').addClass('back');

	}

	else {

		switch (form_id) {
		case 'name':
			form_id = "signup";
			$('.icon-action').removeClass('back');
			break;
		case "phone":
			form_id = "name";
			break;
		case "email":
			form_id = "phone";
			break;
		case "password":
			form_id = "email";
			break;
		case "password-repeat":
			form_id = "password";
			break;
		case "success":
			form_id = "signup";
			break;
		}
		$('.icon-action').removeClass('back');
	}

	$('.grop-from').attr('id', form_id);

});

$('input').keyup(function() {
	$('.grop-from').removeClass('error');
	$('.error-text').fadeOut();

	if ($(this).val() != '') {
		$('.icon-action').removeClass('back');
	} else {
		$('.icon-action').addClass('back');
	}

})

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
}

function email_check(id) {
	var isEmail=false;
	var path = getContextPath();
	console.log(path);
	$.ajax({
		type : 'POST',
		url : path + '/emailCheck.devil',
		data : {
			"id" : id
		},

		success : function(data) {
			console.log(data);
			if ($.trim(data) == "OK") {
				console.log('사용불가');
				isEmail = false;
				// $('#checkMsg').html('<p style="color:blue">사용가능</p>');
			} else {
				isEmail = true;
				// $('#checkMsg').html('<p style="color:red">사용불가능</p>');
			}
		},
		async : false
	}); // end ajax
	return isEmail;
}