package cn.edu.swu;

import java.util.List;

public class User {
	private String username;
    private List<Authority> authorities;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public User(String username, List<Authority> authorities) {
		super();
		this.username = username;
		this.authorities = authorities;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    


}
