package cn.edu.swu.mvc.dao;

import java.util.List;

import cn.edu.swu.mvc.domain.Customer;

public interface CustomerDAO {
	
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
	
	public List<Customer> getALL();
	
	public void save(Customer customer);
	
	public Customer get(Integer id);
	
	public void  delete(Integer id);
	
	public long getCountWithName(String name);
	
	
	
	
	
	
	
		


}

