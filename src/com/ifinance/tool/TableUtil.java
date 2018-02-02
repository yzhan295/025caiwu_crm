package com.ifinance.tool;

import java.util.Date;

public class TableUtil {

	/**
	 * 获取表名
	 * 
	 * @param date
	 * @param babyId
	 * @return
	 */
	public static String getTableName(Date date, String babyId) {
		String yymm = DateUtil.dateToString(date, "yyyy_MM");
		return getTableNameStr(yymm,babyId);
	}

	/**
	 * 参数日期为字符串，格式为:2015_06
	 * @param date
	 * @param babyId
	 * @return
	 */
	public static String getTableNameStr(String date, String babyId) {
		// 截取末尾2位，16进制
		String endHex = babyId.substring(babyId.length() - 2, babyId.length());
		// 16进制转成int，10进制
		int intHex = Integer.parseInt(endHex, 16);

		String tableName = "";
		int tempTabel = intHex % 30;
		if (tempTabel < 10) {
			tableName = "0" + tempTabel;
		} else {
			tableName = String.valueOf(tempTabel);
		}

		return date + "_" + "data" + tableName;
	}

	public static void main(String[] args) {
		System.out.println(getTableName(new Date(), "6813425dc4d34fd0b23ed5efa63ebe78"));
		//createAllTable();
		//添加
	
		//删除字段
	    //ALTER TABLE test DROP rawdata, drop upload;
	}
	
	public static void createAllTable()
	{
		for(int i = 1;i<=12;i++)
		{
			for(int j = 0;j<30;j++)
			{
				String yue = String.format("%02d", i);
				String day = String.format("%02d", j);
				String table = "2016_"+yue+"_data"+day+"";

				
				String sql = "alter table "+table+" add (rawdata varchar(100) default '',upload int(1) default 0) ;";
				//String sql = "ALTER TABLE "+table+" DROP rawdata,upload;";
				System.out.println(sql);
			}
		}
	}

}
