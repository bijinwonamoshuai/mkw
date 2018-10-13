package com.mkw.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;

public class JDBCUtils {
	
	//数据库访问地址
    private String url = null;
    //用户名
    private String user = null;
    //密码，这里的密码为空
    private String password = null;
    //驱动名
    private String className = null;
    // 连接
    private static Connection connection;
    
	public JDBCUtils(String url, String user, String password, String className) throws Exception {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
		this.className = className;
		getConnection();
	}

	@SuppressWarnings("resource")
	private void getConnection() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        connection = dataSource.getConnection();
	}
	
	public List<JSONObject> query(String sql) throws Exception {
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		// 获得结果集元数据（元数据就是描述数据的数据，比如把表的列类型列名等作为数据）
		ResultSetMetaData rsmd = rs.getMetaData();
		// 获得列的总数
		int columnCount = rsmd.getColumnCount();

		// 封装返回数据
		List<JSONObject> list = new ArrayList<>();
		while (rs.next()) {
			JSONObject jsonObject = new JSONObject();
			for (int i = 0; i < columnCount; i++) {
				// 根据列索引取得每一列的列名,索引从1开始
				String columnName = rsmd.getColumnName(i + 1);
				// 根据列名获得列值
				Object columnValue = rs.getObject(columnName);
				// 将列名作为key，列值作为值，放入 hm中，每个 hm相当于一条记录
				jsonObject.put(columnName, columnValue);
			}
			list.add(jsonObject);
		}
		rs = null;
		pstmt = null;
		return list;
	}
}
