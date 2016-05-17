package com.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




//import com.app.tools.MD5Tool;
import com.app.tools.MD5Util;
import com.app.tools.SendEmail;
import com.app.tools.ServiceException;
import com.entity.Email;
import com.entity.EmailDao;

/**
 * 
 * @author Qixuan.Chen
 */
@Service
public class RegisterValidateService {
    public void processregister(Integer id,String email){                           
        //user.setValidateCode(MD5Tool.MD5Encrypt(email));            

        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/mail/register?action=activate&id=");
        sb.append(id); 
        sb.append("&validateCode="); 
        sb.append(MD5Util.encode2hex(email));
        sb.append("\">http://localhost:8080/mail/register?action=activate&id="); 
        sb.append(id);
        sb.append("&validateCode=");
        sb.append(MD5Util.encode2hex(email));
        sb.append("</a>");
        SendEmail.send(email, sb.toString());
        System.out.println("发送邮件");
    }

    /**
     * 处理激活
     * @throws ParseException 
     */
      ///传递激活码和email过来
    public void processActivate(Integer id, String validateCode)throws ServiceException, ParseException{  
         //数据访问层，通过email获取用户信息
        
        EmailDao dao=new EmailDao();
        Email user=dao.getId(id);
        //验证用户是否存在 
        if(user!=null) {  
            //验证用户激活状态  
            if(user.getStatus()==0) { 
                ///没激活
                Date currentTime = new Date();//获取当前时间  
                //验证链接是否过期 
              
                if(currentTime.before(user.getLastActivateTime())) {  
                    //验证激活码是否正确  
                    if(validateCode.equals(user.getValidateCode())) {  
                        //激活成功， //并更新用户的激活状态，为已激活 
                        System.out.println("==sq==="+user.getStatus());
                       
                        System.out.println("==sh==="+user.getStatus());
                        dao.updateStatus(id);
                    } else {  
                       throw new ServiceException("激活码不正确");  
                    }  
                } else { throw new ServiceException("激活码已过期！");  
                }  
            } else {
               throw new ServiceException("邮箱已激活，请登录！");  
            }  
        } else {
            throw new ServiceException("该邮箱未注册（邮箱地址不存在）！");  
        }  

    }
    public void saveReg(String acc,String password,String email,String phone,Date sd){
    	
    	EmailDao dao=new EmailDao();
    	Email zhang=new Email();
    	zhang.setRegisterTime(sd);
    	zhang.setAccount(acc);
    	
    	zhang.setStatus(0);
    	zhang.setPassword(password);
    	zhang.setEmail(email);
    	zhang.setPhone(phone);
    	zhang.setValidateCode(MD5Util.encode2hex(email));
    	dao.insertAcc(zhang);
        
    	
    }
    public void jihuo(Integer id){
    	EmailDao da=new EmailDao();    	    	
    	da.updateStatus(id);    	
    }
    
}
