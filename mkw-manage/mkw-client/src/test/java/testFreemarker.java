import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class testFreemarker {

	@Test
	public void testFreemarker() throws IOException, TemplateException{
		
		//加载模板生成静态页面
		//获取根路径
		String dir="E:\\workspace1\\cy\\mkw-manage\\mkw-client\\src\\main\\webapp\\";
		Configuration config=new Configuration(Configuration.VERSION_2_3_23);
		config.setDirectoryForTemplateLoading(new File(dir));
		Template template = config.getTemplate("ftl\\hello.ftl");
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", "我会");
		Writer out=new FileWriter(dir+"ftl\\hello.html");
		template.process(map, out);
		out.flush();
		out.close();
		
		
	}
}

