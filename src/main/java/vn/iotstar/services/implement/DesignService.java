package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IDesignDao;
import vn.iotstar.dao.implement.DesignDao;
import vn.iotstar.entity.Designs;
import vn.iotstar.services.IDesignService;

public class DesignService implements IDesignService {
	
	IDesignDao designDao = new DesignDao();

	@Override
	public List<Designs> findAll() {
		return designDao.findAll();
	}

	@Override
	public Designs findById(int id) {
		return designDao.findById(id);
	}

	@Override
	public Designs insert(Designs design) {
		return designDao.insert(design);
	}

	@Override
	public void update(Designs design) {
		designDao.update(design);
	}

	@Override
	public void delete(int id) {
		designDao.delete(id);
	}

	@Override
	public List<Designs> findByTitle(String keyword) {
		return designDao.findByTitle(keyword);
	}

	@Override
	public List<Designs> findAll(int page, int pagesize) {
		return designDao.findAll(page, pagesize);
	}

	@Override
	public int countDesign(int pageSize) {
		return designDao.countDesign(pageSize);
	}

	@Override
	public int countDesign(int pageSize, String keyword) {
		return designDao.countDesign(pageSize, keyword);
	}

	@Override
	public List<Designs> findByTitle(int page, int pagesize, String keyword) {
		return designDao.findByTitle(page, pagesize, keyword);
	}

}
