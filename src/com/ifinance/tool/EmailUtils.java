package com.ifinance.tool;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class EmailUtils {

	public static int sendEmail(String email,String code) { 
		int result = 999;
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "XXdfRVYUuKQrJYBe", "7AiZWObytHZOFMwqk7o5ju1liTlCqN");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("service@mailservice.iite.cc");
             request.setFromAlias("iite");
            request.setAddressType(1);
            request.setTagName("authcode");
            request.setReplyToAddress(false);
            request.setToAddress(email);
            request.setSubject("Complete the registration, fun iite!");
            request.setHtmlBody("hello！<br>Welcome to register iite,<br>Your mail verification code is (valid for 15 minutes):<font size='15'>"+code+"</font><br>Note: automatically send mail, please do not reply directly");
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            
            System.out.println(httpResponse.getRequestId());
            result = 0;
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
//	public static void main(String[] args)
//	{
//		sendEmail("44910246@qq.com", "112233");
//	}
}