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

}
