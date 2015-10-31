var Car = window.Car || {};

Car.Customer = {
	init : function(){
		var that = this;
		
		that.customerId = Car.Common.route(document.location.href, "customerId");

		if(that.customerId > 0){
			$("#j-nav").html( baidu.template( $("#j-navTmpl").html(), {customerId : that.customerId} ) ).show();
		}

		Car.Common.getConfig(function(){
			if(that.customerId){
				that.getCustomerInfo();
			} else {
				that.showAddForm();
			}
		});
	},
	
	bind : function(){
		var that = this;

		if(that.customerId){		
			$("#j-editBtn").bind("click", function(e){
				e.preventDefault();

				$("#j-customer").html( baidu.template($("#j-customerEditTmpl").html(), that.data));
				$(this).hide();
				$("#j-cancelBtn").show();
				$("#j-customerForm").bind("submit", function(e){
					e.preventDefault();
					that.handleFormSubmit(e);
				});
			}).show();
		} else{
			$("#j-customerForm").bind("submit", function(e){
				e.preventDefault();
				that.handleFormSubmit(e);
			});
		}

		$("#j-cancelBtn").bind("click", function(e){
			e.preventDefault();

			$("#j-customer").html( baidu.template($("#j-customerTmpl").html(), that.data));
			$(this).hide();
			$("#j-editBtn").show();
			$("#j-customerForm").unbind("submit");
		});
	},

	showAddForm : function(){
		var that = this;

		$("#j-customer").html( baidu.template($("#j-customerEditTmpl").html(), {
			introducerTypeId : null,
			blacked : false
		}));

		that.bind();
	},

	getCustomerInfo : function(){
		var that = this;

		$.ajax({
			url : CAR_HOST + "getCustomerById.php",
			type : "POST",
			data : {
				customerId : that.customerId
			},
			dataType : "jsonp",
			success : function(data){
				that.handleCustomerInfo(data);
			},

			error : function(data){
				that.handleCustomerInfo(null);
			}
		})
	},

	handleCustomerInfo : function(data){
		var that = this;

		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(data.code == 100){
			that.data = data.customer;
			$("#j-customer").html( baidu.template($("#j-customerTmpl").html(), that.data));
			that.bind();
		} else {
			Car.Common.alert(data.msg, "error");
		}
	},

	handleFormSubmit : function(e){
		var that = this;
		var data = $("#j-customerForm").serializeObj();

		that.data = $.extend({}, that.data, data);

		$.ajax({
			url : CAR_HOST + (that.customerId ? "modifyCustomer.php" : "createCustomer.php"),
			type : "POST",
			data : that.data,
			dataType : "jsonp",
			success : function(data){
				that.handleEditProfile(data);
			},

			error : function(data){
				that.handleEditProfile(null);
			}
		})
	},

	handleEditProfile : function(data){
		var that = this;
		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(data.code == 100){
			
			
			if(!that.data.customerId){
				document.location.href="customer.html?customerId=" + data.customer.customerId;
			} else{
				Car.Common.alert("修改成功", "success");
			}

			that.data = data.customer;

			$("#j-cancelBtn").trigger('click');
		} else {
			Car.Common.alert(data.msg, "error");
		}
	}
}