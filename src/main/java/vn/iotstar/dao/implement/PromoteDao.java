package vn.iotstar.dao.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IPromoteDao;
import vn.iotstar.entity.Promote;

public class PromoteDao implements IPromoteDao {

	@Override
	public List<Promote> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Promote> query = enma.createNamedQuery("Promote.findAll", Promote.class);
		return query.getResultList();
	}

	@Override
	public Promote findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Promote promote = enma.find(Promote.class, id);
		return promote;
	}

	@Override
	public List<Promote> findPromoteForOrder(int totalPrice) {
		List<Promote> listPromote = new ArrayList<>();
		LocalDateTime currentDate = LocalDateTime.now();
		
		List<Promote> all = findAll();
		for (Promote x: all) {
			if ((currentDate.isEqual(x.getStartDate()) || currentDate.isAfter(x.getStartDate())) 
					&& (currentDate.isEqual(x.getEndDate()) || currentDate.isBefore(x.getEndDate()))
					&& x.getQuantity() > x.getQuantityUsed()
					&& totalPrice >= x.getMinOrderTotal()) {
				listPromote.add(x);
			}
		}
		return listPromote;
	}
}
