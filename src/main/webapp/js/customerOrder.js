var Car = window.Car || {};

Car.customerOrder = {
	init : function(){
		var that = this;
		
		that.customerId = Car.Common.route(document.location.href, "customerId");
		that.from = Car.Common.route(document.location.href, "from");

		$("#j-nav").html( baidu.template( $("#j-navTmpl").html(), {customerId : that.customerId, from : that.from} ) );

		if( that.from ){
			$("#j-backBtn").html("搜索页").attr("href", "sold-search.html?key=" +  that.from);
		}

		Car.Common.getConfig(function(){
			that.getCustomerHistory();
		});
	},
	
	bind : function(){
		var that = this;

		$(".j-addBtn").unbind("click").bind("click", function(e){
			e.preventDefault();
			$(".j-addBtn,.j-editBtn").hide();
			that.orderId = 0;
			$("#j-customerHistory").prepend( baidu.template($("#j-customerHistoryFormTmpl").html(), {
				data: {
					orderDate : Car.Common.formateDate(new Date(), "YYYY-MM-DD hh:mm:ss")
				}
			}));

			that.bind();
		});

		$(".j-editBtn").unbind("click").bind("click", function(e){
			e.preventDefault();
			$(".j-addBtn,.j-editBtn").hide();

			var $item = $(this).parents(".m-listview");
			var orderId = $item.attr("data-orderId");
			var item;

			for(var i = 0; i < that.list.length; i++){
				if(that.list[i].orderId == orderId){
					item = that.list[i];
					break;
				}
			}

			that.orderId = orderId;
			that.order = item;

			$item.replaceWith( baidu.template($("#j-customerHistoryFormTmpl").html(), {
				data: item
			}));

			that.bind();
		});

		$(".j-cancelBtn").unbind("click").bind("click", function(e){
			e.preventDefault();
			$(".j-addBtn,.j-editBtn").show();
			$("#j-customerHistory").html( baidu.template($("#j-customerHistoryTmpl").html(), {list : that.list}));
			that.bind();
		});

		$("#j-Form").unbind("submit").bind("submit", function(e){
			that.handleFormSubmit(e);
		});

		$(".j-select").unbind("change").bind("change", function(){
			var filed = this.name;
			var id = this.value;
			var $inputFiled = $(this).parent().next("dd");
			var unit = Car.Common.getConfigItem("discountTypeList", id, "discountId").unit;

			if(unit){
				$inputFiled.show().find("input").val("").end().find(".unit").html(unit);
			} else {
				$inputFiled.hide().find("input").val("");
			}
		});

		$(".j-select1").unbind("change").bind("change", function(){
			var filed = this.name;
			var id = this.value;
			var $inputFiled = $(".j-select1-field");
			var showBank = Car.Common.getConfigItem("paymentTypeList", id, "paymentTypeId").showBank;

			console.log("showBank:", showBank)
			if(showBank){
				$inputFiled.show().find("select,input").val("");
			} else {
				$inputFiled.hide().find("select,input").val("");
			}
		});
	},

	getCustomerHistory : function(){
		var that = this;
 
		$.ajax({
			url : CAR_HOST + "getOrders.php",
			type : "GET",
			data : {
				customerId : that.customerId
			},
			dataType : "jsonp",
			success : function(data){
				that.handleUserInfo(data);
			},

			error : function(data){
				that.handleUserInfo(null);
			}
		})
	},

	handleUserInfo : function(data){
		var that = this;

		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(data.code == 100){
			that.list = data.list;
			$("#j-customerHistory").html( baidu.template($("#j-customerHistoryTmpl").html(), {list : that.list}));
			that.bind();
		} else {
			Car.Common.alert(data.msg, "error");
		}
	},

	handleFormSubmit : function(e){
		e.preventDefault();
		var that = this;
		var data = $("#j-Form").serializeObj();

		var item;
		for(var i = 0; i < that.list.length; i++){
			if(that.list[i].orderId == that.orderId){
				item = that.list[i];
				break;
			}
		}

		data = $.extend({}, item, data);

		delete data.employee;
		delete data.customer;

		data.customerId = that.customerId;

		$.ajax({
			url : CAR_HOST + (data.orderId > 0 ? "modifyOrder.php" : "createOrder.php"),
			type : "POST",
			data : data,
			dataType : "jsonp",
			success : function(result){
				that.handleEdit(data, result);
			},

			error : function(data){
				that.handleEdit(null);
			}
		})
	},

	handleEdit : function(data, result){
		var that = this;

		Car.Common.loading(0);

		if(result == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(result.code == 100){
			Car.Common.alert("修改成功", "success");

			console.log(data.orderId);

			if(data.orderId > 0){
				var item;
				for(var i = 0; i < that.list.length; i++){
					if(that.list[i].orderId == data.orderId){
						that.list[i] = result.order;
						break;
					}
				}
			} else {
				that.list.unshift(result.list[0]);
			}

			$(".j-cancelBtn").trigger('click');
		} else {
			Car.Common.alert(result.msg, "error");
		}
	}
}