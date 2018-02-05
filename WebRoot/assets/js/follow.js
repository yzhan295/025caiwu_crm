$(function() {
	
	loadFollowList(1);
	$('#datetimepicker').datetimepicker({
		minView: 'month',
		language:  'zh-CN'
	});
	
	$('#customer-type').change(function(){ 
		loadFollowList(1)
	}); 
	
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

function loadFollowList(pageNo) {
	$.AMUI.progress.start();
	$('#follow-list').data('pageNo', pageNo);
	var customer_type = $("#customer-type").val();
	if (customer_type == 'all') {
		customer_type = '';
	}
	$.ajax({
        url: "follow/listCustomer",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			page: pageNo,
			type: customer_type
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		commonFollowList(data);
        	} 
        	$.AMUI.progress.done();
        }
    });
}

function commonFollowList(data) {

	$('#follow-list').html('');
    for(var i=0; i< data.data.item.length; i++) {
    	  // 渠道
      var source_select_template = $('#source-select-template').html();
      source_select_template = source_select_template
      								.replace('__ID__', data.data.item[i].id)
      								.replace('__SELECT'+data.data.item[i].source+'__', 'selected');
      
      // 电话沟通
      var phone_select_template = $('#phone-select-template').html();
      phone_select_template = phone_select_template
									.replace('__ID__', data.data.item[i].id)
      								.replace('__SELECT'+data.data.item[i].phonestate+'__', 'selected');
      
      // 微信好友
      var wechat_select_template = $('#wechat-select-template').html();
      wechat_select_template = wechat_select_template
									.replace('__ID__', data.data.item[i].id)
      								.replace('__SELECT'+data.data.item[i].wechatState+'__', 'selected');
      
      // 短信状态
      var sms_select_template = $('#sms-select-template').html();
      sms_select_template = sms_select_template
									.replace('__ID__', data.data.item[i].id)
      								.replace('__SELECT'+data.data.item[i].smsState+'__', 'selected');
      
      // 意向客户
      var customer_select_template = $('#customer-select-template').html();
      customer_select_template = customer_select_template
									.replace('__ID__', data.data.item[i].id)
      								.replace('__SELECT'+data.data.item[i].customerType+'__', 'selected');
      var tr_template = $('#follow-tr-template').html()
                .replace('__name__', data.data.item[i].name)
                .replace('__customerstate__', customer_select_template)
                .replace('__source__', source_select_template)
                .replace('__phone__', phone_select_template)
                .replace('__wechat__', wechat_select_template)
                .replace('__sms__', sms_select_template)
                .replace('__CUSTOMER_ID__', data.data.item[i].id)
                
                .replace('__followsaler__', data.data.item[i].followsaler)
                .replace('__followcount__', data.data.item[i].followcount)
                .replace('__followtime__', isNull(data.data.item[i].followtime) ? '' : data.data.item[i].followtime)
                .replace('__followdesc__', isNull(data.data.item[i].followdesc) ? '': data.data.item[i].followdesc)
                .replace('__mobile__', data.data.item[i].mobile);
      
      $('#follow-list').append(tr_template);
    } 

    $('.am-pagination').html('');
    
    // 设置PageNumber到全局hidden中
    $('#pageNumber').val(data.data.pageNumber);
    
    if (data.data.pageNumber != 1) {
        $('.am-pagination').append('<li class="am-pagination-first "><a href="javascript:void(0);" onclick="loadFollowList(1)">第一页</a></li>');
        $('.am-pagination').append('<li class="am-pagination-first "><a href="javascript:void(0);" onclick="loadFollowList('+(data.data.pageNumber-1)+')">上一页</a></li>');
    }

    if (data.data.pageNumber < 5) {
   	 var allPage = (data.data.totalPage >= 10) ? 10 : data.data.totalPage;
   	 
   	 for(var i=1; i<=allPage; i++) {
   		 appendPaginate(i, data.data.pageNumber == i);
   	 }
    } else if (data.data.totalPage - data.data.pageNumber < 5) {
   	 var fromIndex = (data.data.totalPage - 9 > 0) ? (data.data.totalPage - 9) : 1;
   	 
   	 for(var i=fromIndex; i<=data.data.totalPage; i++) {
   		 appendPaginate(i, data.data.pageNumber == i);
   	 }
    } else {
   	 for(var i=data.data.pageNumber-4; i<=data.data.pageNumber+5; i++) {
   		 appendPaginate(i, data.data.pageNumber == i);
   	 }
    }
    
    if (data.data.totalPage > 0 && (data.data.pageNumber != data.data.totalPage)) {
   	$('.am-pagination').append('<li class="am-pagination-last"><a href="javascript:void(0);" onclick="loadFollowList('+(data.data.pageNumber+1)+')">下一页</a></li>');
    	$('.am-pagination').append('<li class="am-pagination-last"><a href="javascript:void(0);" onclick="loadFollowList('+(data.data.totalPage)+')">最末页</a></li>');
    }                 

}

function appendPaginate(paginateNo, isActive) {
	if (isActive) {
		$('.am-pagination').append('<li class="am-active"><a href="javascript:void(0);">'+paginateNo+'</a></li>')
	} else {
		$('.am-pagination').append('<li><a href="javascript:void(0);" onclick="loadFollowList('+paginateNo+')">'+paginateNo+'</a></li>')
	}
}


