package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IDesignDao;
import vn.iotstar.dao.IDesignItemDao;
import vn.iotstar.entity.DesignItem;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.User;

public class DesignItemDao implements IDesignItemDao {

	@Override
	public void delete(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			DesignItem designItem = enma.find(DesignItem.class, id);
			if (designItem != null) {
				enma.remove(designItem);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(designItem);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(DesignItem designItem) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(designItem);
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
	public List<DesignItem> findByIdDesign(int id) {
		EntityManager enma = JPAConfig.getEntityManager();

	    try {
	        String jpql = "SELECT c FROM DesignItem c WHERE c.design.designId = :id";
	        TypedQuery<DesignItem> query = enma.createQuery(jpql, DesignItem.class); 
	        query.setParameter("id", id);
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null; 
	    }
	}
}
