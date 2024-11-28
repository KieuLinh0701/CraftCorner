package vn.iotstar.dao.implement;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IAddressDao;
import vn.iotstar.entity.Address;

public class AddressDao implements IAddressDao{

	@Override
	public Address findByAddressId(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Address c WHERE c.address_id = :id";
        TypedQuery<Address> query = enma.createQuery(jpql, Address.class);
        query.setParameter("id", id);
        return query.getSingleResult();
	}

	@Override
	public void update(Address address) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(address);
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
	public Address insert(Address address) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(address);
			Address newAddress = enma.merge(address);
			trans.commit();
			return newAddress;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
