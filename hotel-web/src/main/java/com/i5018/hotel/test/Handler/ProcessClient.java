package com.i5018.hotel.test.Handler;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Header;

public class ProcessClient {
	private static Map<String,Object> root = new HashMap<String,Object>();  
	  
    /** 
     * 调用FreeMarkertUtil.java 
     * FreeMarkertUtil.processTemplate("body.ftl", root, out); 
     * 来生成html文件 
     * @param out 
     */  
    public static void processBody(Writer out){  
        Header h = HeaderService.getHeader();  
        root.put("h", h);  
        Footer f = FooterService.gerFooter();  
        root.put("f", f);  
        List<User> users = UserService.getUsers();  
        root.put("users", users);  
        FreeMarkertUtil.processTemplate("body.ftl", root, out);  
    }  
}
