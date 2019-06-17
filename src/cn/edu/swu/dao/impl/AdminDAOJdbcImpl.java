package cn.edu.swu.dao.impl;

import java.util.List;
import cn.edu.swu.dao.AdminDAO;
import cn.edu.swu.dao.CriteriaAdmin;
import cn.edu.swu.dao.DAO;
import cn.edu.swu.domain.Admin;;

public class AdminDAOJdbcImpl extends DAO<Admin> implements AdminDAO{
	
	
	@Override
	public List<Admin> getALL() {
		String sql = "SELECT id,name,phone,mail,characters FROM admins";
		return getForList(sql);
	}

	@Override
	public void save(Admin admin) {
		String sql = "INSERT INTO admins(name,phone,mail,characters,pass) VALUES(?,?,?,?,?)";
 		update(sql, admin.getName(),admin.getPhone(),admin.getMail(),admin.getCharacters(),admin.getPass());
	}

	@Override
	public Admin get(Integer id) {
		String sql = "SELECT id,name,phone,mail,characters FROM admins WHERE id = ?";
			return get(sql,id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM admins WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "SELECT count(id) FROM admins WHERE name = ?";
		return getForValue(sql, name);
	}

	@Override
	public List<Admin> getForListWithCriteriaAdmins(CriteriaAdmin aa) {
		String sql = "SELECT id,name,phone,mail,characters,date FROM admins WHERE name LIKE ? AND phone LIKE ? AND mail LIKE ?";
		return getForList(sql, aa.getName(), aa.getPhone(), aa.getMail());
	}

	@Override
	public void update(Admin admin) {
		String sql = "UPDATE admins SET name = ?,phone = ?,mail = ?,characters = ?,pass = ?"
				+ "WHERE id = ?";
		update(sql,admin.getName(),admin.getPhone(),admin.getMail(),admin.getCharacters(),admin.getPass(),admin.getId());
	
	}

	@Override
	public long AdminLogin(String name, String pass) {
		String sql = "SELECT count(id) FROM admins WHERE name = ? AND pass = ?";
		return getForValue(sql, name,pass);
	}

	@Override
	public String getcharacter(String name, String pass) {
		String sql = "SELECT characters FROM admins WHERE name = ? AND pass = ?";
		return getForValue(sql, name,pass);
	}
	@Override
	public long getCountWithCharacter(String character) {
		String sql = "SELECT count(id) FROM admins WHERE characters = ?";
		return getForValue(sql, character);
	}


}
