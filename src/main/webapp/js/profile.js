var Car = window.Car || {};

Car.Profile = {
	init : function(){
		var that = this;

		Car.Common.getConfig(function(){
			that.getUserInfo();
		});
	},
	
	bind : function(){
		var that = this;

		$("#j-editBtn").bind("click", function(e){
			e.preventDefault();

			$("#j-profile").html( baidu.template($("#j-profileEditTmpl").html(), that.data));
			$(this).hide();
			$("#j-cancelBtn").show();
			$("#j-profileEditForm").bind("submit", function(e){
				that.handleFormSubmit(e);
			});
		});

		$("#j-cancelBtn").bind("click", function(e){
			e.preventDefault();

			$("#j-profile").html( baidu.template($("#j-profileTmpl").html(), that.data));
			$(this).hide();
			$("#j-editBtn").show();
			$("#j-profileEditForm").unbind("submit");
		});
	},

	getUserInfo : function(){
		var that = this;

		$.ajax({
			url : CAR_HOST + "getEmployee.php",
			type : "POST",
			data : {},
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
			that.data = data.employee;
			$("#j-profile").html( baidu.template($("#j-profileTmpl").html(), that.data));
			that.bind();
		} else {
			Car.Common.alert(data.msg, "error");
		}
	},

	handleFormSubmit : function(e){
		e.preventDefault();
		var that = this;
		var data = $("#j-profileEditForm").serializeObj();

		console.log("data:", data);

		if(data.status != 1){
			data.status = 0;
		}

		that.data = $.extend({}, that.data, data);

		$.ajax({
			url : CAR_HOST + "modifyEmployee.php",
			type : "POST",
			data : data,
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
		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(data.code == 100){
			Car.Common.alert("修改成功", "success");
			$("#j-cancelBtn").trigger('click');
		} else {
			Car.Common.alert(data.msg, "error");
		}
	}
}