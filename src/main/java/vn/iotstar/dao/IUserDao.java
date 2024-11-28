package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserDao {
	
	int count();

	List<User> findAll(int page, int pagesize);
	
	List<User> findByFullname(String fullname);

	List<User> findAll();

	User findById(int id);
	
	User findByEmail(String email);

	void delete(int id) throws Exception;

	void update(User user);

	void insert(User user);
	
	Boolean checkExistPhone(String phone);
	
	Boolean checkCode(String email, String code);
}
