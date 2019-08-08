package com.shopping.manage.admin.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;
@Component
public class SmsUtil {
	
	//用户名
	private static String Uid = "关wlx心";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "13682194774";
	
	//短信内容
	private static String smsText = "验证码：";
	
	
	public  boolean SendMessage(String phone,String code) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
	
        
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText+String.valueOf(code), phone);
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
			return true;
		}else{
			System.out.println( client.getErrorMsg(result));
			return false;
		}
	}

}
