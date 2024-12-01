package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IDesignDao;
import vn.iotstar.dao.IProductDao;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

public class ProductDao implements IProductDao {

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	@Override
	public Product findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Product product = enma.find(Product.class, id);
		return product;
	}

	@Override
	public void insert(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
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
	public void update(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(product);
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
			Product product = enma.find(Product.class, id);
			if (product != null) {
				enma.remove(product);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findByName(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Product c WHERE c.name like :name";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter("name", "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	public List<Product> findProductActive() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Product c WHERE c.status = 1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
		query.setFirstResult((page-1) * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int countProduct(int pageSize) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String countQuery = "SELECT COUNT(u) FROM Product u";
	    Long count = (Long) enma.createQuery(countQuery).getSingleResult();
	    int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
	    return totalPages;
	}

	@Override
	public int countProduct(int pageSize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String countQuery = "SELECT COUNT(u) FROM Product u WHERE u.name like :name";
	    Query query = enma.createQuery(countQuery);
	    query.setParameter("name", "%" + keyword + "%");
	    Long count = (Long) query.getSingleResult();
	    int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
	    return totalPages;
	}

	@Override
	public List<Product> findByName(int page, int pagesize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String queryStr = "SELECT u FROM Product u WHERE u.name like :name";
	    TypedQuery<Product> query = enma.createQuery(queryStr, Product.class);
	    query.setParameter("name", "%" + keyword + "%");
	    query.setFirstResult((page - 1) * pagesize);
	    query.setMaxResults(pagesize);   
	    return query.getResultList();
	}
}
