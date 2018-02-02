package com.ifinance.tool;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class getUserMobileMongodb {
		
	public static String SN = "/Users/mac/Desktop/SN.txt";
	public static String IMEI = "/Users/mac/Desktop/imei.txt";
	public static String QRCODE = "/Users/mac/Desktop/QRcode.txt";
	
	public static List<String> snList = new ArrayList<String>();
	public static List<String> imeiList = new ArrayList<String>();
	public static List<String> qrcodeList = new ArrayList<String>();
	public static void main(String[] args)
	{

		 File file = new File("/Users/mac/Desktop/waawo_mobile.txt");
	        BufferedWriter writer = null;
	        try {
//	            System.out.println("以行为单位读取文件内容，一次读一整行：");
	        	writer = new BufferedWriter(new FileWriter(file));
	         
	        	
	        	DB db = null;  
	            DBCollection dbCollection = null;  
	            db =  MongodbManager.getInstance().getDb(); //获取数据库实例  
                dbCollection = db.getCollection("users");    //获取数据库中指定的collection集合  
                DBCursor cursor = dbCollection.find();
                //循环输出结果
                int i =0;
                while (cursor.hasNext()) {
                	i++;
                	DBObject oject = cursor.next();	
                	String mobile = oject.get("mobile").toString();
                	System.out.println(i);
                	
                	writer.write(mobile);
		            writer.newLine();//换行
		            writer.flush();
                }
	             
	                
	          
	    
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (writer != null) {
	                try {
	                	writer.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		
	}
	
	
    /** 
     * 数据插入 
     * *测试数据： 
     *  
     * @return  
     */  
    private static void createColData(String sn,String imei,String qrcode){
    	DB db = MongodbManager.getInstance().getDb();
		if (db == null) {

			return;
		}
    	
        DBCollection dbCol = db.getCollection("devices");       
          
        BasicDBObject doc2 = new BasicDBObject();  
        doc2.put("devid", sn);  
        doc2.put("code", qrcode);  
        doc2.put("imei", imei);  
        doc2.put("box", "");  
        doc2.put("bt_mac", "");  
        doc2.put("wifi_mac", ""); 
        
        dbCol.insert(doc2);  
        
        System.out.println("插入数据："+sn);  
    }  
	
 
	 /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName,String mobile) {
        File file = new File(fileName);
        BufferedWriter writer = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
        	writer = new BufferedWriter(new FileWriter(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            writer.write(mobile);
            writer.newLine();//换行
            
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                	writer.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    public static boolean isExit(String collectionName, String key,  
            Object value) {  
        // TODO Auto-generated method stub  
        DB db = null;  
        DBCollection dbCollection = null;  
        if(key!=null && value!=null){  
            try {  
                db =  MongodbManager.getInstance().getDb(); //获取数据库实例  
                dbCollection = db.getCollection("users");    //获取数据库中指定的collection集合  
                DBCursor cursor = dbCollection.find();
                //循环输出结果
                while (cursor.hasNext()) {
                System.out.println(cursor.next());
                }
            } catch (Exception e) {  
                // TODO: handle exception  
                e.printStackTrace();  
            } finally{  
                if(null != db){  
                    db.requestDone();   //关闭db  
                    db = null;  
                }  
            }  
              
        }  
        return false;  
    }  
}
