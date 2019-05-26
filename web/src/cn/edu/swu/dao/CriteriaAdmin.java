package cn.edu.swu.dao;

/**
 * @author zsl
 *
 */
public class CriteriaAdmin {
	
	private String name;
	
	private String phone;
	
	private String mail;


	public String getName() {
		if (name == null) {
			name = "%%";
		} else {
			name = "%" + name + "%";
 		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		if (phone == null) {
			phone = "%%";
		} else {
			phone = "%" + phone + "%";
 		}
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		if (mail == null) {
			mail = "%%";
		} else {
			mail = "%" + mail + "%";
 		}
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/*public String getCharacter() {
		if (characters == null) {
			characters = "%%";
		} else {
			characters = "%" + characters + "%";
 		}
		return characters;
	}

	public void setCharacter(String characters) {
		this.characters = characters;
	}*/

	public CriteriaAdmin(String name, String phone, String mail) {
		super();
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		//this.characters = characters;
		//this.date = date;
	}


	
	
	
}
