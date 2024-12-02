package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.IReviewDao;
import vn.iotstar.entity.Review;

public class ReviewDao implements IReviewDao {
	private EntityManagerFactory emf;

	public ReviewDao() {
		emf = Persistence.createEntityManagerFactory("default"); // Cập nhật tên persistence unit nếu khác
	}

	@Override
	public void saveReview(Review review) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(review);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}

	}
}
