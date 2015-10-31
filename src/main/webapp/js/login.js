var Car = window.Car || {};

Car.Login = {
	init : function(){
		var that = this;
		var $loginform = $("#j-loginform");
		var $username = $("#j-username");
		var $password = $("#j-password");

		FastClick.attach(document.body);
		
		$loginform.bind("submit", function(e){
			e.preventDefault();
			

			var username = $username.val().trim();
			var password = $password.val().trim();

			if(username == ""){
				Car.Common.alert("请输入用户名", "error");
				return;
			}

			if(password == ""){
				Car.Common.alert("请输入密码", "error");
				return;
			}

			Car.Common.loading(1, "正在登录中...");

			$.ajax({
				url : CAR_HOST + "login.php",
				type : "POST",
				data : {
					employeeTel : username,
					passwd : password
				},
				dataType : "jsonp",
				success : function(data){
					that.handleLogin(data);
				},

				error : function(data){
					that.handleLogin(null);
				}
			})
		});
	},

	handleLogin : function(data){
		if(data && data.code == 100){
			document.location.href = "home.html";
			return;
		}

		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		Car.Common.alert(data.msg, "error");

		
	}
}