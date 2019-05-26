package cn.edu.swu.domain;

import java.util.Date;

public class Admin{
	
	private Integer id;
	
	private String name;
	
	private String phone;
	
	private String mail;
	
	private String characters;
	
	private Date date;
	
	private String pass;
	
	public Admin() {
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getCharacters() {
		return characters;
	}


	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Admin( String name, String phone, String mail, String characters, String pass) {
		super();
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.characters = characters;
		this.pass = pass;
	}




	

}
