package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.constants.FIELDS;
import com.subaru.services.OaService;
import com.subaru.utils.LoginHelper;

@Controller
public class OaController {
	@Autowired
	OaService oaService;

	@RequestMapping("/getDropdownMenu.php")
	public ResponseEntity<String> getOrder(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		List<Map<String, Object>> storeList = oaService.getAllStores();
		List<Map<String, Object>> positionList = oaService.getAllPosition();
		List<Map<String, Object>> introducerTypeList = oaService
				.getAllIntroducerType();

		Map<String, Map<String, Map<String, Integer>>> vehicleMap = oaService
				.getVehicleMap();
		Map<String, Integer> vehicle2IdMap = oaService.getVehicle2IdMap();

		List<Map<String, String>> discountTypeList = oaService
				.getDiscountTypeList();

		List<Map<String, Object>> vehicleList = oaService.getVehicleList();

		List<Map<String, Object>> statusList = oaService.getAllStatus();
		List<Map<String, Object>> installationList = oaService
				.getAllInstallation();

		List<Map<String, Object>> paymentTypeList = oaService
				.getPaymentTypeList();
		List<Map<String, Object>> mortgageBankList = oaService
				.getMortgageBankList();

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "storeList", storeList,
						"positionList", positionList, "introducerTypeList",
						introducerTypeList, "vehicleMap", vehicleMap,
						"discountTypeList", discountTypeList, "vehicle2IdMap",
						vehicle2IdMap, "statusList", statusList,
						"installationList", installationList, "vehicleList",
						vehicleList, "paymentTypeList", paymentTypeList,
						"mortgageBankList", mortgageBankList), callback);
	}

}
