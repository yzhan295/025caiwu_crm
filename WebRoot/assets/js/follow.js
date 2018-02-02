$(function() {
	
	loadFollowList(1);
	todayBookedCount();
	$('#datetimepicker').datetimepicker({
		minView: 'month',
		language:  'zh-CN'
	});
	
	$('#customer-type').change(function(){ 
		//alert($(this).children('option:selected').val()); 
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
        url: "market/list",
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
      var customer_select_template = $('#customer-select-template').html();
      var temp = '__SELECT'+data.data.item[i].safeState+'__';
      customer_select_template = customer_select_template
      								.replace('__userToken__', data.data.item[i].userToken)
      								.replace(temp, 'selected');
//      console.log(customer_select_template);	 
      var tr_template = $('#follow-tr-template').html()
                .replace('__name__', data.data.item[i].name)
                .replace('__safeState__', customer_select_template)
//                .replace('__platform__', data.data.item[i].platform)
                .replace('__mobile__', data.data.item[i].mobile)
                .replace('__wxaccount__', data.data.item[i].wxaccount == "0" ? '' : data.data.item[i].wxaccount)
                .replace('__qmcount__', data.data.item[i].qmcount)
                .replace('__collcount__', data.data.item[i].collcount)
                .replace('__createTime__', data.data.item[i].createTime)
                .replace('__hangye__', data.data.item[i].area + '-' +data.data.item[i].hangye)
                .replace('__userToken__', data.data.item[i].userToken)
                .replace('__userToken_book__', data.data.item[i].userToken);
      
      $('#follow-list').append(tr_template);
    } 

    $('.am-pagination').html('');
    
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

function commonFollowList1(data) {

   $('#follow-list').html('');
   for(var i=0; i< data.data.item.length; i++) {
     var customer_select_template = $('#customer-select-template').html();
     var temp = '__SELECT'+data.data.item[i].safeState+'__';
     customer_select_template = customer_select_template
     								.replace('__userToken__', data.data.item[i].userToken)
     								.replace(temp, 'selected');
//     console.log(customer_select_template);	 
     var tr_template = $('#follow-tr-template').html()
               .replace('__name__', data.data.item[i].name)
               .replace('__safeState__', customer_select_template)
//               .replace('__platform__', data.data.item[i].platform)
               .replace('__mobile__', data.data.item[i].mobile)
               .replace('__wxaccount__', data.data.item[i].wxaccount == "0" ? '' : data.data.item[i].wxaccount)
               .replace('__qmcount__', data.data.item[i].qmcount)
               .replace('__collcount__', data.data.item[i].collcount)
               .replace('__createTime__', data.data.item[i].createTime)
               .replace('__hangye__', data.data.item[i].area + '-' +data.data.item[i].hangye)
               .replace('__userToken__', data.data.item[i].userToken)
               .replace('__userToken_book__', data.data.item[i].userToken);
     
     $('#follow-list').append(tr_template);
   } 

   $('.am-pagination').html('');
   
   if (data.data.pageNumber != 1) {
       $('.am-pagination').append('<li class="am-pagination-first "><a href="javascript:void(0);" onclick="search(1)">第一页</a></li>');
       $('.am-pagination').append('<li class="am-pagination-first "><a href="javascript:void(0);" onclick="search('+(data.data.pageNumber-1)+')">上一页</a></li>');
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
  	$('.am-pagination').append('<li class="am-pagination-last"><a href="javascript:void(0);" onclick="search('+(data.data.pageNumber+1)+')">下一页</a></li>');
   	$('.am-pagination').append('<li class="am-pagination-last"><a href="javascript:void(0);" onclick="search('+(data.data.totalPage)+')">最末页</a></li>');
   }                 

}

function appendPaginate1(paginateNo, isActive) {
	if (isActive) {
		$('.am-pagination').append('<li class="am-active"><a href="javascript:void(0);">'+paginateNo+'</a></li>')
	} else {
		$('.am-pagination').append('<li><a href="javascript:void(0);" onclick="search('+paginateNo+')">'+paginateNo+'</a></li>')
	}
}

function changeCustomerType(typeVal, userToken){     
	console.log(typeVal + ' ' + userToken);
	 $.ajax({
        url: "market/changeState",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			userToken: userToken,
			type: typeVal
		},
        success: function(data) {
        	if(data.result == 1) {
//        		$('#my-alert').modal('open');
//        		$('#my-alert .am-modal-bd').html(data.data);
        		$('.am-alert').show();
        		$('.am-alert').html(data.data);
        		
        		setTimeout(function(){
        			$('.am-alert').hide();
    		    }, 2000);
        	}
        }
     });
}  

function book(userToken) {
	 var $modal = $('#my-popup');
	 $modal.modal('open');
	 $('#error-alert1').html('');
	 $('#my-popup').data('userToken', userToken);
	 
	 $('#wxAccount').val('');
	 $('#bookedTime').val('');
	 $('#comment').val('');
	 
	 $.ajax({
        url: "market/queryBookeds",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			userToken: userToken
		},
        success: function(data) {
        	if(data.result == 1) {
        		$('#wxAccount').val(data.data.wxaccount);
        		$('#bookedTime').val(data.data.bookedTime);
        		$('#comment').val(data.data.comment);
        	}
        }
     });
	 
}

function search(pageNo) {
	$.AMUI.progress.start();
	$('#follow-list').data('pageNo', pageNo);
	$.ajax({
        url: "market/search",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			page: pageNo,
			mobile: $('#mobile').val(),
			uname: $('#uname').val()
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		commonFollowList1(data);
        	}
        	$.AMUI.progress.done();
        }
    });
}

