package com.service.javacode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static JSONObject tasteJSON = new JSONObject();
	public static JSONArray menuArray = new JSONArray();
	public static JSONArray packageArray = new JSONArray();
	
	public static void main(String[] args) {
		try {
			String result = readToString("E://doc/food.txt");
			JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
			JSONArray jsonArray = jsonObject.getJSONArray("menu");
			for (Object object : jsonArray) {
				JSONObject obj = (JSONObject) object;
				
				JSONObject menu = new JSONObject();
				menu.put("id", obj.get("menuId"));
				menu.put("icon", obj.get("menuIcon"));
				menu.put("name", obj.get("menuName"));
				menuArray.add(menu);
				
				JSONArray packages = obj.getJSONArray("packages");
				if (null != packages && 0 < packages.size()) {
					int size = packages.size();
					for (int i = 0; i < size; i++) {
						JSONObject p = packages.getJSONObject(i);
						
						JSONObject food = new JSONObject();
						food.put("img", p.get("packageImg"));
						food.put("numberRemaining", p.get("numberRemaining"));
						food.put("originalPrice", p.get("originalPrice"));
						food.put("samePackage", p.get("samePackage"));
						food.put("id", p.get("packageId"));
						food.put("type", p.get("type"));
						food.put("isSpecials", p.get("isSpecials"));
						food.put("price", p.get("price"));
						food.put("dishesNames", p.getJSONArray("dishesNames").toJSONString());
						food.put("name", p.get("packageName"));
						food.put("menuId", p.get("menuId"));
						food.put("status", p.get("status"));
						
						JSONArray taste = p.getJSONArray("taste");
						if (null != taste && 0 < taste.size()) {
							for (Object t : taste) {
								JSONObject temp = (JSONObject) t;
								
								tasteJSON.put(temp.getString("tasteId"), temp.getString("tasteName"));
							}
						}
						
						food.put("taste", taste);
						packageArray.add(food);
					}
				}
			}
			
			System.out.println(tasteJSON.toJSONString());
			System.out.println(menuArray.toJSONString());
			System.out.println(packageArray.toJSONString());
			
			Connection connection = DriverManager.getConnection("jdbc:mysql:loadbalance://192.168.1.20:3306/mkw", "root", "root123");
			PreparedStatement ps = null;
			
			System.out.println("口味处理开始...");
			Set<Entry<String, Object>> entrySet = tasteJSON.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				String value = String.valueOf(entry.getValue());
				String sql = String.format("insert into food_taste(id, name) values('%s', '%s')", key, value);
				System.out.println(sql);
				ps = connection.prepareStatement(sql);
				ps.executeUpdate();
			}
			System.out.println("口味处理结束...");
			
			System.out.println("菜单处理开始...");
			for (int i = 0; i < menuArray.size(); i++) {
				JSONObject params = menuArray.getJSONObject(i);
				
				String sql = String.format("insert into food_menu(id, name, icon) values('%s', '%s', '%s')", params.getString("id"), params.getString("name"), params.getString("icon"));
				System.out.println(sql);
				ps = connection.prepareStatement(sql);
				ps.executeUpdate();
			}
			System.out.println("菜单处理结束...");
			
			System.out.println("菜品处理开始...");
			for (int i = 0; i < packageArray.size(); i++) {
				JSONObject params = packageArray.getJSONObject(i);
				
				String sql = String.format("insert into food_package(id, menu_id, img, number_remaining, original_price, same_package, type, is_specials, price, dishes_names, name) values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
						params.getString("id"), 
						params.getString("menuId"), 
						params.getString("img"), 
						params.getString("numberRemaining"), 
						params.getString("originalPrice"), 
						params.getString("samePackage"), 
						params.getString("type"), 
						params.getString("isSpecials"), 
						params.getString("price"), 
						params.getString("dishesNames"), 
						params.getString("name"));
				System.out.println(sql);
				ps = connection.prepareStatement(sql);
				ps.executeUpdate();
				
				JSONArray list = params.getJSONArray("taste");
				for (int j = 0; j < list.size(); j++) {
					JSONObject taste = list.getJSONObject(j);
					sql = String.format("insert into food_package_taste(id, package_id, taste_id) values('%s', '%s', '%s')", 
							UUID.randomUUID().toString().replace("-", ""), 
							params.getString("id"), 
							taste.getString("tasteId"));
					System.out.println(sql);
					ps = connection.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
			System.out.println("菜品处理结束...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }
}
