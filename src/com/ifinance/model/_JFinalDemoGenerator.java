package com.ifinance.model;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {

	public static DataSource getDataSource() {
		PropKit.use("config_loc.txt");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	 public static void main(String[] args) {
	 getDataSource();
	 // base model 所使用的包名
	 String baseModelPackageName = "com.ifinance.model.base";
	 // base model 文件保存路径
	 String baseModelOutputDir = PathKit.getWebRootPath() +
	 "/../src/com/ifinance/model/base";
	
	 // model 所使用的包名 (MappingKit 默认使用的包名)
	 String modelPackageName = "com.ifinance.model";
	 // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
	 String modelOutputDir = baseModelOutputDir + "/..";
	
	 // 创建生成器
	 Generator gernerator = new Generator(getDataSource(),
	 baseModelPackageName, baseModelOutputDir, modelPackageName,
	 modelOutputDir);
	 // 添加不需要生成的表名
	 //gernerator.addExcludedTable("adv");
	 // 设置是否在 Model 中生成 dao 对象
	 gernerator.setGenerateDaoInModel(true);
	 // 设置是否生成字典文件
	 gernerator.setGenerateDataDictionary(true);
	 // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为
	 //"User"而非 OscUser
	 //gernerator.setRemovedTableNamePrefixes("t_");
	 // 生成
	 gernerator.generate();
	 }

//	public static void main(String[] args) {
//		// getDataSource();
//
//		PropKit.use("iteeth_config_loc.txt");
//		C3p0Plugin dp = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
//
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
//		// 与web环境唯一的不同是要手动调用一次相关插件的start()方法 dp.start();
//		dp.start();
//		arp.start();
//
//		List<Record> list = Db.find("select * from allcaiwu");
//		int j=0;
//		for (int i = 0; i < list.size(); i++) {
//			Record r = list.get(i);
//			String name = r.getStr("name");
//			
////			String newname =name;
////			if(name.indexOf("(")>1)
////			{
////				newname = name.substring(0, name.indexOf("("));
////			}
////			
////			if(name.indexOf("（")>1)
////			{
////				newname = name.substring(0, name.indexOf("（"));
////			}
////			
////			if(i == 617)
////			{
////				newname = "奇辉";
////			}
////			
////			if(i==1449)
////			{
////				continue;
////			}
////			//String newname = name.replace("88888888", "").replace("000000", "").replace("888888", "").replace("111111", "").replace("123456", "");
////			System.out.println(i+"-----"+newname);
////			 r.set("name", newname);
////			 Db.update("allcaiwu", r);
//			int b = 0;
//			Record wuyingying = Db.findFirst("select * from wuyingying where name like '%"+name+"%'");
//			if(null != wuyingying)
//			{
//				b++;
//				String wuliu = "--";
//				if(wuyingying.getStr("name").indexOf("物流")>0)
//				{
//					wuliu = "物流";
//				}
//				
//				wuyingying.set("state", 1);
//				Db.update("wuyingying", wuyingying);
//				
//				r.set("name", wuyingying.getStr("name"));
//				r.set("temp", 1);
//				r.set("guwen", wuyingying.getStr("guwen"));
//				r.set("time", wuyingying.getStr("time"));
//				r.set("cost", wuyingying.getStr("cost"));
//				r.set("kuaiji", wuyingying.getStr("kuaiji"));
//				r.set("type", wuyingying.getStr("type"));
//				r.set("phone", wuyingying.getStr("phone"));
//				Db.update("allcaiwu",r);
//			}
//			
////			Record yangli = Db.findFirst("select * from yangli where name like '%"+name+"%'");
////			if(null != yangli)
////			{
////			     	
////			     	System.out.println(j+"-----"+name+"----------"+yangli.getStr("name"));
////			     	
////			     	yangli.set("state", 1);
////					Db.update("yangli", yangli);
////					
////					r.set("name", yangli.getStr("name"));
////					r.set("temp", 1);
////					r.set("guwen", yangli.getStr("guwen"));
////					r.set("time", yangli.getStr("time"));
////					r.set("cost", yangli.getStr("cost"));
////					r.set("kuaiji", yangli.getStr("kuaiji"));
////					r.set("type", yangli.getStr("type"));
////					r.set("phone", yangli.getStr("phone"));
////					Db.update("allcaiwu",r);
////			}
////			
////			Record zhengyanyan = Db.findFirst("select * from zhengyanyan where name like '%"+name+"%'");
////			if(null != zhengyanyan)
////			{
////			     	j++;
////			     	System.out.println(j+"-----"+name+"----------"+zhengyanyan.getStr("name"));
////			     	
////			     	zhengyanyan.set("state", 1);
////					Db.update("zhengyanyan", zhengyanyan);
////					
////					r.set("name", zhengyanyan.getStr("name"));
////					r.set("temp", 1);
////					r.set("guwen", zhengyanyan.getStr("guwen"));
////					r.set("time", zhengyanyan.getStr("time"));
////					r.set("cost", zhengyanyan.getStr("cost"));
////					r.set("kuaiji", zhengyanyan.getStr("kuaiji"));
////					r.set("type", zhengyanyan.getStr("type"));
////					r.set("phone", zhengyanyan.getStr("phone"));
////					Db.update("allcaiwu",r);
////			}
////			
//			if(b>=2)
//			{
//				System.out.println(i+"----"+b+"-----"+name);
//			}
//		}
//	}
}
