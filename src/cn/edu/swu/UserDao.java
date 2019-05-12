package cn.edu.swu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
	
	private static Map<String, User> users;
	private static List<Authority> authorities = null;
	static {
		authorities= new ArrayList<>();
		authorities.add(new Authority("Article-1","/article-1.jsp"));
		authorities.add(new Authority("Article-2","/article-2.jsp"));
		authorities.add(new Authority("Article-3","/article-3.jsp"));
		authorities.add(new Authority("Article-4","/article-4.jsp"));
	
		users = new HashMap<String, User>();
	
		User user1 = new User("AAA",authorities.subList(0, 2));
		users.put("AAA",user1);
	
		
		User user2 = new User("BBB",authorities.subList(2, 4));
		users.put("BBB",user2);
	
	}
	
	
    User get(String username) {
    	return users.get(username);
    }
    void update(String username, List<Authority> authorities)
    {
    	users.get(username).setAuthorities(authorities);
    }
	public List<Authority> getAuthorites() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public List<Authority> getAuthorites(String[] urls) {
		List<Authority> authorities2 =new ArrayList<>();
		for(Authority authority:authorities) {
			if(urls != null){
				for(String url:urls) {
					if(url.equals(authority.getUrl())) {
						authorities2.add(authority);
					}
				}
				
			}
			
		}
		// TODO Auto-generated method stub
		return authorities2;
	}

}
