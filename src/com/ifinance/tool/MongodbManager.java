package com.ifinance.tool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongodbManager {

	
	private static MongodbManager uniqueInstance = null;

	private MongoClient mongoClient = null;
	
	private DB db = null;

	private MongodbManager() {

		try {
			mongoClient = new MongoClient("115.29.197.17", 10000);
			db = mongoClient.getDB("waawor");
//			//添加Mongo认证
//	    	boolean auth = db.authenticate(ConstWaawo.mongoUser,ConstWaawo.mongoPwd.toCharArray());
//	    	if(auth)
//	    	{
//	    		System.out.println("mongo auth success");
//	    	}else
//	    	{
//	    		System.out.println("mongo auth fail");
//	    	}
	    	
			mongoClient.setWriteConcern(WriteConcern.JOURNALED);

		} catch (Exception e) {
		}
		
		System.out.println("############### init mongodb success ###############");
	}

	public static MongodbManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MongodbManager();
		}
		return uniqueInstance;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}
	
//	public void insertMessage(MsgContent msgContent,String message,String type)
//	{
//		 DBCollection collection = db.getCollection("messages");
//	     //使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
//	      BasicDBObject document = new BasicDBObject();
//	      document.put("uid", msgContent.getUid());
//	      document.put("sid", msgContent.getSid());
//	      document.put("type", type);
//	      document.put("content", message);
//	      document.put("state", "0");
//	      
//	      SimpleDateFormat dateFormat = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			Date locationTime = null;
//			try {
//				locationTime = dateFormat.parse(msgContent.getLtime());
//				
//			} catch (ParseException e) {
//				locationTime = new Date(System.currentTimeMillis());
//				return;
//			}
//	      document.put("date", locationTime);
//	      //将新建立的document保存到collection中去
//	      collection.insert(document);
//	}


}
