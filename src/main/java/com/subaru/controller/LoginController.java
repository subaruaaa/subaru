package com.subaru.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.models.HomePage;
import com.subaru.types.Tuple2;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

@Controller
public class LoginController {
	@RequestMapping("/home.php")
	public ResponseEntity<String> home(Long telephone, String callback,
			HttpServletResponse response) {
		List<HomePage> list = new ArrayList<HomePage>();
		HomePage homePage = new HomePage(1, "审批");
		HomePage homePage2 = new HomePage(2, "报表");
		HomePage homePage3 = new HomePage(3, "邮箱");
		HomePage homePage4 = new HomePage(4, "库存");
		HomePage homePage5 = new HomePage(5, "销售情况");
		HomePage homePage6 = new HomePage(6, "反馈");
		HomePage homePage7 = new HomePage(7, "试驾车");
		list.add(homePage);
		list.add(homePage2);
		list.add(homePage3);
		list.add(homePage4);
		list.add(homePage5);
		list.add(homePage6);
		list.add(homePage7);

		return jsonpEntity(map("list", list), callback);
	}
	
	//修改密码
	
	//找回密码
	
	//登陆
}
