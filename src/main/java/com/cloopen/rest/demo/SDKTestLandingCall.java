package com.cloopen.rest.demo;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;

public class SDKTestLandingCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("accountSid", "accountToken");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("AppId");// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1, "回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");

		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}

}
