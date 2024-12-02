package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
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
	public Designs insert(Designs design) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(design);
			enma.refresh(design);
			trans.commit();
			return design;
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
	public void delete(int id) {
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

	@Override
	public List<Designs> findByTitle(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Designs c WHERE c.title like :title";
		TypedQuery<Designs> query = enma.createQuery(jpql, Designs.class);
		query.setParameter("title", "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	public List<Designs> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Designs> query = enma.createNamedQuery("Designs.findAll", Designs.class);
		query.setFirstResult((page-1) * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int countDesign(int pageSize) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String countQuery = "SELECT COUNT(u) FROM Designs u";
	    Long count = (Long) enma.createQuery(countQuery).getSingleResult();
	    int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
	    return totalPages;
	}

	@Override
	public int countDesign(int pageSize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String countQuery = "SELECT COUNT(u) FROM Designs u WHERE u.title like :title";
	    Query query = enma.createQuery(countQuery);
	    query.setParameter("title", "%" + keyword + "%");
	    Long count = (Long) query.getSingleResult();
	    int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
	    return totalPages;
	}

	@Override
	public List<Designs> findByTitle(int page, int pagesize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String queryStr = "SELECT u FROM Designs u WHERE u.title LIKE :title";
	    TypedQuery<Designs> query = enma.createQuery(queryStr, Designs.class);
	    query.setParameter("title", "%" + keyword + "%");
	    query.setFirstResult((page - 1) * pagesize);
	    query.setMaxResults(pagesize);   
	    return query.getResultList();
	}
}
