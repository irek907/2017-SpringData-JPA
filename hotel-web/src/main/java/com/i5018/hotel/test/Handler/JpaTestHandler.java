package com.i5018.hotel.test.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i5018.hotel.test.bean.Employee;
import com.i5018.hotel.test.service.DepartmentService;
import com.i5018.hotel.test.service.JpaTestService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
public class JpaTestHandler {

	@Autowired
	private JpaTestService jpaTestService;

	@Autowired
	private DepartmentService departmentService;
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id!=null){
			Employee e = jpaTestService.get(id);
			e.setDepartment(null);
			map.put("employee", e);
		}
	}
	
	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {

		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Page<Employee> page = jpaTestService.getPage(pageNo, 5);
		map.put("page", page);
		
		return "list";

	}

	@RequestMapping(value="input",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "input";
	}
	
	@ResponseBody
	@RequestMapping(value="ajax",method=RequestMethod.POST)
	public String ajax(@RequestParam(value="lastName",required=true) String lastName){
		Employee e = jpaTestService.getByLastName(lastName);
		if(e==null){
			return "0";
		}else{
			return "1";
		}
	}
	
	@RequestMapping(value="input",method=RequestMethod.POST)
	public String save(Employee e){
		jpaTestService.save(e);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String,Object> map){
		Employee e = jpaTestService.get(id);
		map.put("employee", e);
		map.put("departments", departmentService.getAll());
		return "input";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.PUT)
	public String update(Employee e){
		jpaTestService.save(e);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		jpaTestService.delete(id);
		return "redirect:/list";
	} 
	
	@RequestMapping(value="qry",method=RequestMethod.GET)
	public String get(){
		////////////
		long x = jpaTestService.getRowCount();
		System.out.println(x);
		return ""+x;
	} 
	
	@RequestMapping(value="freemarker",method=RequestMethod.GET)
	public String freemarker(HttpServletRequest request,HttpServletResponse response){
		
		String dirPath = request.getSession().getServletContext().getRealPath("/templates/html");  
		String basePath = request.getSession().getServletContext().getRealPath("");
		System.out.println(basePath);
/*
		//生成html的位置  
        String dirPath = request.getSession().getServletContext().getRealPath("/templates/html");  
        //文件名字  
        String indexFileName = "index.html";  
          
        //删除原来的文件  
        //delOldHtml(dirPath,indexFileName);  
          
        //防止浏览器缓存，用于重新生成新的html  
        UUID uuid = UUID.randomUUID();  
        Writer out = new OutputStreamWriter(new FileOutputStream(dirPath+"/"+uuid+indexFileName),"UTF-8");  
        ProcessClient.processBody(out);  
        response.sendRedirect("templates/html/"+uuid+"index.html");  */
		
		   try {  
	            //创建一个合适的Configration对象  
	            Configuration configuration = new Configuration();  
	            configuration.setDirectoryForTemplateLoading(new File(dirPath));  
	            configuration.setObjectWrapper(new DefaultObjectWrapper());  
	            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
	            //获取或创建一个模版。  
	            Template template = configuration.getTemplate("static.html");  
	            Map<String, Object> paramMap = new HashMap<String, Object>();  
	            paramMap.put("description", "我正在学习使用Freemarker生成静态文件！");  
	              
	            List<String> nameList = new ArrayList<String>();  
	            nameList.add("陈靖仇");  
	            nameList.add("玉儿");  
	            nameList.add("宇文拓");  
	            paramMap.put("nameList", nameList);  
	              
	            Map<String, Object> weaponMap = new HashMap<String, Object>();  
	            weaponMap.put("first", "轩辕剑");  
	            weaponMap.put("second", "崆峒印");  
	            weaponMap.put("third", "女娲石");  
	            weaponMap.put("fourth", "神农鼎");  
	            weaponMap.put("fifth", "伏羲琴");  
	            weaponMap.put("sixth", "昆仑镜");  
	            weaponMap.put("seventh", null);  
	            paramMap.put("weaponMap", weaponMap);  
	              
	            Writer writer  = new OutputStreamWriter(new FileOutputStream(basePath+"/WEB-INF/jsp/"+"/success.jsp"),"UTF-8");  
	            template.process(paramMap, writer);  
	              
	            System.out.println("恭喜，生成成功~~");  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        }  
		
		return "success";
	} 
}
