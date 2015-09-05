package com.subaru.controller;

import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BabyController {
	@RequestMapping("/baby.php")
	public ResponseEntity<String> home(Long telephone, String callback,
			HttpServletResponse response) {

		return jsonpEntity(
				"牧师： 赖晓航，你是否愿意娶林芸作为你的妻子？你是否愿意无论是顺境或逆境，富裕或贫穷，健康或疾病，快乐或忧愁，你都将毫无保留地爱她，对她忠诚直到永远？赖晓航：我愿意", callback);
	}
}
