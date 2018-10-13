package com.mkw.core.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mkw.constant.CodeConstant;
import com.mkw.plugins.DataInfo;
import com.mkw.util.IdUtils;

public class SuperServiceImpl<M extends SuperMapper<T>, T extends SuperEntity> implements SuperService<T>, CodeConstant {
	@Autowired
	protected M mapper;
	
	protected Class<T> clazz;

	/**可用*/
	protected final static Integer ENABLE = 1;
	/**不可用*/
	protected final static Integer DISENABLE = 0;
	
	@SuppressWarnings("unchecked")
	public SuperServiceImpl() {
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        clazz = (Class<T>) params[1];
	}
	
	@Override
	public int insert(T t, String authorId) throws Exception {
		Date date = new Date();
		t.setId(IdUtils.UUID());
		t.setEnable(ENABLE);
		t.setCtime(date);
		t.setCreater(authorId);
		return mapper.insert(t);
	}

	@Override
	public int update(T t, String authorId) throws Exception {
		if (StringUtils.isNotBlank(t.getId())) {
			T temp = select(t.getId());
			// 当前数据可用时执行更新，更新时不执行软删除操作
			if (null != temp && temp.getEnable() == ENABLE) {
				t.setMtime(new Date());
				t.setModifier(authorId);
				t.setEnable(ENABLE);
				return mapper.updateById(t);
			} else {
				throw new Exception("未找到相关操作性数据");
			}
		}
		return 0;
	}

	@Override
	public int updateColumns(T t, String authorId) throws Exception {
		if (StringUtils.isNotBlank(t.getId())) {
			T temp = select(t.getId());
			// 当前数据可用时执行更新，更新时不执行软删除操作
			if (null != temp && temp.getEnable() == ENABLE) {
				t.setMtime(new Date());
				t.setModifier(authorId);
				t.setEnable(ENABLE);
				return mapper.updateAllColumnById(t);
			} else {
				throw new Exception("未找到相关操作性数据");
			}
		}
		return 0;
	}

	@Override
	public int delete(String id, String authorId) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			T temp = select(id);
			if (null != temp && temp.getEnable() == ENABLE) {
				temp.setEnable(DISENABLE);
				temp.setDtime(new Date());
				temp.setDeleter(authorId);
				return mapper.updateById(temp);
			} else {
				throw new Exception("未找到相关操作性数据");
			}
		}
		return 0;
	}

	@Override
	public int delete(String[] ids, String authorId) throws Exception {
		if (null != ids) {
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					T temp = select(id);
					if (null != temp) {
						temp.setEnable(DISENABLE);
						temp.setDtime(new Date());
						temp.setDeleter(authorId);
						mapper.updateById(temp);
					} else {
						throw new Exception("未找到相关操作性数据");
					}
				}
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int updateStatus(String id, int status, String authorId) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			T t = select(id);
			if (null != t) {
				t.setStatus(status);
				return update(t, authorId);
			} else {
				throw new Exception("未找到相关操作性数据");
			}
		}
		return 0;
	}

	@Override
	public T select(String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			T temp = mapper.selectById(id);
			if (null != temp) {
				return ENABLE == temp.getEnable() ? temp : null;
			}
		}
		return null;
	}

	@Override
	public List<T> selectList(T t) throws Exception {
		EntityWrapper<T> wrapper = new EntityWrapper<T>(t);
		wrapper.eq("enable", ENABLE);
		return mapper.selectList(wrapper);
	}
	
	/**
	 * @Description: 清理缓存
	 * @author xiaojiayi
	 * @date 2018年4月4日 下午2:27:21 
	 *
	 * @throws Exception
	 */
	protected void clear(Object... objects) throws Exception {
		for (Object object : objects) {
			if (null != object) {
				object = null;
			}
		}
	}
	
	/**
	 * @Description: 成功
	 * @author xiaojiayi
	 * @date 2018年4月4日 下午2:32:45 
	 *
	 * @param dataInfo
	 * @param object
	 * @throws Exception
	 */
	protected DataInfo success(DataInfo dataInfo, Object object) throws Exception {
		dataInfo = null == dataInfo ? new DataInfo() : dataInfo;
		dataInfo.setResponseData(object);
		dataInfo.setSuccess(true);
		dataInfo.setCode("666666");
		dataInfo.setMsg("成功");
		return dataInfo;
	}
	
	/**
	 * @Description: 失败
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午6:41:28 
	 * @param dataInfo
	 * @param msg
	 * @throws Exception
	 */
	protected DataInfo fail(DataInfo dataInfo, String code) throws Exception {
		dataInfo = null == dataInfo ? new DataInfo() : dataInfo;
		dataInfo.setSuccess(true);
		dataInfo.setCode(code);
		dataInfo.setMsg("失败");
		return dataInfo;
	}
	
	/**
	 * @Description: 转换成字符串数组
	 * @author xiaojiayi 
	 * @date 2018年5月9日 下午10:07:53 
	 * @param jsonArray
	 * @return
	 */
	protected String[] toStringArray(JSONArray jsonArray) throws Exception {
		if (null != jsonArray && 0 < jsonArray.size()) {
			String[] obj = new String[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				obj[i] = String.valueOf(jsonArray.get(i));
			}
			return obj;
		}
		return null;
	}
	
	/**
	 * @Description: 复制数据
	 * @author xiaojiayi 
	 * @date 2018年5月9日 下午10:34:05 
	 * @param poList
	 * @param class1
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List copyProperties(List <? extends Object> poList , Class voClass) throws Exception {
		if (null != poList && 0 < poList.size()) {
			List voList = new ArrayList();
	         
	        Object voObj = null;
	        for(Object poObj : poList){
	        	voObj = voClass.newInstance();
	            BeanUtils.copyProperties(poObj, voObj);
	            voList.add(voObj);
	        }
	        return voList;
		}
		return null;
	}
}
