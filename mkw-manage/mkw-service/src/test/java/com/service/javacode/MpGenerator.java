package com.service.javacode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {
	
	static class Db {
		public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
		public final static String USER_NAME = "root";
		public final static String PASSWORD = "root123";
		public final static String URL = "jdbc:mysql://192.168.1.20:3306/mkw";
		public final static String[] TABLES = new String[] { "room_food", "room_group" };
	}
	
	// 代码生成
	public static void main(String[] args) {
		findApplicationPath ();
		
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(javaPath);
		gc.setFileOverride(true);
		gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("xiaojiayi");

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName(Db.DRIVER_NAME);
		dsc.setUsername(Db.USER_NAME);
		dsc.setPassword(Db.PASSWORD);
		dsc.setUrl(Db.URL);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setInclude(Db.TABLES);

		// 继承
		strategy.setSuperEntityColumns(new String[] { "id", "enable", "status", "ctime", "creater", "mtime", "modifier", "dtime", "deleter", "remark" });
		strategy.setSuperEntityClass("com.mkw.core.common.SuperEntity");
		strategy.setSuperMapperClass("com.mkw.core.common.SuperMapper");
		strategy.setSuperServiceClass("com.mkw.core.common.SuperService");
		strategy.setSuperServiceImplClass("com.mkw.core.common.SuperServiceImpl");
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.mkw.core");
		mpg.setPackageInfo(pc);
		
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        
        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
 		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
 			@Override
 			public String outputFile(TableInfo tableInfo) {
 				return xmlPath + "/" + tableInfo.getEntityName() + "Mapper.xml";
 			}
 		});
 		cfg.setFileOutConfigList(focList);
 		mpg.setCfg(cfg);
		
		//设置模板
		TemplateConfig tc = new TemplateConfig();
        tc.setEntity("templates/entity.java.vm");
        tc.setService("templates/service.java.vm");
        tc.setServiceImpl("templates/serviceImpl.java.vm");
		tc.setXml(null);
		tc.setController(null);
        mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();

		System.out.println("execute code ok...");
	}
	
	private static String javaPath = null;
	private static String xmlPath = null;
	
	private static void findApplicationPath () {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getFile();
		path = path.substring(1, path.length()).replace("/target/test-classes/", "");
		
		javaPath = path + "/src/main/java";
		xmlPath = path + "/src/main/resources/sqlmap";
	}
}
