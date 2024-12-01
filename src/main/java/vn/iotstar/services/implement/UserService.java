package vn.iotstar.services.implement;

import java.util.List;

import jakarta.persistence.NoResultException;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.implement.UserDao;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService{

	IUserDao userDao = new UserDao();
	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public User findByEmail(String email) {
		try {
			return userDao.findByEmail(email);
		}
		catch (NoResultException e){
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public void delete(int id) throws Exception {
		userDao.delete(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public List<User> findByFullname(String fullname) {
		return userDao.findByFullname(fullname);
	}

	@Override
	public Boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public Boolean checkCode(String email, String code) {
		return userDao.checkCode(email, code);
	}

	@Override
	public boolean checkPassword(String email, String password) {
		return userDao.checkPassword(email, password);
	}
//	public List<User> findAllCustomers() {
//        return userDao.findAll().stream()
//                       .filter(user -> user.getRole().getId() == 2) // Giả sử roleId = 2 là khách hàng
//                       .toList();
//    }
	
	@Override
    public List<User> findAllCustomers() {
        return userDao.findAllCustomers();
    }
}
