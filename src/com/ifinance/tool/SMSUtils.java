package com.ifinance.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SMSUtils {

	// 云片配置
	public static final String	SMSURL				= "http://yunpian.com/v1/sms/tpl_send.json";
	public static final String	APIKEY				= "8beed76b2ec2dd1f1413f96f1e8374c7";
	//国内模板
	public static final String	TPL_ID				= "1895250";
	//国际模板
	public static final String	TPL_ID_OTHERCOUNTRY				= "1514738";
	
	public static String		tpl					= "#code#=@@@";
	
	public static String ENCODING = "UTF-8";

	// haoservice配置
	public static final String	haoservice_SMSURL	= "http://apis.haoservice.com/sms/send";
	public static final String	haoservice_apikey	= "ef525761dd9f4aad98cbaeca565b26fe";
	public static final String	haoservice_TPL_ID	= "1512";
	public static final String	haoservice_tpl		= "#code#=@@@";

	private static final int	SUCCESS_CODE		= 0;

	// 先发云片，云片不通，继续发好service
	public static int sendSMS(String phoneNumber, String code,boolean otherCountry) {

		int result = 999;
//		result = sendYunPianSMS(phoneNumber, code,otherCountry);
//		
//		//国外的短信就不重试其他服务商了
//		if (SUCCESS_CODE != result && !otherCountry) {
//			result = sendhaoserviceSMS(phoneNumber, code);
//		}

		return result;

	}
	
	/**
	 *  注册之后发短信给用户
	 * @param phoneNumber
	 * @param uName
	 * @param resigtMoible
	 * @return
	 */
	public static int sendRegistSMS(String phoneNumber,String uName,String resigtMoible)
	{
		String tpl_id = "1960540";
		
		 String tpl_value = null;
		try {
			tpl_value = URLEncoder.encode("#uname#",ENCODING) +"="
				        + URLEncoder.encode(uName, ENCODING) + "&"
				        + URLEncoder.encode("#mobile#",ENCODING) + "="
				        + URLEncoder.encode(resigtMoible,ENCODING);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		int result = 999;
		result = sendYunPianSMS(phoneNumber, tpl_value,tpl_id);
	
		return result;
	}

	/**
	 *  给客户发送客服短信
	 * @param phoneNumber
	 * @param uName
	 * @param resigtMoible
	 * @return
	 */
	public static int sendCustomService(String phoneNumber,String uName,String area,String customerService,String csmobile)
	{
		String tpl_id = "1960536";
		
		 String tpl_value = null;
		try {
			tpl_value = URLEncoder.encode("#uname#",ENCODING) +"="
				        + URLEncoder.encode(uName, ENCODING) + "&"
				        + URLEncoder.encode("#area#",ENCODING) + "="
				        + URLEncoder.encode(area,ENCODING)+"&"
				        + URLEncoder.encode("#CustomerService#",ENCODING) + "="
				        + URLEncoder.encode(customerService,ENCODING)+"&"
				        + URLEncoder.encode("#csmobile#",ENCODING) + "="
				        + URLEncoder.encode(csmobile,ENCODING);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		int result = 999;
		result = sendYunPianSMS(phoneNumber, tpl_value,tpl_id);
	
		return result;
	}
	
	/**
	 * 发送云片短信验证码
	 * 
	 * @param phoneNumber
	 * @param code
	 * @return
	 */
	private static int sendYunPianSMS(String phoneNumber, String message,String tpl_id) {
		int codeResult = 999;
		PostMethod post = new PostMethod(SMSURL);
		post.setParameter("apikey", APIKEY);
		post.setParameter("mobile", phoneNumber);
		post.setParameter("tpl_id", tpl_id);
		post.setParameter("tpl_value", message);

		HttpClient httpclient = new HttpClient();
		httpclient.setTimeout(1000 * 5);
		httpclient.setConnectionTimeout(1000 * 5);
		try {
			int result = httpclient.executeMethod(post);

			String respone = post.getResponseBodyAsString();
			System.out.println("yuanpian:"+respone);
			if (null != respone) {
				JSONObject json = JSON.parseObject(respone);
				int respcode = json.getIntValue("code");
				codeResult = respcode;
			}

		} catch (Exception e) {
			// 记录日志
		} finally {
			post.releaseConnection();
		}

		return codeResult;
	}

	/**
	 * 发送haoservice验证码
	 * 
	 * @param phoneNumber
	 * @param code
	 * @return
	 */
	private static int sendhaoserviceSMS(String phoneNumber, String code) {
		int codeResult = 999;
		PostMethod post = new PostMethod(haoservice_SMSURL);
		post.setParameter("key", haoservice_apikey);
		post.setParameter("mobile", phoneNumber);
		post.setParameter("tpl_id", haoservice_TPL_ID);
		post.setParameter("tpl_value", haoservice_tpl.replace("@@@", code));

		HttpClient httpclient = new HttpClient();
		httpclient.setTimeout(1000 * 5);
		httpclient.setConnectionTimeout(1000 * 5);
		try {
			int result = httpclient.executeMethod(post);

			String respone = post.getResponseBodyAsString();
			System.out.println("haoservice:"+respone);
			if (null != respone) {
				JSONObject json = JSON.parseObject(respone);
				int respcode = json.getIntValue("code");
				codeResult = respcode;
			}

		} catch (Exception e) {
			// 记录日志
		} finally {
			post.releaseConnection();
		}

		return codeResult;
	}

//	public static void main(String[] args) {
//		sendSMS("15951885125", "112233");
//	}
}