function changeSourceType(val, id){     
	 $.ajax({
       url: "follow/updateCustomer",
       type : "POST",
       dataType: "json",
       contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: id,
			source: val
		},
       success: function(data) {
       	if(data.result == 1) {
       		$('.am-alert').show();
       		$('.am-alert').html(data.data);
       		
       		setTimeout(function(){
       			$('.am-alert').hide();
   		    }, 2000);
       	}
       }
    });
} 

function changePhoneType(val, id){     
	 $.ajax({
       url: "follow/updateCustomer",
       type : "POST",
       dataType: "json",
       contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: id,
			phone: val
		},
       success: function(data) {
       	if(data.result == 1) {
       		$('.am-alert').show();
       		$('.am-alert').html(data.data);
       		
       		setTimeout(function(){
       			$('.am-alert').hide();
   		    }, 2000);
       	}
       }
    });
} 

function changeWechatType(val, id){     
	 $.ajax({
       url: "follow/updateCustomer",
       type : "POST",
       dataType: "json",
       contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: id,
			wechat: val
		},
       success: function(data) {
       	if(data.result == 1) {
       		$('.am-alert').show();
       		$('.am-alert').html(data.data);
       		
       		setTimeout(function(){
       			$('.am-alert').hide();
   		    }, 2000);
       	}
       }
    });
} 

function changeSmsType(val, id){     
	 $.ajax({
       url: "follow/updateCustomer",
       type : "POST",
       dataType: "json",
       contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: id,
			sms: val
		},
       success: function(data) {
       	if(data.result == 1) {
       		$('.am-alert').show();
       		$('.am-alert').html(data.data);
       		
       		setTimeout(function(){
       			$('.am-alert').hide();
   		    }, 2000);
       	}
       }
    });
} 

function changeCustomerType(val, id){     
	 $.ajax({
        url: "follow/updateCustomer",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: id,
			customerType: val
		},
        success: function(data) {
        	if(data.result == 1) {
        		$('.am-alert').show();
        		$('.am-alert').html(data.data);
        		
        		setTimeout(function(){
        			$('.am-alert').hide();
    		    }, 2000);
        	}
        }
     });
}  

function openDetail(customerId) {
	var $modal = $('#my-popup');
	$modal.modal('open');
	
	$('#my-popup').data('customerId', customerId);
	$('#followDesc').val('');
	
	$('#history-list').html('');
	$.ajax({
        url: "follow/getHistoryList",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: customerId
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		for(var i=0; i< data.data.item.length; i++) {
        			var templateHistory = '<tr><td>'+data.data.item[i].followDesc+'</td><td>'+data.data.item[i].followTime+'</td><td>'+data.data.item[i].username+'</td><tr>';  
        			$('#history-list').append(templateHistory);
        		}
        	} 
        }
    });
}

function save() {
	var customerId = $('#my-popup').data('customerId');
	var followDesc = $('#followDesc').val();
	
	if (isNull(followDesc)) {
		$('.am-alert').show();
		$('.am-alert').html('备注不能为空！');
		setTimeout(function(){
			$('.am-alert').hide();
	    }, 2000);
		return;
	}	
	
	$.ajax({
        url: "follow/createFollowDetail",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customerId: customerId,
			followDesc:followDesc
		},
        success: function(data) {
	        	console.log(data);
	        	if(data.result == 1) {
	        		$('.am-alert').show();
	        		$('.am-alert').html(data.data);
	        		
	        		setTimeout(function(){
	        			$('.am-alert').hide();
	    		    }, 2000);
	        		
	        		setTimeout(function(){
	        			$('#my-popup').modal('close');
	    		    }, 2000);
	        		
	        		loadFollowList($('#pageNumber').val());
	        	} 
        }
    });
}

/**
 * 新建客户POPUP
 * @returns
 */
function addCustomerPopup() {
	var $modal = $('#customer-popup');
	$modal.modal('open');
	
	$('#customer_name').val('');
	$('#customer_phone').val('');
}

function addCustomer() {
	var customer_name = $('#customer_name').val();
	if (isNull(customer_name)) {
		$('.am-alert').show();
		$('.am-alert').html('客户姓名不能为空！');
		setTimeout(function(){
			$('.am-alert').hide();
	    }, 2000);
		return;
	}
	
	var customer_phone = $('#customer_phone').val();
	if (isNull(customer_phone)) {
		$('.am-alert').show();
		$('.am-alert').html('电话号码不能为空！');
		setTimeout(function(){
			$('.am-alert').hide();
	    }, 2000);
		return;
	}	
	
	$.ajax({
        url: "follow/createCustomer",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			customer_name: customer_name,
			customer_phone:customer_phone
		},
        success: function(data) {
	        	console.log(data);
	        	if(data.result == 1) {
	        		$('.am-alert').show();
	        		$('.am-alert').html(data.data);
	        		
	        		setTimeout(function(){
	        			$('.am-alert').hide();
	    		    }, 2000);
	        		
	        		setTimeout(function(){
	        			$('#customer-popup').modal('close');
	    		    }, 2000);
	        		
	        		loadFollowList($('#pageNumber').val());
	        	} 
        }
    });
}