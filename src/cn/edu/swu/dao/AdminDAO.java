package cn.edu.swu.dao;

import java.util.List;

import cn.edu.swu.domain.Admin;

public interface AdminDAO {
	
	public List<Admin> getForListWithCriteriaAdmins(CriteriaAdmin aa);
	
	public List<Admin> getALL();
	
	public void save(Admin admin);
	
	public void update(Admin admin);
	
	public Admin get(Integer id);
	
	public void  delete(Integer id);
	
	public long getCountWithName(String name);
	
	public long AdminLogin(String  name, String pass);
	
	
	
	
	
	
	
		


}
