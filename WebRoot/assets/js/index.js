$(function() {
	summary();
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

function summary() {
	$.AMUI.progress.start();
	$.ajax({
        url: "user/summary",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		$('#today').html(data.data.today);
        		$('#week').html(data.data.week);
        		$('#month').html(data.data.month);
        		$('#total').html(data.data.total);
        	} 
        	$.AMUI.progress.done();
        }
    });
}