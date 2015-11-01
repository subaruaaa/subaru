var Car = window.Car || {};

Car.SoldSearch = {
	init : function(){
		var that = this;
		that.page_size = 10;
		that.page_num = 0;
		that.key = Car.Common.route(document.location.href, "key") || "";

		that.bind();
	},
	
	bind : function(){
		var that = this;
		var $load_more = $("#j-loadmore");

		$(window).bind("scroll", function(){
			if(!that.key){
				return;
			}
            if($load_more.hasClass('error') || $load_more.hasClass("done")){
                return;
            }

            if ($("body").height() - $(window).height() - window.scrollY <= 0){
                $load_more.trigger("click");
            }
        });

        $load_more.bind("click", function(){
        	if($load_more.hasClass("doing")){
        		return false;
        	}

        	$load_more.html('<i></i><p>正在加载中...</p>').addClass("doing").removeClass("error").show();

        	that.handleFormSubmit(that.page_num + 1);
        });

        if (that.key &&  $("body").height() - $(window).height() - window.scrollY <= 100){
            $load_more.trigger("click");
        }
	},

	handleFormSubmit : function(page_num){
		var that = this;

		$.ajax({
			url : CAR_HOST + "searchCustomerByCustomerTel.php",
			type : "POST",
			data : {
				customerTel : that.key,
				page_size : that.page_size,
				page_num : page_num
			},
			dataType : "jsonp",
			success : function(data){
				that.handleSearch(data);
			},

			error : function(data){
				that.handleSearch(null);
			}
		})
	},

	handleSearch : function(result){
		var that = this;
		var $load_more = $("#j-loadmore");

		if(result == null){
			$load_more.html("<p>加载失败，点击重新加载</p>").removeClass("doing").addClass("error").show();
			return;
		}

		if(result.code == 100){
			that.page_num++;
			if(that.page_num == 1 && result.list.length == 0){
				$load_more.hide();
				$("#j-search").hide();
				$("#j-nodata").show();
				$("#j-addBtn").attr("href", "customer.html?from=" + that.key);
				return;
			}

			$("#j-search").show().find("tbody").append( baidu.template($("#j-searchTmpl").html(), result));

			if(that.page_num == result.totalPage){
				$load_more.html("<p>数据已全部加载</p>").removeClass("doing error").addClass("done").show();
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