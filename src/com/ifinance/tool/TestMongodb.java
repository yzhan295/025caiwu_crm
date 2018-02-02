package com.ifinance.tool;
//工程无用的类
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class TestMongodb {
		
	public static String SN = "/Users/mac/Desktop/SN.txt";
	public static String IMEI = "/Users/mac/Desktop/imei.txt";
	public static String QRCODE = "/Users/mac/Desktop/QRcode.txt";
	
	public static List<String> snList = new ArrayList<String>();
	public static List<String> imeiList = new ArrayList<String>();
	public static List<String> qrcodeList = new ArrayList<String>();
	public static void main(String[] args)
	{
		readFileByLines(SN,snList);
		readFileByLines(IMEI,imeiList);
		readFileByLines(QRCODE,qrcodeList);
		
		System.out.println("snList:"+snList.size()+";imeiList:"+imeiList.size()+";qrcodeList:"+qrcodeList.size()+"");
		for(int i = 0;i<snList.size();i++)
		{
			String sn = snList.get(i);
			String imei = imeiList.get(i);
			String qr = qrcodeList.get(i);
			System.out.println(sn+";"+imei+";"+qr);
			
			if(isExit("devices", "devid", sn))
			{
				System.out.println("存在");
			}else
			{
				createColData(sn, imei, qr);
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
    public static void readFileByLines(String fileName,List<String> list) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
            	list.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
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
                dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
                BasicDBObject obj = new BasicDBObject();    //构建查询条件  
                obj.put(key, value);  
                  
                if(dbCollection.count(obj) > 0) {  
                    return true;  
                }else{  
                    return false;  
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
