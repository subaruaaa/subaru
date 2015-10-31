var Car = window.Car || {};

Car.Sold = {
	init : function(){
		var that = this;
		that.page_size = 10;
		that.page_num = 0;

		that.type = Car.Common.route(document.location.href, "type") || 0;

		$("#j-nav").html( baidu.template( $("#j-navTmpl").html(), {type : that.type} ) ).show();
		that.bind();
	},
	
	bind : function(){
		var that = this;
		var $load_more = $("#j-loadmore");

		$(window).bind("scroll", function(){
            if($load_more.hasClass('error')){
                return;
            }

            if ($("body").height() - $(window).height() - window.scrollY <= 100){
                $load_more.trigger("click");
            }
        });

        $load_more.bind("click", function(){
        	if($load_more.hasClass("doing")){
        		return false;
        	}

        	$load_more.html('<i></i><p>正在加载中...</p>').addClass("doing").removeClass("error").show();

        	that.getSoldList(that.page_num + 1);
        });

        if ($("body").height() - $(window).height() - window.scrollY <= 100){
            $load_more.trigger("click");
        }
	},

	getSoldList : function(page_num){
		var that = this;
		var url;

		switch(+that.type){
			case 1 : 
				url = "getCustomersDone";
				break;
			case 2 :
				url = "getCustomersAll";
				break;
			default :
				url = "getCustomersRecently";
		}

		$.ajax({
			url : CAR_HOST + url + ".php",
			type : "GET",
			data : {
				page_size : that.page_size,
				page_num : page_num
			},
			dataType : "jsonp",
			success : function(data){
				that.handleSoldGet(data);
			},

			error : function(data){
				that.handleSoldGet(null);
			}
		})
	},

	handleSoldGet : function(result){
		var that = this;
		var $load_more = $("#j-loadmore");

		if(result == null){
			$load_more.html("<p>加载失败，点击重新加载</p>").removeClass("doing").addClass("error").show();
			return;
		}

		if(result.code == 100){
			that.page_num++;
			if(that.page_num == 1 && result.customerOnePage.length == 0){
				$load_more.html("<p>没有顾客数据</p>").removeClass("doing error").addClass("nodata").show();
				$(window).unbind("scroll");
				$load_more.unbind("click");
				return;
			}

			$("#j-sold tbody").append( baidu.template($("#j-soldTmpl").html(), result));

			if(that.page_num == result.totalPage){
				$load_more.html("<p>数据已全部加载</p>").removeClass("doing error").addClass("done").show();
				$(window).unbind("scroll");
				$load_more.unbind("click");
				return;
			} else {

				$load_more.removeClass("doing error").hide();

				if ($("body").height() - $(window).height() - window.scrollY <= 0){
	                $load_more.trigger("click");
	            }
			}
		}
	}
}