var Car = window.Car || {};
var CAR_HOST = "http://123.57.93.85/";
var CONFIG = null;

Car.Common = {
	tmpl : function(tmpl, data){
		return tmpl.replace(/<%=\s?([a-zA-Z0-9_]+)\s?%>/g, function(all, key){
			return typeof data[key] === "undefined" ? "" : data[key]; 
		});
	},

	alert : function(message, type, delay){
		message = message || "";
		type = type || "";
		delay = delay || 3000;

		if(type == "success"){
			type = "c-alert-success";
		} else if(type == "error"){
			type = "c-alert-error";
		}

		var tmpl = '\
		<div class="c-alert <%= type %>">\
            <p><%= message %></p>\
        </div>';

        tmpl = this.tmpl(tmpl, {message : message, type : type});

        if($(".c-alert").length > 0){
        	$(".c-alert").remove();
        	clearTimeout(this._alertTimer);
        }

        $("body").append(tmpl);

        this._alertTimer = setTimeout(function(){
        	$(".c-alert").remove();
        }, delay);
	},

	confirm : function(message, onConfirm, onCancel, confirmText, cancelText){
		message = message || "";
		onConfirm = typeof onConfirm === "function" ? onConfirm : function(){};
		onCancel = typeof onCancel === "function" ? onCancel : function(){};
		confirmText = confirmText || "确定";
		cancelText = cancelText || "取消";

		var tmpl = '\
		<div class="c-confirm">\
            <div class="content">\
                <p class="title"><%= message %></p>\
                <div class="action">\
                    <a class="confirm"><%= confirmText %></a>\
                    <a class="cancel"><%= cancelText %></a>\
                </div>\
            </div>\
        </div>';

        tmpl = this.tmpl(tmpl, {message : message, confirmText : confirmText, cancelText : cancelText});

        if($(".c-confirm").length > 0){
        	$(".c-confirm").remove();
        }

        $("body").append(tmpl);


        $(".c-confirm").find(".confirm").bind("click", function(e){
        	e.preventDefault();
        	$(".c-confirm").remove();
        	onConfirm();
        });

        $(".c-confirm").find(".cancel").bind("click", function(e){
        	e.preventDefault();
        	$(".c-confirm").remove();
        	onCancel();
        });
	},

	loading : function(action, message){
		message = message || "正在提交中...";

		if(action == 0){
			$(".c-loader-popup").removeClass("doing");
			return;
		}

		if($(".c-loader-popup").length == 0){
			var tmpl = '\
			<div class="c-loader c-loader-popup doing">\
	            <i></i>\
	            <p><%= message %></p>\
	        </div>';
	        tmpl = this.tmpl(tmpl, {message : message});
			$("body").append(tmpl);
		} else {
			$(".c-loader-popup").find("p").html(message).end().addClass("doing");
		}
	},

	route : function(url, key){
		
		if(arguments.length == 1){
			if(url.indexOf("?") < 0){
				return {};
			}
		}

		if(arguments.length == 2){
			if(url.indexOf(key + "=") >= 0){
				return url.split(key + "=")[1].split("&")[0];
			}

			return "";
		}

		var search = url.split("?")[1].split("&");
		var result = {};

		for(var i = 0; i < search.length; i++){
			var hashmap = search[i].split("=");
			result[hashmap[0]] = hashmap[1];
		}

		return result;
	},

	getConfig : function(callback){
		var that = this;

		if(CONFIG){
			callback();
			return;
		}

		$.ajax({
			url : CAR_HOST + "getDropdownMenu.php",
			type : "GET",
			data : {},
			dataType : "jsonp",
			success : function(data){
				if(data.code == 100){
					CONFIG = data;
					CONFIG.paymentTypeList[0].showBank = 0;
					CONFIG.paymentTypeList[1].showBank = 1;
					callback();
				} else {
					Car.Common.alert(data.msg, "error");
				}
			},

			error : function(data){
				Car.Common.alert("网络请求失败，请重试", "error");
			}
		})
	},

	getConfigItem : function(key, id, idKey){
		var data = CONFIG[key];
		
		for(var i = 0; i < data.length; i++){
			if(data[i][idKey] == id){
				return data[i];
			}
		}

		return data;
	},

	formateDate : function(date, formate){
		formate = formate || "";
		var date = new Date(date);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();

		var monthStr = (month <= 9) ? ("0" + month) : month;
		var dayStr = (day <= 9) ? ("0" + day) : day;
        var hourStr = (hour <= 9) ? ("0" + hour) : hour;
        var minuteStr = (minute <= 9) ? ("0" + minute) : minute;
        var secondStr = (second <= 9) ? ("0" + second) : second;

        formate = formate.replace("YYYY", year);
        formate = formate.replace("MM", monthStr);
        formate = formate.replace("DD", dayStr);
        formate = formate.replace("hh", hourStr);
        formate = formate.replace("mm", minuteStr);
        formate = formate.replace("ss", secondStr);
        formate = formate.replace("M", month);
        formate = formate.replace("D", day);
        formate = formate.replace("h", hour);
        formate = formate.replace("m", minute);
        formate = formate.replace("s", second);

        return formate;
	}
}

window.addEventListener("pageshow", function(e){
	if(e.persisted){
		Car.Common.loading(0);
	}
})