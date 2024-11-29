package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IDesignDao;
import vn.iotstar.entity.Designs;

public class DesignDao implements IDesignDao {

	@Override
	public List<Designs> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Designs> query = enma.createNamedQuery("Designs.findAll", Designs.class);
		return query.getResultList();
	}

	
}
