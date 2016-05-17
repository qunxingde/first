package com.entity;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

/**
 * Email entity. @author MyEclipse Persistence Tools
 */

public class Email implements java.io.Serializable {

	// Fields
	private Date  registerTime;
	private Integer id;
	private String account;
	private String password;
	private String email;
	private String phone;
	private Integer status;
	private String validateCode;

	// Constructors

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	/** default constructor */
	public Email() {
	}

	/** minimal constructor */
	public Email(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Email(Integer id, String account, String password, String email,
			String phone, Integer status,Date registerTime) {
		this.id = id;		
		this.registerTime=registerTime;
		this.account = account;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	@Transient
    public Date getLastActivateTime() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(registerTime);
        cl.add(Calendar.DATE , 2);

        return cl.getTime();
    }

}