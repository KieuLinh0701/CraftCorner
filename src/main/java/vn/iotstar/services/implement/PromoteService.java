package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IPromoteDao;
import vn.iotstar.dao.implement.PromoteDao;
import vn.iotstar.entity.Promote;
import vn.iotstar.services.IPromoteService;

public class PromoteService implements IPromoteService{
	
	IPromoteDao promoteDao = new PromoteDao();
	
	@Override
	public List<Promote> findAll() {
		return promoteDao.findAll();
	}

	@Override
	public Promote findById(int id) {
		return promoteDao.findById(id);
	}

	@Override
	public List<Promote> findPromoteForOrder(int totalPrice) {
		return promoteDao.findPromoteForOrder(totalPrice);
	}

	@Override
	public void update(Promote promote) {
		promoteDao.update(promote);
	}

	@Override
	public void insert(Promote promote) {
        promoteDao.insert(promote);
		
	}

	@Override
	public void delete(Promote promote) {
        promoteDao.delete(promote);
		
	}

}
