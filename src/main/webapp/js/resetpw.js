var Car = window.Car || {};

Car.Resetpw = {
	init : function(){
		var that = this;

		FastClick.attach(document.body);
		
		var $resetpwform = $("#j-resetpwform");
		var $password = $("#j-password");
		var $repassword = $("#j-repassword");
		var employeeTel = Car.Common.route(document.location.href, "employeeTel");

		$resetpwform.bind("submit", function(e){
			e.preventDefault();

			var password = $password.val().trim();
			var repassword = $repassword.val().trim();

			if(password == ""){
				Car.Common.alert("请输入新密码", "error");
				return;
			}

			if(repassword == ""){
				Car.Common.alert("请输入确认密码", "error");
				return;
			}

			if(repassword !== password){
				Car.Common.alert("两次密码输入不一致", "error");
				return;
			}

			Car.Common.loading(1);

			$.ajax({
				url : CAR_HOST +  "resetPasswd.php",
				type : "POST",
				data : {
					employeeTel : employeeTel,
					newPasswd : password
				},
				dataType : "jsonp",
				success : function(data){
					that.handleData(data);
				},

				error : function(data){
					that.handleData(null);
				}
			})
		})
	},

	handleData : function(data){
		Car.Common.loading(0);

		if(data && data.code == 100){
			Car.Common.alert("密码重置成功", "success");
			setTimeout(function(){
				Car.Common.loading(1, "页面跳转中...");
				document.location.href = "index.html";
			}, 3000);
			return;
		}

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		Car.Common.alert(data.msg, "error");
	}
}