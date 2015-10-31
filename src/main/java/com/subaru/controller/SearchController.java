package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.types.functions.subList;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.services.SearchService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;

	@RequestMapping("/searchCustomerByCustomerTel.php")
	public ResponseEntity<String> searchCustomerByCustomerTel(@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "page_size", defaultValue = FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
			@RequestParam(value = "page_num", defaultValue = FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num, String callback, HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE, FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		// 对拉黑的人进行额外的标注
		// 显示客户信息

		List<Map<String, Object>> list = searchService.searchVisitCustomerByCustomerTel(customerTel);
		List<Map<String, Object>> customerOnePage = subList(list, page_size * (page_num - 1), page_size * page_num);
		int totalPage = list.size() / page_size + 1;

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_SUCCESS, "list", customerOnePage, "totalPage", totalPage), callback);
	}
}