function saveBook() {
	var userToken = $('#my-popup').data('userToken');
	var wxAccount = $('#wxAccount').val();
	var bookedTime = $('#bookedTime').val();
	var comment = $('#comment').val();
	
	if (isNull(wxAccount)) {
		$('#error-alert1').html('微信号不能为空！');
		return;
	}
	
	if (isNull(bookedTime)) {
		$('#error-alert1').html('预约时间不能为空！');
		return;
	}
	
	if (isNull(comment)) {
		$('#error-alert1').html('备注不能为空！');
		return;
	}	
	
	$.ajax({
        url: "market/booked",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			userToken: userToken,
			wxAccount: wxAccount,
			bookedTime: bookedTime,
			comment: comment
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		$('#error-alert1').html('预约成功！');
        		
        		setTimeout(function(){
        			$('#my-popup').modal('close');
    		    }, 2000);
        	} 
        }
    });
}

function openDetail(userToken) {
	var $modal = $('#detail-popup');
	$modal.modal('open');
	$('#query-log-list').html('');
	$('#collect-list').html('');
	
	$('.am-tabs-nav li').removeClass('am-active');
	$('.am-tabs-nav li:first').addClass('am-active');
	$('.am-tab-panel').removeClass('am-active');
	$('.am-tab-panel:first').addClass('am-active');
	$.ajax({
        url: "market/getUserQueryLog",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			userToken: userToken
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		for(var i=0; i< data.data.length; i++) {
        			var templateQueryLog = '<tr><td>'+data.data[i].content+'</td><td>'+data.data[i].count+'</td></tr>';  
        			$('#query-log-list').append(templateQueryLog);
        		}
        	} 
        }
    });
	
	$.ajax({
        url: "market/getUserCollect",
        type : "POST",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data: {
			userToken: userToken
		},
        success: function(data) {
        	console.log(data);
        	if(data.result == 1) {
        		for(var i=0; i< data.data.item.length; i++) {
        			var templateCollect = '<tr><td>'+data.data.item[i].name+'</td></tr>';  
        			$('#collect-list').append(templateCollect);
        		}
        	} 
        }
    });
}

