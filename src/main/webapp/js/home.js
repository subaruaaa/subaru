var Car = window.Car || {};

Car.Home = {
	init : function(){
		var that = this;

		FastClick.attach(document.body);
		
		that.getUserInfo();
	},
	
	bind : function(){
		var that = this;

		$(".j-link").bind("click", function(e){
			e.preventDefault();

			document.location.href = $(this).attr("data-href");
		});

		$("#j-logout").bind("click", function(e){
			e.preventDefault();

			that.logout();
		});
	},

	getUserInfo : function(){
		var that = this;

		$.ajax({
			url : CAR_HOST + "home.php",
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
			$("#j-home").html( baidu.template($("#j-hometmpl").html(), data));
			that.bind();
		} else {
			Car.Common.alert(data.msg, "error");
		}
	},

	logout : function(){
		var that = this;

		Car.Common.loading(1, "正在退出中...");

		$.ajax({
			url : CAR_HOST + "logout.php",
			type : "POST",
			data : {},
			dataType : "jsonp",
			success : function(data){
				that.handleLogout(data);
			},

			error : function(data){
				that.handleLogout(null);
			}
		})
	},

	handleLogout : function(data){
		if(data && data.code == 100){
			document.location.href = "index.html";
			return;
		}

		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		if(data.code != 100){
			Car.Common.alert(data.msg, "error");
		}
	}
}