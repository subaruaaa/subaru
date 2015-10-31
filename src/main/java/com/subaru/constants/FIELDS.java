package com.subaru.constants;

public class FIELDS {

	public static final String STATUS = "status";
	public static final String CODE = "code";
	public static final String MESSAGE = "msg";
	public static final String DATA = "data";
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	public static final String NOT_LOGIN = "请登录";
	public static final String PARAMETERS_ERROR = "参数错误";
	public static final String NO_DATA = "没有数据";
	public static final String CUSTOMER_EXIST = "客户电话已存在";
	public static final String EMPLOYEE_EXIST = "员工电话已存在";
	public static final String CUSTOMER_NOT_EXIST = "客户不村子啊";
	public static final String ILLEGAL_DATE = "非法的日期格式";
	public static final String ILLEGAL_TEL = "非法的电话格式";
	public static final String ILLEGAL_EMAIL = "非法的邮件地址";
	public static final String NAME_EMPTY = "姓名为空";
	public static final String TEL_EMPTY = "电话为空";

	public static final String CODE_SUCCESS = "100";
	// 未登录
	public static final String CODE_NOT_LOGIN = "-101";
	// 账号或密码错误
	public static final String CODE_ERROR_PASSWD = "-102";

	// 不存在此客户
	public static final String CODE_CUSTOMER_NOT_EXIST = "-201";

	// 没有获取并验证重置密码的验证码
	public static final String CODE_RESET_ERROR = "-301";
	// 验证码错误
	public static final String CODE_ERROR = "-302";

	// 旧密码错误
	public static final String OLD_PASSWD_ERROR = "-303";

	public static final String DEFAULT_PAGE_SIZE_STR = "5";
	public static final String DEFAULT_PAGE_NUM_STR = "1";

	public static final String CODE_CUSTOMER_EXIST = "-401";
	public static final String CODE_EMPLOYEE_EXIST = "-501";
	public static final String CODE_ILLEGAL_DATE = "-601";
	public static final String CODE_NAME_EMPTY = "-402";
	public static final String CODE_TEL_EMPTY = "-403";
	public static final String CODE_ILLEGAL_TEL = "-404";
	public static final String CODE_ILLEGAL_EMAIL = "-405";

	public static final String CODE_PAYMENTTYPE_ERROR = "-406";
	public static final String PAYMENTTYPE_ERROR = "请选择付款方式";
	
	public static final String CODE_PRICE_NULL = "-407";
	public static final String PRICE_NULL_ERROR = "请填写车身价格";
	
	public static final String CODE_INVOICEPRICE_NULL = "-408";
	public static final String INVOICEPRICE_NULL_ERROR = "请填写开票价格";
	
	public static final String CODE_VEHICLEIDENTIFICATIONNUMBER_NULL = "-409";
	public static final String VEHICLEIDENTIFICATIONNUMBER_NULL_ERROR = "请填写车架号";
	
	public static final String CODE_VEHICLESTYLEID_NULL = "-410";
	public static final String VEHICLESTYLEID_NULL_ERROR = "请填写车型";
	
	public static final String CODE_ORDER_NOT_EXIST = "-411";
	public static final String ORDER_NOT_EXIST = "订单不存在";
	
	public static final String CODE_PURCHASEQUANTITY_NOT_EXIST = "-412";
	public static final String PURCHASEQUANTITY_NOT_EXIST = "请填写购买数量";
}
