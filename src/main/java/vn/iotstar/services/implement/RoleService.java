package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IRoleDao;
import vn.iotstar.dao.implement.RoleDao;
import vn.iotstar.entity.Role;
import vn.iotstar.services.IRoleService;

public class RoleService implements IRoleService{
	IRoleDao roleDao = new RoleDao();
	
	@Override
	public int count() {
		return roleDao.count();
	}

	@Override
	public List<Role> findAll(int page, int pagesize) {
		return roleDao.findAll(page, pagesize);
	}

	@Override
	public List<Role> findByName(String name) {
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findById(int id) {
		return roleDao.findById(id);
	}

	@Override
	public void delete(int id) throws Exception {
		roleDao.delete(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void insert(Role role) {
		roleDao.insert(role);
	}
}
