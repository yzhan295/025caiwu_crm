$(function() {
	queryMyDayRec();
	todayBookedCount();
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
        $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
        $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
    });

    // 侧边导航下拉列表
    $('.tpl-left-nav-link-list').on('click', function() {
        $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
            .end()
            .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
    });
    
    // 头部导航隐藏菜单
	$('.tpl-header-nav-hover-ico').on('click', function() {
	    $('.tpl-left-nav').toggle();
	    $('.tpl-content-wrapper').toggleClass('tpl-content-wrapper-hover');
	});
});

function openPopup() {
	 var $modal = $('#my-popup');
	 $modal.modal('open');
	 $('#error-alert1').html('');
	 $('#newDayRec').val('');
	 $('#oldDayRec').val($('#countSetting').data('dayRecSetting'));
}

function queryMyDayRec() {
	$.AMUI.progress.start();
	$.ajax({
        url: "user/mydayRec",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		$('#mydayRec').html(data.data.dayRecSetting);
        		$('#oldDayRec').html(data.data.dayRecSetting);
        		$('#countSetting').data('dayRecSetting', data.data.dayRecSetting);
        		var stateHtml = '';
        		$('#state').removeClass('red');
        		if(data.data.state==0) {
        			stateHtml = '正常';
        		} else {
        			stateHtml = '禁用';
        			$('#state').addClass('red');
        			$('#conutSettingModifyBtn').attr('disabled',"true");
        		}
        		$('#state').html(stateHtml);
        	} 
        	$.AMUI.progress.done();
        }
    });
}

function setMyDayRec() {
	if (isNull($('#newDayRec').val())) {
		$('#error-alert1').html('日接受号码数不能为空！');
		return;
	}
	$.ajax({
        url: "user/setdayRec",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			newDayRec: $('#newDayRec').val()
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 0) {
        		$('#error-alert1').html(data.data);
        		return;
        	}
        	if(data.result == 1) {
        		$('#error-alert1').html('调整成功！');
        		
        		queryMyDayRec();
        		
        		setTimeout(function(){
        			$('#my-popup').modal('close');
    		    }, 1000);
        	} 
        }
    });
}

