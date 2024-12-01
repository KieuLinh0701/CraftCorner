package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Order;

public class OrderDao implements IOrderDao{
	
	@Override
	public Order insert(Order order) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order);
			Order newOrder = enma.merge(order);
			trans.commit();
			return newOrder;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	@PersistenceContext
    private EntityManager entityManager;

	// Lấy tổng số đơn hàng theo ngày, tháng, năm
    public Long getTotalOrders(String periodType, String periodValue) {
        String queryStr = "SELECT COUNT(o) FROM Order o WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(o.orderDate) = :periodValue";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(o.orderDate) = :year";
        }
        Query query = entityManager.createQuery(queryStr);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue); // Ex: 2024-11-30
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (Long) query.getSingleResult();
    }

    // Lấy doanh thu tổng theo ngày, tháng, năm
    public Double getTotalRevenue(String periodType, String periodValue) {
        String queryStr = "SELECT SUM(o.totalAmount) FROM Order o WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(o.orderDate) = :periodValue";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(o.orderDate) = :year";
        }
        Query query = entityManager.createQuery(queryStr);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue);
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (Double) query.getSingleResult();
    }

    // Lấy sản phẩm có doanh thu cao nhất
    public String getHighestRevenueProduct(String periodType, String periodValue) {
        // Lấy thông tin sản phẩm có doanh thu cao nhất (giả sử có bảng OrderDetail để tính doanh thu theo sản phẩm)
        String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(od.order.orderDate) = :periodValue GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(od.order.orderDate) = :month AND YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
        }
        Query query = entityManager.createQuery(queryStr).setMaxResults(1);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue);
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (String) query.getSingleResult();
    }

    // Các hàm còn lại (low, most, least) làm tương tự
    public String getLowestRevenueProduct(String periodType, String periodValue) {
        String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(od.order.orderDate) = :periodValue GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(od.order.orderDate) = :month AND YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
        }
        Query query = entityManager.createQuery(queryStr).setMaxResults(1);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue);
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (String) query.getSingleResult();
    }


    public String getMostPurchasedProduct(String periodType, String periodValue) {
        String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(od.order.orderDate) = :periodValue GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(od.order.orderDate) = :month AND YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
        }
        Query query = entityManager.createQuery(queryStr).setMaxResults(1);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue);
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (String) query.getSingleResult();
    }


    public String getLeastPurchasedProduct(String periodType, String periodValue) {
        String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE ";
        if (periodType.equals("date")) {
            queryStr += "DATE(od.order.orderDate) = :periodValue GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
        } else if (periodType.equals("month")) {
            queryStr += "MONTH(od.order.orderDate) = :month AND YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
        } else if (periodType.equals("year")) {
            queryStr += "YEAR(od.order.orderDate) = :year GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
        }
        Query query = entityManager.createQuery(queryStr).setMaxResults(1);
        
        // Set parameter
        if (periodType.equals("date")) {
            query.setParameter("periodValue", periodValue);
        } else if (periodType.equals("month")) {
            query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
            query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
        } else if (periodType.equals("year")) {
            query.setParameter("year", Integer.parseInt(periodValue));
        }

        return (String) query.getSingleResult();
    }


}
