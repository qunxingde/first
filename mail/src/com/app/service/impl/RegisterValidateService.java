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

        StringBuffer sb=new StringBuffer("����������Ӽ����˺ţ�48Сʱ��Ч����������ע���˺ţ�����ֻ��ʹ��һ�Σ��뾡�켤�</br>");
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
        System.out.println("�����ʼ�");
    }

    /**
     * ������
     * @throws ParseException 
     */
      ///���ݼ������email����
    public void processActivate(Integer id, String validateCode)throws ServiceException, ParseException{  
         //���ݷ��ʲ㣬ͨ��email��ȡ�û���Ϣ
        
        EmailDao dao=new EmailDao();
        Email user=dao.getId(id);
        //��֤�û��Ƿ���� 
        if(user!=null) {  
            //��֤�û�����״̬  
            if(user.getStatus()==0) { 
                ///û����
                Date currentTime = new Date();//��ȡ��ǰʱ��  
                //��֤�����Ƿ���� 
              
                if(currentTime.before(user.getLastActivateTime())) {  
                    //��֤�������Ƿ���ȷ  
                    if(validateCode.equals(user.getValidateCode())) {  
                        //����ɹ��� //�������û��ļ���״̬��Ϊ�Ѽ��� 
                        System.out.println("==sq==="+user.getStatus());
                       
                        System.out.println("==sh==="+user.getStatus());
                        dao.updateStatus(id);
                    } else {  
                       throw new ServiceException("�����벻��ȷ");  
                    }  
                } else { throw new ServiceException("�������ѹ��ڣ�");  
                }  
            } else {
               throw new ServiceException("�����Ѽ�����¼��");  
            }  
        } else {
            throw new ServiceException("������δע�ᣨ�����ַ�����ڣ���");  
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
