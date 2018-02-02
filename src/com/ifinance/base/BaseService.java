package com.ifinance.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaseService {
	
	
	public String respJsonError(String msgString)
	{
		JSONObject json = new JSONObject();
		json.put("result", 0);
		json.put("data", msgString);
		return json.toJSONString();
	}
	
	public String respJsonError(int result,String msgString)
	{
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("data", msgString);
		return json.toJSONString();
	}
	
	public String respJsonSuccess(String jsonString)
	{
		JSONObject json = new JSONObject();
		json.put("result", 1);
		json.put("data", jsonString);
		return json.toJSONString();
	}
	
	public String respJsonSuccess(JSONObject jsonObject)
	{
		JSONObject json = new JSONObject();
		json.put("result", 1);
		json.put("data", jsonObject);
		return json.toJSONString();
	}
	
	public String respJsonSuccess(JSONArray jsonArray)
	{
		JSONObject json = new JSONObject();
		json.put("result", 1);
		json.put("data", jsonArray);
		return json.toJSONString();
	}
	
	

}
