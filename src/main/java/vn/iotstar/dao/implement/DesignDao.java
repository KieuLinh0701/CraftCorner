package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IDesignDao;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.User;

public class DesignDao implements IDesignDao {

	@Override
	public List<Designs> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Designs> query = enma.createNamedQuery("Designs.findAll", Designs.class);
		return query.getResultList();
	}

	@Override
	public Designs findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Designs design = enma.find(Designs.class, id);
		return design;
	}

	@Override
	public void insert(Designs design) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(design);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Designs design) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(design);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int id){
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Designs design = enma.find(Designs.class, id);
			if (design != null) {
				enma.remove(design);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(design);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	
}
