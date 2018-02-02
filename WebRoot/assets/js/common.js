function isNull(value) {
	if(value == null || value == ""){
        return true;
    }
	return false;
}

function getURLParameter(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}

function autoLeftNav() {
    $('.tpl-header-switch-button').on('click', function() {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').removeClass('active');
            }
            $('.left-sidebar').removeClass('active');
        } else {

            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').addClass('active');
            }
        }
    })

    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}

function quit() {
	$.ajax({
        url: "user/quit",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		window.location.href = 'loginPage';
        	}
        }
	});
}

function openNewPswModal() {
	$('#change-psw-modal').modal('open');
	$('#error-alert').html('');
	$('#newPwd').val('');
}

function saveNewPsw() {
	if(isNull($('#newPwd').val())) {
		$('#error-alert').html('新密码不能为空！');
		return;
	}
	
	$.ajax({
        url: "user/changePwd",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			newPwd: $('#newPwd').val()
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		$('#error-alert').html('修改成功！');
        		
        		setTimeout(function(){
        			$('#change-psw-modal').modal('close');
    		    }, 2000);
        	} 
        }
    });
}

function todayBookedCount() {
	$.ajax({
        url: "user/todayBookedCount",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		$('#todayBooked').html(data.data.todayBooked);
        	} 
        }
    });
}