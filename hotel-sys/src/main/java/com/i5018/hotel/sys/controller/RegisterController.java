package com.i5018.hotel.sys.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.i5018.hotel.sys.exception.ServiceException;
import com.i5018.hotel.sys.service.RegisterValidateServiceImpl;

@Controller
public class RegisterController {

    @Autowired
    private RegisterValidateServiceImpl registerValidateService;

    @RequestMapping(value="/user/register",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView  load(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        String action = request.getParameter("action");
        System.out.println("-----r----"+action);
        ModelAndView mav=new ModelAndView();
        if("register".equals(action)) {
            //注册
            String email = request.getParameter("email");
            registerValidateService.processregister(email);//发邮箱激活
            mav.addObject("text","注册成功");
            mav.setViewName("test/register_success");
        } 
        else if("activate".equals(action)) {
            //激活
            String email = request.getParameter("email");//获取email
            String validateCode = request.getParameter("validateCode");//激活码

            try {
            	registerValidateService.processActivate(email , validateCode);//调用激活方法
                mav.setViewName("test/activate_success");
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
                mav.setViewName("test/activate_failure");
            }

        }
        return mav;
    }
   
}
