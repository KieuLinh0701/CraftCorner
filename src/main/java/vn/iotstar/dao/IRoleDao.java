package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Role;

public interface IRoleDao {

	int count();

	List<Role> findAll(int page, int pagesize);

	List<Role> findByName(String name);

	List<Role> findAll();

	Role findById(int id);

	void delete(int id) throws Exception;

	void update(Role role);

	void insert(Role role);
	
}
