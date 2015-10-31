var Car = window.Car || {};

Car.Findpw = {
	init : function(){
		var that = this;
		var $findpwform = $("#j-findpwform");
		var $username = $("#j-username");
		var $captcha = $("#j-captcha");
		var $sendbtn = $("#j-sendbtn");

		FastClick.attach(document.body);

		$findpwform.bind("submit", function(e){
			e.preventDefault();
			
			var username = $username.val().trim();
			var captcha = $captcha.val().trim();

			if(username == ""){
				Car.Common.alert("请输入用户名", "error");
				return;
			}

			if(captcha == ""){
				Car.Common.alert("请输入验证码", "error");
				return;
			}

			Car.Common.loading(1, "正在提交中...");

			$.ajax({
				url : CAR_HOST + "checkCode.php",
				type : "POST",
				data : {
					employeeTel : username,
					code : captcha
				},
				dataType : "jsonp",
				success : function(data){
					that.handleSendCode(data, username);
				},

				error : function(data){
					that.handleSendCode(null);
					
				}
			})
		});

		$sendbtn.bind("click", function(e){
			e.preventDefault();

			if($sendbtn.hasClass("disabled")){
				return;
			}

			var username = $username.val().trim();

			if(username == ""){
				Car.Common.alert("请输入用户名", "error");
				return;
			}

			$sendbtn.addClass("disabled").html("发送中");

			$.ajax({
				url : CAR_HOST + "getCode.php",
				type : "GET",
				data : {
					employeeTel : username,
					r : Date.now()
				},
				dataType : "jsonp",
				success : function(data){
					that.handleCaptcha(data);
				},

				error : function(data){
					that.handleCaptcha(null);
				}
			})
		});
	},

	handleSendCode : function(data, username){
		if(data && data.code == 100){
			document.location.href = "resetpw.html?employeeTel=" + username;
			return;
		}

		Car.Common.loading(0);

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}
		
		Car.Common.alert(data.msg, "error");
	},

	handleCaptcha : function(data){
		if(data && data.code == 100){
			var $sendbtn = $("#j-sendbtn");
			var time = 60;

			$sendbtn.addClass("disabled").html("重新发送(" +  time + ")");
			
			var timer = setInterval(function(){
				time--;
				$sendbtn.html("重新发送(" +  time + ")");

				if(time == 0){
					clearInterval(timer);
					$sendbtn.html("重新发送").removeClass("disabled");
				}
			}, 1000);
			return;
		}

		$sendbtn.html("重新发送").removeClass("disabled"); 

		if(data == null){
			Car.Common.alert("网络请求失败，请重试", "error");
			return;
		}

		Car.Common.alert(data.msg, "error");


		
	}
}