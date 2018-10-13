package com.mkw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Title: ExcelUtil.java
 * @Package cn.zeale.utils
 * @author 邱发红
 * @date 2015年10月20日 下午2:49:07
 * @version V1.0
 * @Description: (Excel导入导出,支持Excel2007版及以上)
 * @ModifyPeople (修改人:)
 * @ModifyDate (修改时间:2014年9月5日 下午2:49:07)
 * @ModifyContent (修改内容:)
 */
@SuppressWarnings({ "unused", "rawtypes", "unchecked", "deprecation" })
public class ExcelUtil {
	protected static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static FileInputStream input;
	
	public static void main(String[] args) {
		/*String[] rowName = {"编号"};
		String[] cellName = {"no"};
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("no", 1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("no", 2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("no", 3);
		
		List<Map<String, Object>> dataset = new ArrayList<>();
		dataset.add(map1);
		dataset.add(map2);
		dataset.add(map3);
		
		try {
			XSSFWorkbook wb = ExcelUtil.autoWriteExcel(dataset, rowName, cellName, "测试");
			File file = new File("e://播放日志.xlsx");
		    if (!file.exists()) {
			    file.createNewFile();
		    }
			OutputStream outputStream = new FileOutputStream(file);
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
	
	/**
     * 读取excel中的数据
     */
    public static List<List<String>> getContentByExcel(String path) {
        if (path != null && !path.equals("")) {
            String ext = getExt(path);
            if (ext!=null && !ext.equals("")) {
                if (ext.equals("xls")) {
                    return readXls(path);
                } else if (ext.equals("xlsx")) {
                    return readXlsx(path);
                }
            }
        }
        return null;
    }
	
	/**
     * 读取excel中的数据
     */
    public static List<JSONObject> excelToJson(String path) {
        if (path != null && !path.equals("")) {
            String ext = getExt(path);
            if (ext!=null && !ext.equals("")) {
                if (ext.equals("xls")) {
                    return readXlsToJson(path);
                } else if (ext.equals("xlsx")) {
                    return readXlsxToJson(path);
                }
            }
        }
        return null;
    }
    
    /**
     * 获取文件扩展名
     */
    private static String getExt(String path) {
        if (path == null || path.equals("") || !path.contains(".")) {
            return null;
        } else {
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
    }
    
    @SuppressWarnings({ "static-access", "resource" })
	private static List<JSONObject> readXlsxToJson(String path) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<JSONObject> list = new ArrayList<JSONObject>();
        if(xssfWorkbook!=null){
            // 表
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // 行
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                    	JSONObject jsonObject = new JSONObject();
						
                    	//列
                    	for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
                    		if (null != xssfRow.getCell(i)) {
                    			//先设置为string
                        		xssfRow.getCell(i).setCellType(xssfRow.getCell(i).CELL_TYPE_STRING);
                        		//获取数据
    							jsonObject.put(getValue(xssfSheet.getRow(0).getCell(i)), getValue(xssfRow.getCell(i)));
							}
						}
                        list.add(jsonObject);
                        jsonObject = null;
                    }
                }
            }
            xssfWorkbook = null;
        }
        return list;
    }
    
    @SuppressWarnings({ "static-access", "resource" })
	private static List<List<String>> readXlsx(String path) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> list = new ArrayList<List<String>>();
        if(xssfWorkbook!=null){
            // 表
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // 行
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                    	List<String> strings = new ArrayList<>();
						
                    	//列
                    	for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
                    		if (null != xssfRow.getCell(i)) {
                    			//先设置为string
                        		xssfRow.getCell(i).setCellType(xssfRow.getCell(i).CELL_TYPE_STRING);
                        		//获取数据
    							strings.add(getValue(xssfRow.getCell(i)));
							}
						}
                        list.add(strings);
                        strings = null;
                    }
                }
            }
            xssfWorkbook = null;
        }
        return list;
    }
    
    /**
     * 读取后缀为xls的excel文件的数据
     */
    @SuppressWarnings({ "static-access", "resource" })
	private static List<List<String>> readXls(String path) {

        HSSFWorkbook hssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            hssfWorkbook = new HSSFWorkbook(is);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> list = new ArrayList<List<String>>();
        if (hssfWorkbook != null) {
            // 表
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 行
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
						List<String> strings = new ArrayList<>();
						
                    	//列
                    	for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                    		if (null != hssfRow.getCell(i)) {
                        		//先设置为string
                        		hssfRow.getCell(i).setCellType(hssfRow.getCell(i).CELL_TYPE_STRING);
                        		//获取数据
    							strings.add(getValue(hssfRow.getCell(i)));
							}
						}
                        list.add(strings);
                        strings = null;
                    }
                }
            }
            hssfWorkbook = null;
        }
        return list;
    }
    
    /**
     * 读取后缀为xls的excel文件的数据
     */
    @SuppressWarnings({ "static-access", "resource" })
	private static List<JSONObject> readXlsToJson(String path) {

        HSSFWorkbook hssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            hssfWorkbook = new HSSFWorkbook(is);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<JSONObject> list = new ArrayList<JSONObject>();
        if (hssfWorkbook != null) {
            // 表
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 行
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                    	JSONObject jsonObject = new JSONObject();
						
                    	//列
                    	for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                    		if (null != hssfRow.getCell(i)) {
                        		//先设置为string
                        		hssfRow.getCell(i).setCellType(hssfRow.getCell(i).CELL_TYPE_STRING);
                        		//获取数据
                        		jsonObject.put(getValue(hssfSheet.getRow(0).getCell(i)), getValue(hssfRow.getCell(i)));
							}
						}
                        list.add(jsonObject);
                        jsonObject = null;
                    }
                }
            }
            hssfWorkbook = null;
        }
        return list;
    }
    
    @SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
    
    @SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
    public static XSSFWorkbook autoWriteExcel(List<?> dataset, String[] headerColumns, String[] fieldColumns, String sheetName) {
    	if (null != dataset && 0 < dataset.size()) {
    		// 每页数量
    		int pageCount = 60000;
			// 数据总量
    		int size = dataset.size();
    		// 余数
    		int residue = size % pageCount;
    		// 计算sheet数量
    		int sheetCount = size / pageCount + (residue > 0 ? 1 : 0);
    		
    		XSSFWorkbook workbook = new XSSFWorkbook();
    		for (int i = 0; i < sheetCount; i++) {
        		XSSFSheet sheet = workbook.createSheet(sheetName + i);
        		generateHeader(workbook, sheet, headerColumns);
        		XSSFCellStyle style = getCellStyle(workbook, false);
        		
        		int index = i * pageCount;
        		for (int k = 0; k < pageCount; k++) {
					if (index < size) {
						// 创建第i+1行
						XSSFRow rowi = sheet.createRow(k + 1);
						for (int j = 0; j < fieldColumns.length; j++) {
							XSSFCell cell = rowi.createCell(j);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellStyle(style);
							String str = null;
							try {
								str = getProperty((Map) dataset.get(index), fieldColumns[j]);
							} catch (Exception e) {
								logger.info("", "该对象中不存在对应属性!");
							}
							cell.setCellValue(str);// 设置值
						}
					}
					index++;
				}
			}
    		return workbook;
		}
    	return null;
    }

	/**
	 * 
	 * @Description (导出数据到Excel,一张sheet表)
	 * @param dataList  数据列表
	 * @param rowName   列名
	 * @param cellName  属性名
	 * @param sheetName  工作簿的名称
	 * @return XSSFWorkbook 返回类型
	 * @date 2015-10-13下午2:11:00
	 * @author qiufh
	 */
	public static XSSFWorkbook exportToExcel(List<?> dataset, String[] headerColumns, String[] fieldColumns, String sheetName) {
		logger.info("", "开始创建excel对象！");
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		XSSFWorkbook workbook = new XSSFWorkbook();
		/*if (dataset == null || dataset.size() == 0) {
			logger.info("", "导出excel失败，没有数据");
			return null;
		}*/
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		XSSFSheet sheet = workbook.createSheet(sheetName);
		generateHeader(workbook, sheet, headerColumns);
		XSSFCellStyle style = getCellStyle(workbook, false);
		for (int i = 0; i < dataset.size(); i++) {
			if (dataset.get(i) != null && !"".equals(dataset.get(i))) {
				// 创建第i+1行
				XSSFRow rowi = sheet.createRow(i + 1);
				for (int j = 0; j < fieldColumns.length; j++) {
					XSSFCell cell = rowi.createCell(j);// 创建该行的单元格
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// cell.setEncoding(HSSFCell.); // 设置编码
					cell.setCellStyle(style);// 应用格式
					String str = null;
					try {

						str = getProperty((Map) dataset.get(i), fieldColumns[j]);
					} catch (Exception e) {
						logger.info("", "该对象中不存在对应属性!");
						throw new RuntimeException("该对象中不存在对应属性!", e);
					}
					cell.setCellValue(str);// 设置值
				}
			}

		}
		logger.info("", "excel对象创建成功！");
		return workbook;
	}

	/**
	 * 
	 * @Description (导出数据到Excel，多张sheet表)
	 * @param datas
	 * @return XSSFWorkbook
	 * @date 2016年4月12日下午6:13:42
	 * @author qiufh
	 */
	public static XSSFWorkbook exportToExcel(List<Map<String, Object>> datas) {
		logger.info("", "开始创建excel对象！");
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (Map<String, Object> maps : datas) {
			String sheetName = (String) maps.get("sheetName");
			String[] headerColumns = (String[]) maps.get("headerColumns");
			String[] fieldColumns = (String[]) maps.get("fieldColumns");
			List<?> dataset = (List<?>) maps.get("dataset");
			if (dataset == null || dataset.size() == 0) {
				logger.info("", "导出excel失败，没有数据");
				return null;
			}
			// 创建Excel的工作sheet,对应到一个excel文档的tab
			XSSFSheet sheet = workbook.createSheet(sheetName);
			generateHeader(workbook, sheet, headerColumns);
			XSSFCellStyle style = getCellStyle(workbook, false);
			for (int i = 0; i < dataset.size(); i++) {
				if (dataset.get(i) != null && !"".equals(dataset.get(i))) {
					// 创建第i+1行
					XSSFRow rowi = sheet.createRow(i + 1);
					for (int j = 0; j < fieldColumns.length; j++) {
						XSSFCell cell = rowi.createCell(j);// 创建该行的单元格
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						// cell.setEncoding(HSSFCell.); // 设置编码
						cell.setCellStyle(style);// 应用格式
						String str = null;
						try {
							str = getProperty((Map) dataset.get(i), fieldColumns[j]);
							/*	//将数字换换成对应的文字(具体业务具体实现)
							if (value.equals("templateType")) {
								str = ActivityTemplateTypeEnum.toTagName(Integer.parseInt(cellValue));
							}*/
						} catch (Exception e) {
							logger.info("", "该对象中不存在对应属性!");
							throw new RuntimeException("该对象中不存在对应属性!", e);
						}
						cell.setCellValue(str);// 设置值
					}
				}
			}

		}
		logger.info("", "excel对象创建成功！");
		return workbook;
	}

	/**
	 * 
	 * @Description (得到某个对象的属性值)
	 * @param obj  对象
	 * @param fieldName  属性名
	 * @return Object 属性值
	 * @date 2015-10-13下午2:14:22
	 * @author qiufh
	 */
	private static String getProperty(Map map, String fieldName) {
		String string = "";
		try {
			string = (String) map.get(fieldName);
		} catch (Exception e) {
			string = String.valueOf(map.get(fieldName));
		}
		
		return map.get(fieldName) == null ? "" : string;
	}

	/**
	 * 
	 * @Description (通过反射得到某个对象的属性值)
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException Object
	 * @date 2016年4月12日下午4:52:26
	 * @author qiufh
	 */
	private static String getProperty(Object obj, String fieldName) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<? extends Object> ownerClass = obj.getClass();
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
		Object value = ownerClass.getMethod("get" + fieldName).invoke(obj, new Object[] {});
		String cellValue = "";
		if (value instanceof Date) {
			Date date = (Date) value;
			cellValue = TimeUtils.findDate(date, "yyyy-MM-dd HH:mm:ss");
		} else {
			cellValue = null != value ? value.toString() : "";
		}
		return cellValue;
	}

	// 获取单元格数据内容为字符串类型的数据
	private static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: // 字符串格式
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC: // 数字格式
			if (DateUtil.isCellDateFormatted(cell)) {// 日期格式
				strCell = TimeUtils.findDate(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
			} else {// 数字格式
				double value = cell.getNumericCellValue();
				String valueStr = String.valueOf(value);
				String valueStrEnd = valueStr.substring(valueStr.lastIndexOf(".") + 1, valueStr.length());
				if (valueStrEnd.matches("^[0]*$")) {
					strCell = String.valueOf((int) value);
				} else {
					strCell = valueStr;
				}
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN: // BOOLEAN格式
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 
	 * @Description (设置Excel表格样式)
	 * @param workbook
	 * @param isHeader
	 * @return XSSFCellStyle
	 * @date 2016年4月12日下午4:30:28
	 * @author qiufh
	 */
	private static XSSFCellStyle getCellStyle(XSSFWorkbook workbook, boolean isHeader) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setLocked(true);
		if (isHeader) {
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			XSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
		return style;
	}

	/**
	 * 
	 * @Description (设置sheet表格头(列))
	 * @param workbook
	 * @param sheet
	 * @param headerColumns void
	 * @date 2016年4月12日下午4:31:51
	 * @author qiufh
	 */
	private static void generateHeader(XSSFWorkbook workbook, XSSFSheet sheet, String[] headerColumns) {
		XSSFCellStyle style = getCellStyle(workbook, true);
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);
		for (int i = 0; i < headerColumns.length; i++) {
			Cell cell = row.createCell(i);
			String column = headerColumns[i];
			sheet.setColumnWidth(i, Integer.valueOf(7000));
			cell.setCellValue(column);
			cell.setCellStyle(style);
		}
	}

	/**
	 * 
	 * @Description (将准备数据封装成Map)
	 * @param sheetName
	 * @param headerColumns
	 * @param fieldColumns
	 * @param obj
	 * @param datas void
	 * @date 2016年4月12日下午4:33:08
	 * @author qiufh
	 */
	public static List<Map<String, Object>> addToExcelMap(String sheetName, String[] headerColumns, String[] fieldColumns,
			Object obj) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", sheetName);
		map.put("headerColumns", headerColumns);
		map.put("fieldColumns", fieldColumns);
		map.put("dataset", obj);
		datas.add(map);
		return datas;
	}

	// 读取Excel表格表头的内容
	public static List<?> readExcelTitle(Workbook wb, int numberOfSheet) {
		List titleList = new ArrayList();
		logger.info("", "开始导入表头信息");
		Sheet sheet = wb.getSheetAt(numberOfSheet);
		// 得到标题的内容对象。
		row = (XSSFRow) sheet.getRow(0);
		// 得到标题总列数
		int colNum = row.getLastCellNum();//getPhysicalNumberOfCells();
		for (int i = 0; i < colNum; i++) {
			String cellValue = getStringCellValue(row.getCell(i));
			titleList.add(cellValue);
		}
		logger.info("", "导入表头信息结束");
		return titleList;
	}

	// 根据文件名读取excel文件
	public static List<Map<String, Object>> read(String fileName) {
		List<Map<String, Object>> dataset = null;
		// 检查文件名是否为空或者是否是Excel格式的文件
		if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			return dataset;
		}
		boolean isExcel2003 = true;
		// 对文件的合法性进行验
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		// 检查文件是否存在
		File file = new File(fileName);
		if (file == null || !file.exists()) {
			return dataset;
		}
		try {
			// 读取excel
			dataset = read(new FileInputStream(file), isExcel2003);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dataset;
	}

	// 根据流读取Excel文件
	private static List<Map<String, Object>> read(InputStream inputStream, boolean isExcel2003) {
		List<Map<String, Object>> dataset = null;
		try {
			// 根据版本选择创建Workbook的方式
			Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			dataset = read(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	// 读取数据
	private static List<Map<String, Object>> read(Workbook wb) {
		List<Map<String, Object>> dataset = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dataLst = null;
		Map<String, Object> dataMap = null;
		int numberOfSheets = wb.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			//Sheet sheetAt = wb.getSheetAt(i);
			Sheet sheet = wb.getSheetAt(i);// 得到第一个shell
			String sheetName = sheet.getSheetName();
			Integer totalRows = sheet.getLastRowNum();//总行数,获取的是最后一行的编号（编号从0开始）。
			//int totalRow = sheet.getPhysicalNumberOfRows();//总行数,获取的是物理行数，也就是不包括那些空行（隔行）的情况。
			if (sheet.getPhysicalNumberOfRows() > 0 && sheet.getRow(0) != null) {
				List<?> readExcelTitles = readExcelTitle(wb, i);
				dataMap = new LinkedHashMap<String, Object>();
				dataLst = new ArrayList<Map<String, Object>>();
				int totalCells = sheet.getRow(0).getLastCellNum();//.getPhysicalNumberOfCells();//总列数
				// 循环Excel的行
				for (int r = 0; r <= totalRows; r++) {
					Row row = sheet.getRow(r);//拿到行
					if (row == null) {
						continue;
					}
					ArrayList<String> rowLst = new ArrayList<String>();
					Map<String, Object> linkedHasMap = new LinkedHashMap();
					// 循环Excel的列
					for (short c = 0; c < totalCells; c++) {
						Cell cell = row.getCell(c);
						String cellValue = "";
						if (cell == null) {
							rowLst.add(cellValue);
							continue;
						}
						cellValue = getStringCellValue(cell);
						//rowLst.add(cellValue);
						linkedHasMap.put((String) readExcelTitles.get(c), cellValue);
					}
					dataLst.add(linkedHasMap);
				}
				//dataset.add(dataLst);
				dataMap.put("sheetName", sheetName);
				dataMap.put("dataset", dataLst);
				dataset.add(dataMap);
			}
		}
		return dataset;
	}

	/**
	 * 
	 * @Description (将Excel保存到本地磁盘)
	 * @param wb
	 * @param xlsName void
	 * @date 2015-10-19下午3:06:00
	 * @author qiufh
	 */
	public static void writeLocal(XSSFWorkbook wb, String realPath, String xlsName) {
		logger.info("", "将Excel保存到服务器");
		// 初始化变量
		FileOutputStream fileOut = null;
		try {
			File targetFile = new File(realPath, xlsName);
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			xlsName = URLDecoder.decode(xlsName, "UTF-8");
			fileOut = new FileOutputStream(realPath + xlsName);
		} catch (Exception e) {
			// 抛出异常
			throw new RuntimeException("将Excel表保存到服务器失败,错误消息： " + e.getMessage(), e);
		} finally {
			try {
				if (wb != null)
					wb.write(fileOut);
			} catch (IOException e) {
				wb = null;
			} finally {
				try {
					if (fileOut != null)
						fileOut.close();
				} catch (IOException e) {
					fileOut = null;
				}
			}
		}
	}

	/**
	 * 
	 * @Description (下载本地Excel到客户端)
	 * @param response
	 * @param realPath   保存路径
	 * @param xlsName   xls名称
	 *   void
	 * @date 2015-10-22上午10:14:46
	 * @author qiufh
	 */
	public static void downloadExcel(HttpServletResponse response, String realPath, String xlsName) {
		logger.info("", "下载本地Excel到客户端");
		// 先建立一个文件读取流去读取这个临时excel文件
		FileInputStream fs = null;
		String excelName = null;
		OutputStream os = null;
		int len = 0;
		try {
			fs = new FileInputStream(realPath + xlsName);
			// 设置响应头和保存文件名
			excelName = URLEncoder.encode(xlsName, "UTF-8");
			//HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
			response.setCharacterEncoding("utf-8");
			os = response.getOutputStream();
			// 写出流信息
			while ((len = fs.read()) != -1) {
				os.write(len);
			}
		} catch (Exception e) {
			// 抛出异常
			throw new RuntimeException("下载服务器Excel到客户端失败,错误消息： " + e.getMessage(), e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				os = null;
				e.printStackTrace();
			} finally {
				try {
					if (fs != null) {
						fs.close();
					}
				} catch (IOException e) {
					fs = null;
					e.printStackTrace();
				}
			}
			//删除源文件
			File file = new File(realPath + xlsName);
			if (file.isFile() & file.exists()) {
				file.delete();
			}
		}
	}

}
