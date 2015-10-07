package com.subaru.constants;

public class FIELDS {

	public static final String STATUS = "status";
	public static final String CODE = "code";
	public static final String MESSAGE = "msg";
	public static final String DATA = "data";
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	public static final String NOT_LOGIN = "please login";
	public static final String PARAMETERS_ERROR = "parameters error";
	public static final String NO_DATA = "no data";

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
	public static final String OLD_PASSWD_ERROR = "-303S";

	public static final String DEFAULT_PAGE_SIZE_STR = "5";
	public static final String DEFAULT_PAGE_NUM_STR = "1";

}
