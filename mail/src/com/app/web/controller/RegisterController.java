package com.app.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.service.impl.RegisterValidateService;
import com.app.tools.ServiceException;
import com.entity.Email;
import com.entity.EmailDao;

@Controller
public class RegisterController{

    @Resource
    private RegisterValidateService service;

    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView  load(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        String action = request.getParameter("action");
        System.out.println("-----r----"+action);
        String acc = request.getParameter("acc");
        String pa = request.getParameter("pa");
        String phone=request.getParameter("phone");
        ModelAndView mav=new ModelAndView();
        
		Date sd=new Date();
        if("register".equals(action)) {
            //注册
            String email = request.getParameter("email");   
            service.saveReg(acc,pa,email,phone,sd);
            EmailDao dao=new EmailDao();
        	Email tii=new Email();
        	tii=dao.getEmail(email);
        	Integer id=tii.getId();
            service.processregister(id,email);//发邮箱激活            
            mav.addObject("text","注册成功");
            mav.setViewName("register_success");
        } 
        else if("activate".equals(action)) {
            //激活                     
            String validateCode = request.getParameter("validateCode");//激活码
            String di = request.getParameter("id");
            int id =Integer.parseInt(di);
           
            try {
                service.processActivate(id, validateCode);//调用激活方法                
                service.jihuo(id);
                mav.setViewName("activate_success");
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
                mav.setViewName("activate_failure");
            }

        }
        return mav;
    }
   
}
