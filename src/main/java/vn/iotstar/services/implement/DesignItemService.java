package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IDesignDao;
import vn.iotstar.dao.IDesignItemDao;
import vn.iotstar.dao.implement.DesignDao;
import vn.iotstar.dao.implement.DesignItemDao;
import vn.iotstar.entity.DesignItem;
import vn.iotstar.entity.Designs;
import vn.iotstar.services.IDesignItemService;
import vn.iotstar.services.IDesignService;

public class DesignItemService implements IDesignItemService {
	
	IDesignItemDao designDao = new DesignItemDao();
		
	@Override
	public void delete(int id) {
		designDao.delete(id);
	}
	@Override
	public void insert(DesignItem designItem) {
		designDao.insert(designItem);
	}
	@Override
	public List<DesignItem> findByIdDesign(int id) {
		return designDao.findByIdDesign(id);
	}
}
