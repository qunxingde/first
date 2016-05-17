package com.entity;
import java.util.List;
import java.beans.Transient;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.entity.Email;
@Repository
public class EmailDao {	
	Configuration conf = new Configuration().configure();
	SessionFactory sf=conf.buildSessionFactory();
	
	public void insertAcc(Email mail){
		Session session=sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(mail);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public Email getAcc(String acc) {
		Session session = sf.openSession();
		Email dep1;
		try {
			dep1 = (Email) session.get(Email.class, acc);
		} finally {
			session.close();
		}
		return dep1;
	}
	public Email getId(Integer id)throws ParseException {
		Session session = sf.openSession();
		Email dep1;
		try {
			dep1 = (Email) session.get(Email.class, id);
		} finally {
			session.close();
		}
		return dep1;
	}
	public Email getEmail(String email)throws ParseException {
		Session session = sf.openSession();
		Email user=null;
			Query query = session.createQuery("from Email dep2 where dep2.email=email");
			List list=query.list();	
			Iterator it =list.iterator();
			while(it.hasNext()){
				user=(Email)it.next();
			}
			session.close();		
		return user;
	}

	public void updateAcc(String acc, String pa,String email,String phone) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Email dep = this.getAcc(acc);
			dep.setPassword(pa);
			dep.setEmail(email);
			dep.setPhone(phone);
			session.update(dep);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateStatus(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Email dep = this.getId(id);
			dep.setStatus(1);
			session.update(dep);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	public void delDep(String acc) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Email dep = this.getAcc(acc);
			session.delete(dep);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

}
